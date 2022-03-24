package com.newsletter.email.feignclient;

import com.newsletter.email.constants.Constant;
import com.newsletter.email.dto.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = Constant.SUBSCRIBE_SERVICE_FC_NAME, url = Constant.SUBSCRIBE_SERVICE_FC_URL)
public interface SubscribeServiceClient {

  @GetMapping(Constant.SUBSCRIBE_SERVICE_FIND_SUBSCRIBER_ID_BY_SUBSCRIPTION_ID_MAPPING)
  ResponseEntity<ApiResponseDto> findSubscriberIdBySubscriptionId(@PathVariable String id);

}
