package com.newsletter.subscribe.feignclient;

import com.newsletter.subscribe.constants.Constant;
import com.newsletter.subscribe.dto.ApiResponseDto;
import com.newsletter.subscribe.dto.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
