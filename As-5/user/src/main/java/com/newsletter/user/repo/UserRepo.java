package com.newsletter.user.repo;

import com.newsletter.user.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
  @Query(
      value = "SELECT * FROM users u where u.email = ?1",
      nativeQuery = true)
  Optional<User> findByEmail(@Param("email") String email);
}
