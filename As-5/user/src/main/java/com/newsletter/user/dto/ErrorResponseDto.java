package com.newsletter.user.dto;

import lombok.Data;

/**
 * The type Error response dto.
 */
@Data
public class ErrorResponseDto {
  private Exception error;
  private String details;
}
