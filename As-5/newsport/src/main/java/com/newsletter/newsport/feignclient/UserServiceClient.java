package com.newsletter.newsport.feignclient;

import com.newsletter.newsport.dto.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserServiceClient {

  @GetMapping("/newsletter/authorize/subscriber/{id}")
  ResponseEntity<ApiResponseDto> authorizeUser(
      @RequestHeader("Authorization") String token,
      @PathVariable Long id);

}
