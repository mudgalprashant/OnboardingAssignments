package com.newsletter.user.services;

import com.newsletter.user.models.User;

/**
 * The interface Auth service.
 */
public interface AuthService {
  /**
   * Authorize subscriber boolean.
   *
   * @param user   the user
   * @param bearer the bearer
   * @return the boolean
   */
  boolean isSubscriberAuthorized(User user, String bearer);

  /**
   * Authorize publisher boolean.
   *
   * @param user   the user
   * @param bearer the bearer
   * @return the boolean
   */
  boolean isPublisherAuthorized(User user, String bearer);

  /**
   * Is user authorized boolean.
   *
   * @param user   the user
   * @param bearer the bearer
   * @return the boolean
   */
  boolean isUserAuthorized(User user, String bearer);
}
