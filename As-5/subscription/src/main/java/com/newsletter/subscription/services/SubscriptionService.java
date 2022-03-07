package com.newsletter.subscription.services;

import com.newsletter.subscription.models.Subscription;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface Subscription service.
 */
public interface SubscriptionService {

  /**
   * Create subscription.
   *
   * @param subscription the subscription
   * @return the subscription
   */
  Subscription create(Subscription subscription);

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  Subscription getById(String id);

  /**
   * Gets all.
   *
   * @return the all
   */
  List<Subscription> getAll();

  /**
   * Search list.
   *
   * @param query the query
   * @return the list
   */
  List<Subscription> search(String query);

  /**
   * Update subscription.
   *
   * @param subscription the subscription
   * @param id           the id
   * @return the subscription
   */
  Subscription update(Subscription subscription, String id);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(String id);

  /**
   * Delete all.
   */
  void deleteAll();

}
