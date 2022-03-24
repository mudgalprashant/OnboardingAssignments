package com.newsletter.email.feignclient;

import com.newsletter.email.constants.Constant;
import com.newsletter.email.dto.ApiResponseDto;
import com.newsletter.email.dto.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Subscription service client.
 */
@FeignClient(name = Constant.SUBSCRIPTION_SERVICE_FC_NAME, url = Constant.SUBSCRIPTION_SERVICE_FC_URL)
public interface SubscriptionServiceClient {

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  @GetMapping(Constant.SUBSCRIPTION_SERVICE_GET_BY_ID_MAPPING)
  ResponseEntity<ApiResponseDto> getById(@PathVariable String id);

  @GetMapping(Constant.SUBSCRIPTION_SERVICE_SEARCH_BY_PUBLISHER_ID_MAPPING)
  ResponseEntity<ApiResponseDto> searchByPublisherId(@RequestParam Long query);
}
