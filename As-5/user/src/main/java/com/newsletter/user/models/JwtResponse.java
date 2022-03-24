package com.newsletter.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * The type Jwt response.
 */
@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {

  private static final long serialVersionUID = -8091879091924046844L;
  private final String jwtToken;

}
