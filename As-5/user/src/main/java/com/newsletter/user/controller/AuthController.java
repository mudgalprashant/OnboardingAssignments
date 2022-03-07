package com.newsletter.user.controller;

import com.newsletter.user.config.JwtTokenUtil;
import com.newsletter.user.constants.Constant;
import com.newsletter.user.dto.ApiResponseDto;
import com.newsletter.user.models.JwtRequest;
import com.newsletter.user.models.JwtResponse;
import com.newsletter.user.models.User;
import com.newsletter.user.services.AuthServiceImpl;
import com.newsletter.user.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * The type User login controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private final UserDetailsServiceImpl userService;

  @Autowired
  private final JwtTokenUtil jwtTokenUtil;

  @Autowired
  private final AuthServiceImpl authService;

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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

  /**
   * Authorize subscriber response entity.
   *
   * @param token the token
   * @param id    the id
   * @return the response entity
   */
  @GetMapping("/authorize/subscriber/{id}")
  ResponseEntity<ApiResponseDto> authorizeSubscriber(
      @RequestHeader("Authorization") String token,
      @PathVariable Long id) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();
    if (authService.isSubscriberAuthorized(userService.getUserById(id), token)) {
      apiResponseDto.setStatus(Constant.ACCEPTED);
    } else {
      apiResponseDto.setStatus(Constant.DENIED);
    }
    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Authorize publisher response entity.
   *
   * @param token the token
   * @param id    the id
   * @return the response entity
   */
  @GetMapping("/authorize/publisher/{id}")
  ResponseEntity<Object> authorizePublisher(
      @RequestHeader("Authorization") String token,
      @PathVariable Long id) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();
    //User user = userService.getUserById(id);
    if (authService.isPublisherAuthorized(userService.getUserById(id), token)) {
      apiResponseDto.setStatus(Constant.ACCEPTED);
    } else {
      apiResponseDto.setStatus(Constant.DENIED);
    }
    return ResponseEntity.accepted().body(apiResponseDto);
  }

  /**
   * Authorize user response entity.
   *
   * @param token the token
   * @param id    the id
   * @return the response entity
   */
  @GetMapping("/authorize/user/{id}")
  ResponseEntity<Object> authorizeUser(
      @RequestHeader("Authorization") String token,
      @PathVariable Long id) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();
    //User user = userService.getUserById(id);
    if (authService.isUserAuthorized(userService.getUserById(id), token)) {
      apiResponseDto.setStatus(Constant.ACCEPTED);
    } else {
      apiResponseDto.setStatus(Constant.DENIED);
    }
    return ResponseEntity.accepted().body(apiResponseDto);
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
