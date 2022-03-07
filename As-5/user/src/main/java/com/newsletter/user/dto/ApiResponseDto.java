package com.newsletter.user.dto;

import com.newsletter.user.constants.Constant;
import lombok.Data;

/**
 * The type Api response dto.
 */
@Data
public class ApiResponseDto {
  private String status;
  private Object body;
}
