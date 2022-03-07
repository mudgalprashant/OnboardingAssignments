package com.newsletter.user.dto;

import com.newsletter.user.constants.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Unauthorized response dto.
 */
@Data
public class UnauthorizedResponseDto {
  private final String message = Constant.UNAUTHORIZED_ACCESS_MESSAGE;
  private final int errorCode = 401;
  private Object metadata;
}
