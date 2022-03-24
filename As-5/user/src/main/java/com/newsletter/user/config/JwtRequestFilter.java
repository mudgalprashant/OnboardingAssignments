package com.newsletter.user.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newsletter.user.constants.Constant;
import com.newsletter.user.constants.MessageConstant;
import com.newsletter.user.models.User;
import com.newsletter.user.services.UserDetailsServiceImpl;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;

/**
 * The type Jwt request filter.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsServiceImpl userService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    final String requestTokenHeader = request.getHeader(Constant.SECURITY_HEADER);

    String email = null;
    String jwtToken = null;

    // JWT Token is in the form "Bearer token". Remove Bearer word and get
    // only the Token
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        email = jwtTokenUtil.getEmailFromToken(jwtToken);
      } catch (IllegalArgumentException exception) {
        logger.error(MessageConstant.JWT_FETCH_FAILED);
      } catch (ExpiredJwtException exception) {
        logger.error(MessageConstant.JWT_EXPIRED);
      } catch(Exception exception) {
        logger.error(MessageConstant.INVALID_JWT_TOKEN);
      }
    } else {
      logger.warn(MessageConstant.JWT_WITHOUT_BEARER);
    }

    // Once we get the token validate it.
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      User user = this.userService.getUserByEmail(email);

      // if token is valid configure Spring Security to manually set
      // authentication
      if (jwtTokenUtil.validateToken(jwtToken, user)) {

        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("email", user.getEmail());
        credentials.put("password", user.getPassword());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
            = new UsernamePasswordAuthenticationToken(
            user, credentials, List.of(new SimpleGrantedAuthority(user.getRole().toString())));
        usernamePasswordAuthenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }
}
