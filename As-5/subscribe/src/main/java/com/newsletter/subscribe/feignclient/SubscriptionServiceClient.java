package com.newsletter.subscribe.feignclient;

import com.newsletter.subscribe.dto.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subscription-service", url = "http://localhost:8081")
public interface SubscriptionServiceClient {

  @GetMapping("/newsletter/subscriptions/{id}")
  ResponseEntity<SubscriptionDto> getById(@PathVariable String id);
}
