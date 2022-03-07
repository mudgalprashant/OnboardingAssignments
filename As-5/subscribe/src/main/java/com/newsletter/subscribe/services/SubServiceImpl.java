package com.newsletter.subscribe.services;

import com.newsletter.subscribe.feignclient.SubscriptionServiceClient;
import com.newsletter.subscribe.feignclient.UserServiceClient;
import com.newsletter.subscribe.models.Sub;
import com.newsletter.subscribe.repo.SubRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubServiceImpl implements SubService{

  @Autowired
  private SubRepo subRepo;

  @Autowired
  SubscriptionServiceClient subscriptionServiceClient;

  @Autowired
  UserServiceClient userServiceClient;

  @Override
  public Sub subscribe(Sub sub) {
    List<String> subscriptionsIds = subRepo
        .findAllBySubscriberId(sub.getSubscriberId())
        .stream()
        .map(Sub::getSubscriptionId)
        .collect(Collectors.toList());
    if (subscriptionsIds.contains(sub.getSubscriptionId())) {
      return null;
    }
    if (!Objects.isNull(subscriptionServiceClient.getById(sub.getSubscriptionId()))
        && !Objects.isNull(userServiceClient.getUserById(sub.getSubscriberId()))) {
      sub.setEndTimestamp(new Timestamp(
          new Timestamp(System.currentTimeMillis()).getTime() +
              Objects.requireNonNull(subscriptionServiceClient
                      .getById(sub.getSubscriptionId())
                      .getBody())
                  .getDuration()
      ));
      return subRepo.save(sub);
    } else {
      return null;
    }
  }

  @Override
  public Sub renew(Long id) {
    Sub sub = subRepo.findById(id).orElseThrow();
    if (Objects
        .requireNonNull(subscriptionServiceClient
            .getById(sub.getSubscriptionId())
            .getBody())
        .getRenewable()
    ) {
      List<String> subscriptionsIds = subRepo
          .findAllBySubscriberId(sub.getSubscriberId())
          .stream()
          .map(Sub::getSubscriptionId)
          .collect(Collectors.toList());
      if (subscriptionsIds.contains(sub.getSubscriptionId())) {
        sub.setEndTimestamp(new Timestamp(
            Math.max(sub.getEndTimestamp().getTime(), new Timestamp(System.currentTimeMillis()).getTime()) +
                Objects.requireNonNull(subscriptionServiceClient
                        .getById(sub.getSubscriptionId())
                        .getBody())
                    .getDuration()
            )
        );
        return sub;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  @Override
  public void unSubscribe(Long id) {
    final Sub sub = subRepo.findById(id).orElseThrow();
    if (!Objects.isNull(subscriptionServiceClient.getById(sub.getSubscriptionId()))
        && !Objects.isNull(userServiceClient.getUserById(sub.getSubscriberId()))) {
      subRepo.deleteById(id);
    }
  }
}
