package com.newsletter.email.dto;

import com.newsletter.email.enums.Role;
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
