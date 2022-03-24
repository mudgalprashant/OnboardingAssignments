package com.newsletter.user.services;

import com.fasterxml.jackson.databind.ObjectMapper;
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

  @Autowired
  private UserDetailsServiceImpl userDetailsService;


  @Override
  public boolean isSubscriberAuthorized(String bearer) {
    boolean authorized = false;
    try {

      String email = jwtTokenUtil.getEmailFromToken(bearer.substring(7));
      User user = userDetailsService.getUserByEmail(email);

      authorized = user.getRole() == Role.ROLE_SUBSCRIBER;
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return authorized;
  }

  @Override
  public boolean isPublisherAuthorized(String bearer) {
    boolean authorized = false;
    try {
      String email = jwtTokenUtil.getEmailFromToken(bearer.substring(7));
      User user = userDetailsService.getUserByEmail(email);
      authorized = user.getRole() == Role.ROLE_PUBLISHER;
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return authorized;
  }

  @Override
  public boolean isUserAuthorized(String bearer) {
    boolean authorized;
    try {
      jwtTokenUtil.getEmailFromToken(bearer.substring(7));
      authorized = true;
    } catch (Exception exception) {
      exception.printStackTrace();
      authorized = false;
    }
    return authorized;
  }
}
