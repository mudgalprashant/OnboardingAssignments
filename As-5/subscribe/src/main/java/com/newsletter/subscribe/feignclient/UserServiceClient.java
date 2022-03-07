package com.newsletter.subscribe.feignclient;

import com.newsletter.subscribe.constants.Constant;
import com.newsletter.subscribe.dto.ApiResponseDto;
import com.newsletter.subscribe.dto.SubscriptionDto;
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
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   */
  @GetMapping(Constant.USER_SERVICE_GET_BY_ID_MAPPING)
  ResponseEntity<Object> getUserById(@PathVariable Long id);

  /**
   * Authorize subscriber response entity.
   *
   * @param token the token
   * @param id    the id
   * @return the response entity
   */
  @GetMapping(Constant.USER_SERVICE_AUTHORIZE_SUBSCRIBER_MAPPING)
  ResponseEntity<ApiResponseDto> authorizeSubscriber(
      @RequestHeader(Constant.SECURITY_HEADER) String token,
      @PathVariable Long id);
}