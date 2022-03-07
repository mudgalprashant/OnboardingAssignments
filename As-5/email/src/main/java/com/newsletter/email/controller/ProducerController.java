package com.newsletter.email.controller;

import com.newsletter.email.config.KafkaProducerConfig;
import com.newsletter.email.constants.Constant;
import com.newsletter.email.dto.ApiResponseDto;
import com.newsletter.email.dto.EmailDto;
import com.newsletter.email.dto.UserResponseDto;
import com.newsletter.email.feignclient.UserServiceClient;
import com.newsletter.email.mapper.EmailMapper;
import com.newsletter.email.models.Email;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * The type Producer controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/newsletter")
public class ProducerController {

  @Autowired
  private KafkaProducerConfig producerConfig = new KafkaProducerConfig();

  @Autowired
  private KafkaTemplate<String, Email> kafkaTemplate;

  @Autowired
  private UserServiceClient userServiceClient;

  @Autowired
  private final EmailMapper emailMapper;


  /**
   * Publish response entity.
   *
   * @param emailDto the email dto
   * @param token    the token
   * @return the response entity
   */
  @PostMapping("/publish")
  public ResponseEntity<ApiResponseDto> publish(@RequestBody EmailDto emailDto,
                                                @RequestHeader("Authorization") String token) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();
    String senderEmail = "prashant.mudgal.test@gmail.com";
    UserResponseDto sender = (UserResponseDto) userServiceClient.getUserByEmail(senderEmail).getBody();

    if (Objects.isNull(sender)) {
      apiResponseDto.setStatus(Constant.FAILURE_STATUS);
      apiResponseDto.setBody("Could not find sender");
    } else if (Objects.equals(Objects.requireNonNull(userServiceClient
            .authorizePublisher(token, sender.getId())
            .getBody())
        .getStatus(), Constant.ACCEPTED)) {
      Email email = emailMapper.dtoToEmail(emailDto);
      kafkaTemplate.send(Constant.TOPIC, email.getReceiver(), email);
      apiResponseDto.setStatus(Constant.SUCCESS_STATUS);
      apiResponseDto.setBody(emailMapper.emailToDto(email));
    } else {
      apiResponseDto.setStatus(Constant.FAILURE_STATUS);
      apiResponseDto.setBody("Could not authorize and send email");
    }

    return ResponseEntity.ok(apiResponseDto);
  }
}
