package com.newsletter.user.controller;

import com.newsletter.user.config.JwtTokenUtil;
import com.newsletter.user.models.JwtRequest;
import com.newsletter.user.models.JwtResponse;
import com.newsletter.user.models.User;
import com.newsletter.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User login controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserLoginController {

  @Autowired
  private final UserService userService;

  @Autowired
  private final JwtTokenUtil jwtTokenUtil;

  private final AuthenticationManager authenticationManager;

  /**
   * Create auth token response entity.
   *
   * @param authRequest the auth request
   * @return the response entity
   * @throws Exception the exception
   */
  @PostMapping("/login")
  @CachePut(value = "users")
  ResponseEntity<JwtResponse> createAuthToken (@RequestBody JwtRequest authRequest) throws Exception {
    authenticate(authRequest.getEmail(), authRequest.getPassword());

    final User user = userService
        .getUserByEmail(authRequest.getEmail());

    final String token = jwtTokenUtil.generateToken(user);

    return ResponseEntity.ok(new JwtResponse(token));
  }


  private void authenticate(String email, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (DisabledException exception) {
      throw new Exception("USER_DISABLED", exception);
    } catch (BadCredentialsException exception) {
      throw new Exception("INVALID_CREDENTIALS", exception);
    }
  }

}
