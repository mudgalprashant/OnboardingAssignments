package com.newsletter.subscription.dto;

import com.newsletter.subscription.enums.Role;
import lombok.Data;

/**
 * The type User response dto.
 */
@Data
public class UserResponseDto {
  private Long id;
  private String name;
  private String email;
  private Role role;
}
