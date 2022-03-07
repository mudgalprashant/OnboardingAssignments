package com.newsletter.email.feignclient;

import com.newsletter.email.constants.Constant;
import com.newsletter.email.dto.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * The interface User service client.
 */
@FeignClient(name = Constant.USER_SERVICE_FC_NAME, url = Constant.USER_SERVICE_FC_URL)
public interface UserServiceClient {

  /**
   * Gets user by email.
   *
   * @param email the email
   * @return the user by email
   */
  @GetMapping(Constant.USER_SERVICE_GET_BY_EMAIL_MAPPING)
  ResponseEntity<Object> getUserByEmail(@PathVariable String email);

  /**
   * Authorize publisher response entity.
   *
   * @param token the token
   * @param id    the id
   * @return the response entity
   */
  @GetMapping(Constant.USER_SERVICE_AUTHORIZE_PUBLISHER_MAPPING)
  ResponseEntity<ApiResponseDto> authorizePublisher(
      @RequestHeader(Constant.SECURITY_HEADER) String token,
      @PathVariable Long id);
}