package com.newsletter.subscribe.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsletter.subscribe.constants.Constant;
import com.newsletter.subscribe.dto.SubscriptionDto;
import com.newsletter.subscribe.dto.UserResponseDto;
import com.newsletter.subscribe.feignclient.SubscriptionServiceClient;
import com.newsletter.subscribe.feignclient.UserServiceClient;
import com.newsletter.subscribe.models.Sub;
import com.newsletter.subscribe.repo.SubRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Sub service.
 */
@AllArgsConstructor
@Service
public class SubServiceImpl implements SubService{

  @Autowired
  private SubRepo subRepo;

  /**
   * The Subscription service client.
   */
  @Autowired
  SubscriptionServiceClient subscriptionServiceClient;

  /**
   * The User service client.
   */
  @Autowired
  UserServiceClient userServiceClient;

  @Autowired
  ObjectMapper objectMapper;


  @Cacheable(value = Constant.CACHE_VALUE, key = "#id")
  @Override
  public Sub findById(final Long id) {
    return subRepo.findById(id).orElseThrow();
  }



  @CachePut(value = Constant.CACHE_VALUE)
  @Override
  public Sub subscribe(final Sub sub) {

    // Find all subscription of current subscriber and check if user has already subscribed.
    List<String> subscriptionsIds = subRepo
        .findAllBySubscriberId(sub.getSubscriberId())
        .stream()
        .map(Sub::getSubscriptionId)
        .collect(Collectors.toList());
    if (subscriptionsIds.contains(sub.getSubscriptionId())) {
      return null;
    }

    // Set end timestamp for subscription if both subscription and subscriber exist.

    final SubscriptionDto subscriptionDto = objectMapper.convertValue(
        Objects.requireNonNull(
                subscriptionServiceClient
                    .getById(sub.getSubscriptionId())
                    .getBody())
            .getBody(),
        SubscriptionDto.class);

    final UserResponseDto userResponseDto = objectMapper.convertValue(
        Objects.requireNonNull(
            userServiceClient.
                getUserById(sub.getSubscriberId())
                .getBody())
            .getBody(),
        UserResponseDto.class);

    if (!Objects.isNull(subscriptionDto) && !Objects.isNull(userResponseDto)) {
      sub.setEndTimestamp(new Timestamp(
          new Timestamp(System.currentTimeMillis()).getTime() +
              subscriptionDto.getDuration()
      ));
      return subRepo.save(sub);
    } else {
      return null;
    }
  }

  @Cacheable(value = Constant.CACHE_VALUE, key = "#id")
  @Override
  public Sub renew(final Long id) {
    Sub sub = subRepo.findById(id).orElseThrow();

    // Check if subscription is renewable and renew accordingly
    final SubscriptionDto subscriptionDto = objectMapper.convertValue(
        Objects.requireNonNull(
            subscriptionServiceClient
                .getById(sub.getSubscriptionId())
                .getBody())
            .getBody(),
        SubscriptionDto.class);
    if (subscriptionDto.getRenewable()) {
      List<String> subscriptionsIds = subRepo
          .findAllBySubscriberId(sub.getSubscriberId())
          .stream()
          .map(Sub::getSubscriptionId)
          .collect(Collectors.toList());

      // If subscription exists for the current subscriber
      // then, update endTimeStamp
      if (subscriptionsIds.contains(sub.getSubscriptionId())) {
        sub.setEndTimestamp(
            new Timestamp(
                Math.max(sub.getEndTimestamp().getTime(),
                new Timestamp(System.currentTimeMillis()).getTime()) +
                    subscriptionDto.getDuration()
            )
        );
        return subRepo.save(sub);
      } else {
        return null;
      }
    } else {
      return null;
    }
  }


  @CacheEvict(value = Constant.CACHE_VALUE)
  @Override
  public void unSubscribe(final Long id) {
    final Sub sub = subRepo.findById(id).orElseThrow();
    if (!Objects.isNull(subscriptionServiceClient.getById(sub.getSubscriptionId()))
        && !Objects.isNull(userServiceClient.getUserById(sub.getSubscriberId()))) {
      subRepo.deleteById(id);
    }
  }

  @Override
  public List<Long> findSubscriberIdBySubscriptionId(String id) {
    return subRepo
        .findAllBySubscriptionId(id)
        .stream()
        .map(Sub::getSubscriberId)
        .collect(Collectors.toList());
  }


}
