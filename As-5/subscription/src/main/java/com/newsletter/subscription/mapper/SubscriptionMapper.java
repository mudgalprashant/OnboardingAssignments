package com.newsletter.subscription.mapper;

import com.newsletter.subscription.dto.SubscriptionDto;
import com.newsletter.subscription.models.Subscription;
import org.mapstruct.Mapper;

/**
 * The interface Subscription mapper.
 */
@Mapper
public interface SubscriptionMapper {

  /**
   * Subscription to dto subscription dto.
   *
   * @param subscription the subscription
   * @return the subscription dto
   */
  SubscriptionDto subscriptionToDto(Subscription subscription);

  /**
   * Dto to subscription subscription.
   *
   * @param subscriptionDto the subscription dto
   * @return the subscription
   */
  Subscription dtoToSubscription(SubscriptionDto subscriptionDto);

}
