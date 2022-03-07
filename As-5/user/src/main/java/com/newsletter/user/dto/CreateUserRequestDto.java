package com.newsletter.user.dto;

import com.newsletter.user.enums.Role;
import lombok.Data;


/**
 * The type User dto.
 */
@Data
public class CreateUserRequestDto {
  private Long id;
  private String name;
  private String email;
  private String password;
  private Role role;
}
