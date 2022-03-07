package com.newsletter.user.repo;

import com.newsletter.user.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repo.
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long> {

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<User> findByEmail(String email);

  /**
   * Exists by email boolean.
   *
   * @param email the email
   * @return the boolean
   */
  Boolean existsByEmail(String email);

}
