package com.newsletter.user.services;

import com.newsletter.user.models.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

  /**
   * Create user.
   *
   * @param user the user
   * @return the user
   */
  User createUser(User user);

  /**
   * Gets all user.
   *
   * @return the all user
   */
  List<User> getAllUser();

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   */
  User getUserById(long id);

  /**
   * Gets user by email.
   *
   * @param email the email
   * @return the user by email
   */
  User getUserByEmail(String email);

  /**
   * Update user user.
   *
   * @param user the user
   * @param id   the id
   * @return the user
   */
  User updateUser(User user, long id);

  /**
   * Delete user.
   *
   * @param id the id
   */
  void deleteUser(long id);

  /**
   * Copy non null.
   *
   * @param source      the source
   * @param destination the destination
   */
  void copyNonNull(Object source, Object destination);

}
