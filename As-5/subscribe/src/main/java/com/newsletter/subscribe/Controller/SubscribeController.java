package com.newsletter.subscribe.Controller;

import com.newsletter.subscribe.constants.Constant;
import com.newsletter.subscribe.dto.ApiResponseDto;
import com.newsletter.subscribe.dto.SubRequestDto;
import com.newsletter.subscribe.dto.SubResponseDto;
import com.newsletter.subscribe.feignclient.UserServiceClient;
import com.newsletter.subscribe.mapper.SubMapper;
import com.newsletter.subscribe.models.Sub;
import com.newsletter.subscribe.services.SubService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@AllArgsConstructor
@RestController
public class SubscribeController {

  @Autowired
  private SubService subService;

  @Autowired
  private SubMapper subMapper;

  @Autowired
  private UserServiceClient userServiceClient;

  @CachePut(value = "subscribes")
  @PostMapping("/subscribe")
  ResponseEntity<ApiResponseDto> subscribe(@RequestBody SubRequestDto subRequestDto,
  @RequestHeader("Authorization") String token) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();


    if (Objects.equals(Objects.requireNonNull(userServiceClient
            .authorizeSubscriber(token, subRequestDto.getSubscriberId())
            .getBody())
        .getStatus(), Constant.ACCEPTED)) {
      final Sub sub = subService.subscribe(subMapper.requestDtoToSub(subRequestDto));
      if (Objects.isNull(sub)) {
        apiResponseDto.setStatus(Constant.FAILURE_STATUS);
      } else {
        apiResponseDto.setStatus(Constant.SUCCESS_STATUS);
        apiResponseDto.setBody(subMapper.subToResponseDto(sub));
      }
    }

    return ResponseEntity.ok(
        apiResponseDto
    );
  }

  @PutMapping("/renew/{id}")
  ResponseEntity<ApiResponseDto> renew(@PathVariable Long id) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();
    final Sub sub = subService.renew(id);
    if (Objects.isNull(sub)) {
      apiResponseDto.setStatus(Constant.FAILURE_STATUS);
    } else {
      apiResponseDto.setStatus(Constant.SUCCESS_STATUS);
      apiResponseDto.setBody(subMapper.subToResponseDto(sub));
    }
    return ResponseEntity.ok(
        apiResponseDto
    );
  }

  @DeleteMapping("/unsubscribe/{id}")
  @CacheEvict(value = "subscribes")
  void unSubscribe(@PathVariable Long id) {
    subService.unSubscribe(id);
  }


}
