package com.newsletter.subscription.feignclient;

import com.newsletter.subscription.constants.PathConstant;
import com.newsletter.subscription.constants.UtilityConstant;
import com.newsletter.subscription.dto.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * The interface User service client.
 */
@FeignClient(name = UtilityConstant.USER_SERVICE_FC_NAME, url = UtilityConstant.USER_SERVICE_FC_URL)
public interface UserServiceClient {

  /**
   * Authorize subscriber response entity.
   *
   * @param bearer the bearer
   * @return the response entity
   */
  @GetMapping(PathConstant.USER_SERVICE_AUTHORIZE_PUBLISHER_MAPPING)
  ResponseEntity<ApiResponseDto> authorizePublisher(
      @RequestHeader(UtilityConstant.SECURITY_HEADER) String bearer);

  @GetMapping(PathConstant.USER_SERVICE_BY_TOKEN_MAPPING)
  ResponseEntity<ApiResponseDto> getUserByToken(
      @RequestHeader(UtilityConstant.SECURITY_HEADER) String bearer);
}