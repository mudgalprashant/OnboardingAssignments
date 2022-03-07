package com.newsletter.email.dto;

import lombok.Data;

/**
 * The type Api response dto.
 */
@Data
public class ApiResponseDto {
  private String status;
  private Object body;
}
