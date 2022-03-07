package com.newsletter.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Unauthorized response dto.
 */
@Data
public class UnauthorizedResponseDto {
  private final String message = "Permission Denied: Unauthorized access";
  private final int errorCode = 401;
  private Object metadata;
}
