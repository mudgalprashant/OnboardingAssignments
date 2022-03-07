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

/**
 * The type Subscribe controller.
 */
@AllArgsConstructor
@RestController
public class SubscribeController {

  @Autowired
  private SubService subService;

  @Autowired
  private SubMapper subMapper;

  @Autowired
  private UserServiceClient userServiceClient;

  /**
   * Subscribe response entity.
   *
   * @param subRequestDto the sub request dto
   * @param token         the token
   * @return the response entity
   */
  @PostMapping(Constant.SUBSCRIBE_MAPPING)
  ResponseEntity<ApiResponseDto> subscribe(@RequestBody SubRequestDto subRequestDto,
  @RequestHeader(Constant.SECURITY_HEADER) String token) {

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

  /**
   * Renew response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @PutMapping(Constant.RENEW_MAPPING + Constant.BY_ID_MAPPING)
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

  /**
   * Un subscribe.
   *
   * @param id the id
   */
  @DeleteMapping(Constant.UNSUBSCRIBE_MAPPING + Constant.BY_ID_MAPPING)
  void unSubscribe(@PathVariable Long id) {
    subService.unSubscribe(id);
  }


}
