package com.newsletter.subscribe.services;

import com.newsletter.subscribe.models.Sub;

import java.util.List;

/**
 * The interface Sub service.
 */
public interface SubService {

  Sub findById(Long id);
  /**
   * Subscribe sub.
   *
   * @param sub the sub
   * @return the sub
   */
  Sub subscribe(Sub sub);

  /**
   * Renew sub.
   *
   * @param id the id
   * @return the sub
   */
  Sub renew(Long id);

  /**
   * Un subscribe.
   *
   * @param id the id
   */
  void unSubscribe(Long id);

  List<Long> findSubscriberIdBySubscriptionId(String id);

}
