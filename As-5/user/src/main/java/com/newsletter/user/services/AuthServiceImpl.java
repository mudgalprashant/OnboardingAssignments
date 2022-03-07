package com.newsletter.user.services;

import com.newsletter.user.config.JwtTokenUtil;
import com.newsletter.user.enums.Role;
import com.newsletter.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * The type Auth service.
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  public boolean isSubscriberAuthorized(User user, String bearer) {
    boolean authorized = false;
    try {
      authorized = user.getRole() == Role.ROLE_SUBSCRIBER
          && ((LinkedHashMap<String, String>)
          jwtTokenUtil
              .getAuthorizationRoleFromToken(bearer.substring(7))
              .get(0))
          .get("authority")
          .equalsIgnoreCase(user.getRole().toString());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return authorized;
  }

  @Override
  public boolean isPublisherAuthorized(User user, String bearer) {
    boolean authorized = false;
    try {
      authorized = user.getRole() == Role.ROLE_PUBLISHER
          && ((LinkedHashMap<String, String>)
          jwtTokenUtil
              .getAuthorizationRoleFromToken(bearer.substring(7))
              .get(0))
          .get("authority")
          .equalsIgnoreCase(user.getRole().toString());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return authorized;
  }

  @Override
  public boolean isUserAuthorized(User user, String bearer) {
    boolean authorized = false;
    try {
      authorized = ((LinkedHashMap<String, String>)
          jwtTokenUtil
              .getAuthorizationRoleFromToken(bearer.substring(7))
              .get(0))
          .get("authority")
          .equalsIgnoreCase(user.getRole().toString());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return authorized;
  }
}
