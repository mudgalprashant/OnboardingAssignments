package com.newsletter.email.feignclient;

import com.newsletter.email.dto.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * The interface User service client.
 */
@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserServiceClient {

  /**
   * Gets user by email.
   *
   * @param email the email
   * @return the user by email
   */
  @GetMapping("/newsletter/users/email/{email}")
  ResponseEntity<Object> getUserByEmail(@PathVariable String email);

  /**
   * Authorize publisher response entity.
   *
   * @param token the token
   * @param id    the id
   * @return the response entity
   */
  @GetMapping("/newsletter/auth/authorize/publisher/{id}")
  ResponseEntity<ApiResponseDto> authorizePublisher(
      @RequestHeader("Authorization") String token,
      @PathVariable Long id);
}