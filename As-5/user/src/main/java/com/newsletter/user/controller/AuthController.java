package com.newsletter.user.controller;

import com.newsletter.user.config.JwtTokenUtil;
import com.newsletter.user.constants.Constant;
import com.newsletter.user.constants.MessageConstant;
import com.newsletter.user.constants.PathConstant;
import com.newsletter.user.dto.ApiResponseDto;
import com.newsletter.user.models.JwtRequest;
import com.newsletter.user.models.JwtResponse;
import com.newsletter.user.models.User;
import com.newsletter.user.services.AuthServiceImpl;
import com.newsletter.user.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@AllArgsConstructor
@RestController
@RequestMapping(PathConstant.AUTH_MAPPING)
public class AuthController {

  @Autowired
  private final UserDetailsServiceImpl userService;

  @Autowired
  private final JwtTokenUtil jwtTokenUtil;

  @Autowired
  private final AuthServiceImpl authService;

  private final AuthenticationManager authenticationManager;

  /**
   * Create auth token response entity.
   *
   * @param authRequest the auth request
   * @return the response entity
   */
  @PostMapping(PathConstant.CREATE_AUTH_TOKEN_MAPPING)
  ResponseEntity<ApiResponseDto> createAuthToken (@RequestBody JwtRequest authRequest){

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    String token = Constant.EMPTY_STRING;

    try {
      // Authenticate user with email and password
      authenticate(authRequest.getEmail(), authRequest.getPassword());

      // Get user by email
      final User user = userService
          .getUserByEmail(authRequest.getEmail());

      // Generate token for user
      token = jwtTokenUtil.generateToken(user);

      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);

    } catch (Exception exception) {

      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.LOGIN_FAILED);
    }

    apiResponseDto.setBody(new JwtResponse(token));
    // Return response
    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Authorize subscriber response entity.
   *
   * @param bearer the bearer
   * @return the response entity
   */
  @GetMapping(PathConstant.AUTHORIZE_SUBSCRIBER_MAPPING)
  ResponseEntity<ApiResponseDto> authorizeSubscriber(
      @RequestHeader(Constant.SECURITY_HEADER) String bearer) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try{
      if(authService.isSubscriberAuthorized(bearer)) {
        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);
      }else {
        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.INVALID_JWT_TOKEN);
    }


    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Authorize publisher response entity.
   *
   * @param bearer the bearer
   * @return the response entity
   */
  @GetMapping(PathConstant.AUTHORIZE_PUBLISHER_MAPPING)
  ResponseEntity<ApiResponseDto> authorizePublisher(
      @RequestHeader(Constant.SECURITY_HEADER) String bearer) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try{
      if(authService.isPublisherAuthorized(bearer)) {
        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);
      }else {
        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }
    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.INVALID_JWT_TOKEN);
    }


    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Authorize user response entity.
   *
   * @param bearer the bearer
   * @return the response entity
   */
  @GetMapping(PathConstant.AUTHORIZE_USER_MAPPING)
  ResponseEntity<ApiResponseDto> authorizeUser(
      @RequestHeader(Constant.SECURITY_HEADER) String bearer) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      if(authService.isUserAuthorized(bearer)) {
        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);
      }else {
        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED);
      }
      } catch(Exception exception) {
      exception.printStackTrace();
      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.FAILED);
      }

    return ResponseEntity.ok(apiResponseDto);
  }

  private void authenticate(String email, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (DisabledException exception) {
      throw new Exception(MessageConstant.USER_DISABLED, exception);
    } catch (BadCredentialsException exception) {
      throw new Exception(MessageConstant.INVALID_CREDENTIALS, exception);
    }
  }

}
