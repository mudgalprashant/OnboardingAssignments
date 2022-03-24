package com.newsletter.user.services;

import com.newsletter.user.models.User;

/**
 * The interface Auth service.
 */
public interface AuthService {
  /**
   * Authorize subscriber boolean.
   *
   * @param bearer the bearer
   * @return the boolean
   */
  boolean isSubscriberAuthorized(String bearer);

  /**
   * Authorize publisher boolean.
   *
   * @param bearer the bearer
   * @return the boolean
   */
  boolean isPublisherAuthorized(String bearer);

  /**
   * Is user authorized boolean.
   *
   * @param bearer the bearer
   * @return the boolean
   */
  boolean isUserAuthorized(String bearer);
}
