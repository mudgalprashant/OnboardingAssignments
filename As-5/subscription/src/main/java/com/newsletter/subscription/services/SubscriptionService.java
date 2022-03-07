package com.newsletter.subscription.services;

import com.newsletter.subscription.models.Subscription;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubscriptionService {

  Subscription create(Subscription subscription);

  Subscription getById(String id);

  List<Subscription> getAll();

  List<Subscription> search(String query);

  Subscription update(Subscription subscription, String id);

  void delete(String id);

  void deleteAll();

}
