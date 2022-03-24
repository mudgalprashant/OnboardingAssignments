package com.newsletter.subscribe.dto;

import com.newsletter.subscribe.enums.Role;
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
