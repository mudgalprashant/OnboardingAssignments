package com.newsletter.subscription.mapper;

import com.newsletter.subscription.dto.SubscriptionDto;
import com.newsletter.subscription.models.Subscription;
import org.mapstruct.Mapper;

@Mapper
public interface SubscriptionMapper {

  SubscriptionDto subscriptionToDto(Subscription subscription);

  Subscription dtoToSubscription(SubscriptionDto subscriptionDto);

}
