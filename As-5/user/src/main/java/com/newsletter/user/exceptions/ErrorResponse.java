package com.newsletter.user.exceptions;

import lombok.Data;

/**
 * The type Error response.
 */
@Data
public class ErrorResponse {
  private Exception error;
  private String details;
}
