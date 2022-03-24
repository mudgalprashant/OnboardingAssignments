package com.newsletter.user.dto;

import com.newsletter.user.enums.Role;
import lombok.Data;

import java.util.Set;

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
