package com.newsletter.subscription.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsletter.subscription.constants.MessageConstant;
import com.newsletter.subscription.constants.PathConstant;
import com.newsletter.subscription.constants.UtilityConstant;
import com.newsletter.subscription.dto.*;
import com.newsletter.subscription.feignclient.UserServiceClient;
import com.newsletter.subscription.mapper.SubscriptionMapper;
import com.newsletter.subscription.models.Subscription;
import com.newsletter.subscription.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Subscription controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping(PathConstant.SUBSCRIPTIONS_MAPPING)
public class SubscriptionController {

  @Autowired
  private final SubscriptionService subscriptionService;
  @Autowired
  private final SubscriptionMapper subscriptionMapper;
  @Autowired
  private final UserServiceClient userServiceClient;

  private final ObjectMapper objectMapper;

  /**
   * Create response entity.
   *
   * @param subscriptionDto the subscription dto
   * @return the response entity
   */
  @PostMapping
  ResponseEntity<ApiResponseDto> create(
      @RequestBody SubscriptionDto subscriptionDto,
      @RequestHeader(UtilityConstant.SECURITY_HEADER) String bearer) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      // Authorize publisher
      boolean authorized = Objects.requireNonNull(userServiceClient
              .authorizePublisher(bearer)
              .getBody())
          .getStatus()
          .equalsIgnoreCase(MessageConstant.ACCEPTED);

      if (authorized) {
        Subscription subscription = subscriptionMapper
            .dtoToSubscription(subscriptionDto);

        // Get publisher
        UserResponseDto userResponseDto =
            objectMapper.convertValue(
                Objects.requireNonNull(userServiceClient
                        .getUserByToken(bearer)
                        .getBody())
                    .getBody(),
                UserResponseDto.class
            );
        // Add publisher id to subscription

        subscription.setPublisherId(userResponseDto.getId());

        // Create Subscription
        subscriptionDto =
            subscriptionMapper
                .subscriptionToDto(subscriptionService
                    .create(subscription));
        apiResponseDto.setBody(subscriptionDto);

        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);

      } else {
        apiResponseDto.setStatus(MessageConstant.FAILED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_CREATION_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);

  }

  /**
   * Gets all subscriptions.
   *
   * @return the list of subscriptions
   */
  @GetMapping
  ResponseEntity<ApiResponseDto> getAll() {


    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      List<SubscriptionDto> subscriptionDtoList = subscriptionService
          .getAll()
          .stream()
          .map(subscriptionMapper::subscriptionToDto)
          .collect(Collectors.toList());

      apiResponseDto.setBody(subscriptionDtoList);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);

    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_FETCH_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);

  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  @GetMapping(PathConstant.BY_ID_MAPPING)
  ResponseEntity<ApiResponseDto> getById(@PathVariable String id) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      SubscriptionDto subscriptionDto = subscriptionMapper
          .subscriptionToDto(subscriptionService.getById(id));

      apiResponseDto.setBody(subscriptionDto);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_NOT_FOUND);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Search response entity.
   *
   * @param query the query
   * @return the response entity
   */
  @GetMapping(PathConstant.SEARCH_MAPPING + PathConstant.BY_NAME_MAPPING)
  ResponseEntity<ApiResponseDto> searchByName (@RequestParam String query) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      List<SubscriptionDto> subscriptionDtoList = subscriptionService
          .searchByName(query)
          .stream()
          .map(subscriptionMapper::subscriptionToDto)
          .collect(Collectors.toList());

      apiResponseDto.setBody(subscriptionDtoList);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_FETCH_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  @GetMapping(PathConstant.SEARCH_MAPPING + PathConstant.BY_CATEGORY_MAPPING)
  ResponseEntity<ApiResponseDto> searchByCategory (@RequestParam String query) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      List<SubscriptionDto> subscriptionDtoList = subscriptionService
          .searchByCategory(query)
          .stream()
          .map(subscriptionMapper::subscriptionToDto)
          .collect(Collectors.toList());

      apiResponseDto.setBody(subscriptionDtoList);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_FETCH_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  @GetMapping(PathConstant.SEARCH_MAPPING + PathConstant.BY_PUBLISHER_ID)
  ResponseEntity<ApiResponseDto> searchByPublisherId (@RequestParam Long query) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      List<SubscriptionDto> subscriptionDtoList = subscriptionService
          .searchByPublisherId(query)
          .stream()
          .map(subscriptionMapper::subscriptionToDto)
          .collect(Collectors.toList());

      apiResponseDto.setBody(subscriptionDtoList);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_FETCH_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  @PutMapping(PathConstant.BY_ID_MAPPING)
  ResponseEntity<ApiResponseDto> update(
      @RequestBody SubscriptionDto subscriptionDto,
      @PathVariable String id,
      @RequestHeader(UtilityConstant.SECURITY_HEADER) String bearer) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      // Authorize publisher(owner of subscription)
      boolean authorized = Objects.requireNonNull(userServiceClient
              .authorizePublisher(bearer)
              .getBody())
          .getStatus()
          .equalsIgnoreCase(MessageConstant.ACCEPTED);

      // Get publisher
      UserResponseDto userResponseDto =
          objectMapper.convertValue(
              Objects.requireNonNull(userServiceClient
                      .getUserByToken(bearer)
                      .getBody())
                  .getBody(),
              UserResponseDto.class
          );
      if (authorized && Objects.equals(userResponseDto.getId(), subscriptionDto.getPublisherId())) {

        // Update subscription
        subscriptionDto = subscriptionMapper.subscriptionToDto(
            subscriptionService.update(
                subscriptionMapper.dtoToSubscription(subscriptionDto), id));
        apiResponseDto.setBody(subscriptionDto);

        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);
      } else {
        apiResponseDto.setStatus(MessageConstant.FAILED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_UPDATE_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @DeleteMapping(PathConstant.BY_ID_MAPPING)
  ResponseEntity<ApiResponseDto> delete(
      @PathVariable String id,
      @RequestHeader(UtilityConstant.SECURITY_HEADER) String bearer) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {
      // Authorize publisher(owner of subscription)
      boolean authorized = Objects.requireNonNull(userServiceClient
              .authorizePublisher(bearer)
              .getBody())
          .getStatus()
          .equalsIgnoreCase(MessageConstant.ACCEPTED);

      // Get publisher
      UserResponseDto userResponseDto =
          objectMapper.convertValue(
              Objects.requireNonNull(userServiceClient
                      .getUserByToken(bearer)
                      .getBody())
                  .getBody(),
              UserResponseDto.class
          );
      if (authorized &&
          Objects.equals(userResponseDto.getId(), subscriptionService.getById(id).getPublisherId())) {

        // Delete subscription
        subscriptionService.delete(id);

        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);
      } else {
        apiResponseDto.setStatus(MessageConstant.FAILED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.SUBSCRIPTION_DELETION_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

}
