package com.newsletter.subscribe.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * The enum Role.
 */
public enum Role implements GrantedAuthority {
  /**
   * Role publisher role.
   */
  ROLE_PUBLISHER,
  /**
   * Role subscriber role.
   */
  ROLE_SUBSCRIBER;

  @Override
  public String getAuthority() {
    return name();
  }
}
