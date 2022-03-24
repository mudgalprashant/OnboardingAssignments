package com.newsletter.user.models;

import com.newsletter.user.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The type User.
 */
@Getter
@Setter
@Entity(name = "users")
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @NotNull
  private String name;

  @Length(max = 45)
  @Column(unique = true, length = 45)
  @Email
  private String email;

  @Length(min = 8)
  @NotNull
  @NotBlank
  private String password;

  @Enumerated
  private Role role;
}
