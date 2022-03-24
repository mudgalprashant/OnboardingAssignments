package com.newsletter.subscribe.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsletter.subscribe.constants.Constant;
import com.newsletter.subscribe.constants.MessageConstant;
import com.newsletter.subscribe.dto.ApiResponseDto;
import com.newsletter.subscribe.dto.SubRequestDto;
import com.newsletter.subscribe.dto.UserResponseDto;
import com.newsletter.subscribe.feignclient.UserServiceClient;
import com.newsletter.subscribe.mapper.SubMapper;
import com.newsletter.subscribe.models.Sub;
import com.newsletter.subscribe.services.SubService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The type Subscribe controller.
 */
@AllArgsConstructor
@RestController
public class SubscribeController {

  @Autowired
  private SubService subService;

  @Autowired
  private SubMapper subMapper;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserServiceClient userServiceClient;

  /**
   * Subscribe response entity.
   *
   * @param subRequestDto the sub request dto
   * @return the response entity
   */
  @PostMapping(Constant.SUBSCRIBE_MAPPING)
  ResponseEntity<ApiResponseDto> subscribe(@RequestBody SubRequestDto subRequestDto,
  @RequestHeader(Constant.SECURITY_HEADER) String bearer) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {
      // Authorize subscriber
      if (Objects.equals(Objects.requireNonNull(userServiceClient
              .authorizeSubscriber(bearer)
              .getBody())
          .getStatus(), MessageConstant.ACCEPTED)) {

        // Add subscriber ID to subscribe
        Sub sub = subMapper.requestDtoToSub(subRequestDto);
        sub.setSubscriberId(
            objectMapper.convertValue(
                Objects.requireNonNull(userServiceClient
                        .getUserByToken(bearer)
                        .getBody())
                    .getBody(),
                UserResponseDto.class
            ).getId()
        );

        // Subscribe to subscription
        sub = subService.subscribe(sub);
        if (Objects.isNull(sub)) {
          apiResponseDto.setStatus(MessageConstant.DENIED);
          apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_ALREADY_EXISTS);
        } else {
          apiResponseDto.setStatus(MessageConstant.ACCEPTED);
          apiResponseDto.setMessage(MessageConstant.SUCCESS);
          apiResponseDto.setBody(subMapper.subToResponseDto(sub));
        }
      } else {
        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.FAILED);
    }
    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Renew response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @PutMapping(Constant.RENEW_MAPPING + Constant.BY_ID_MAPPING)
  ResponseEntity<ApiResponseDto> renew(
      @PathVariable Long id,
      @RequestHeader(Constant.SECURITY_HEADER) String bearer) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {
      // Authorize subscriber
      if (Objects.equals(Objects.requireNonNull(userServiceClient
              .authorizeSubscriber(bearer)
              .getBody())
          .getStatus(), MessageConstant.ACCEPTED)) {

        // Renew subscription
        Sub sub = subService.renew(id);
        apiResponseDto.setBody(subMapper.subToResponseDto(sub));
        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);

      } else {
        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }

    } catch (NoSuchElementException exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.ID_NOT_FOUND);

    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Un subscribe.
   *
   * @param id the id
   */
  @DeleteMapping(Constant.UNSUBSCRIBE_MAPPING + Constant.BY_ID_MAPPING)
  void unSubscribe(@PathVariable Long id) {
    subService.unSubscribe(id);
  }


  @GetMapping(Constant.FIND_BY_SUBSCRIPTION_MAPPING + Constant.BY_ID_MAPPING)
  ResponseEntity<ApiResponseDto> findSubscriberIdBySubscriptionId(@PathVariable String id) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();
    try {
      List<Long> subscriberIdList =  subService.findSubscriberIdBySubscriptionId(id);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);
      apiResponseDto.setBody(subscriberIdList);
    } catch (Exception exception) {
      exception.printStackTrace();
      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.FAILED);
    }
    return ResponseEntity.ok(apiResponseDto);
  }
}
