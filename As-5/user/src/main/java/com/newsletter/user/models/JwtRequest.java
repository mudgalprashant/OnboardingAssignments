package com.newsletter.user.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Jwt request.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtRequest implements Serializable {

  private static final long serialVersionUID = 5926468583005150707L;

  private String email;
  private String password;

}
