package com.newsletter.subscribe.services;

import com.newsletter.subscribe.models.Sub;

/**
 * The interface Sub service.
 */
public interface SubService {

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

}
