package com.newsletter.user.dto;

import com.newsletter.user.enums.Role;
import lombok.Data;

/**
 * The type User response dto.
 */
@Data
public class UserResponseDto {
  private long id;
  private String name;
  private String email;
  private Role role;
}
