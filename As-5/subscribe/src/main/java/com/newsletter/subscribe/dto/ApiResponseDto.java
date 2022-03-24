package com.newsletter.subscribe.dto;

import lombok.Data;

/**
 * The type Api response dto.
 */
@Data
public class ApiResponseDto {
  private String status;
  private String message;
  private Object body;
}
