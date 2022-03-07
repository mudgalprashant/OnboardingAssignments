package com.newsletter.subscribe.feignclient;

import com.newsletter.subscribe.dto.ApiResponseDto;
import com.newsletter.subscribe.dto.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserServiceClient {

  @GetMapping("/newsletter/users/{id}")
  ResponseEntity<Object> getUserById(@PathVariable Long id);

  @GetMapping("/newsletter/auth/authorize/subscriber/{id}")
  ResponseEntity<ApiResponseDto> authorizeSubscriber(
      @RequestHeader("Authorization") String token,
      @PathVariable Long id);
}