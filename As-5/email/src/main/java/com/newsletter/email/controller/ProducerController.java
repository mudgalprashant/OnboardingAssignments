package com.newsletter.email.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsletter.email.config.KafkaProducerConfig;
import com.newsletter.email.constants.Constant;
import com.newsletter.email.dto.ApiResponseDto;
import com.newsletter.email.dto.EmailDto;
import com.newsletter.email.dto.SubscriptionDto;
import com.newsletter.email.dto.UserResponseDto;
import com.newsletter.email.feignclient.SubscribeServiceClient;
import com.newsletter.email.feignclient.SubscriptionServiceClient;
import com.newsletter.email.feignclient.UserServiceClient;
import com.newsletter.email.mapper.EmailMapper;
import com.newsletter.email.models.Email;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Producer controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping(Constant.BASE_MAPPING)
public class ProducerController {

  @Autowired
  private KafkaProducerConfig producerConfig = new KafkaProducerConfig();

  @Autowired
  private KafkaTemplate<String, Email> kafkaTemplate;

  @Autowired
  private UserServiceClient userServiceClient;

  @Autowired
  private SubscriptionServiceClient subscriptionServiceClient;

  @Autowired
  private SubscribeServiceClient subscribeServiceClient;

  @Autowired
  private final EmailMapper emailMapper;

  private final ObjectMapper objectMapper;


  /**
   * Publish response entity.
   *
   * @param emailDto the email dto
   * @param token    the token
   * @return the response entity
   */
  @PostMapping(Constant.PUBLISH_MAPPING)
  public ResponseEntity<ApiResponseDto> publish(
      @RequestBody EmailDto emailDto,
      @RequestHeader(Constant.SECURITY_HEADER) String token) {

    // Get sender using sender email
    ApiResponseDto apiResponseDto = new ApiResponseDto();
    UserResponseDto sender =
        objectMapper
            .convertValue(userServiceClient
                    .getUserByEmail(emailDto.getSender()).getBody().getBody(),
                UserResponseDto.class);

    if (Objects.isNull(sender)) {

      apiResponseDto.setStatus(Constant.FAILURE);
      apiResponseDto.setBody(Constant.SENDER_NOT_FOUND);

      // Authorize sender as publisher
    } else if (Objects.equals(Objects.requireNonNull(userServiceClient
            .authorizePublisher(token)
            .getBody())
        .getStatus(), Constant.ACCEPTED)) {

      // Get Subscriptions of the publisher
      List<SubscriptionDto> subscriptionDtoList = objectMapper.convertValue(
          Objects.requireNonNull(subscriptionServiceClient
                  .searchByPublisherId(sender.getId())
                  .getBody())
              .getBody(),
          List.class);

      subscriptionDtoList =
          objectMapper
              .convertValue(subscriptionDtoList, new TypeReference<List<SubscriptionDto>>() {
              });


      // Get subscribers' ids of the subscriptions
      List<Long> subscriberIdList = new ArrayList<>();
      for (SubscriptionDto subscriptionDto : subscriptionDtoList) {
        subscriberIdList.addAll(objectMapper.convertValue(
            Objects.requireNonNull(subscribeServiceClient
                    .findSubscriberIdBySubscriptionId(subscriptionDto.getId())
                    .getBody())
                .getBody(),
            List.class)
        );
      }


      subscriberIdList =
          objectMapper
              .convertValue(subscriberIdList, new TypeReference<List<Long>>() {
              });

      Email email = emailMapper.dtoToEmail(emailDto);

      List<String> subscriberEmailList = new ArrayList<>();
      // send email to each subscriber
      for (Long receiverId : subscriberIdList) {

        subscriberEmailList.add(
            objectMapper
                .convertValue(userServiceClient
                        .getUserById(receiverId).getBody().getBody(),
                    UserResponseDto.class)
                .getEmail());
      }
      email.setReceiverList(subscriberEmailList);
      kafkaTemplate.send(Constant.TOPIC, email);

      apiResponseDto.setStatus(Constant.ACCEPTED);
      apiResponseDto.setMessage(Constant.SUCCESS);
      apiResponseDto.setBody(emailMapper.emailToDto(email));

    } else {
      apiResponseDto.setStatus(Constant.DENIED);
      apiResponseDto.setMessage(Constant.FAILURE);
      apiResponseDto.setBody(Constant.UNAUTHORIZED_SENDER);
    }

    return ResponseEntity.ok(apiResponseDto);
  }
}
