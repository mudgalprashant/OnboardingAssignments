package com.newsletter.user.models;

import com.newsletter.user.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;

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
  private long id;

  @NotBlank
  @NotNull
  private String name;

  @Column(unique = true)
  @Email
  private String email;

  @Length(min = 8)
  @NotNull
  @NotBlank
  private String password;

  @Enumerated
  @Column(nullable = false)
  private Role role;
}
