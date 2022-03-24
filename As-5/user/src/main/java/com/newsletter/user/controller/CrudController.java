package com.newsletter.user.controller;

import com.newsletter.user.config.JwtTokenUtil;
import com.newsletter.user.constants.Constant;
import com.newsletter.user.constants.MessageConstant;
import com.newsletter.user.constants.PathConstant;
import com.newsletter.user.dto.ApiResponseDto;
import com.newsletter.user.dto.UserRequestDto;
import com.newsletter.user.dto.UserResponseDto;
import com.newsletter.user.mapper.UserMapper;
import com.newsletter.user.services.AuthServiceImpl;
import com.newsletter.user.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * The type User crud controller.
 */
@AllArgsConstructor
@RequestMapping(PathConstant.CRUD_MAPPING)
@RestController
public class CrudController {

  @Autowired
  private final UserDetailsServiceImpl userService;

  @Autowired
  private final UserMapper userMapper;

  @Autowired
  private final AuthServiceImpl authService;

  @Autowired
  private final JwtTokenUtil jwtTokenUtil;


  /**
   * Create user response entity.
   *
   * @param userRequestDto the user request dto
   * @return the response entity
   */
  @PostMapping
  ResponseEntity<ApiResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {
      UserResponseDto userResponseDto =
          userMapper.userToResponseDto(
              userService.createUser(
                  userMapper.requestDtoToUser(userRequestDto)
              )
          );
      apiResponseDto.setBody(userResponseDto);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);


    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_CREATION_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);

  }

  /**
   * Gets all users.
   *
   * @return the all users
   */
  @GetMapping
  ResponseEntity<ApiResponseDto> getAllUsers() {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {

      List<UserResponseDto> userList = userService
          .getAllUser()
          .stream()
          .map(userMapper::userToResponseDto)
          .collect(Collectors.toList());
      apiResponseDto.setBody(userList);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);


    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_LIST_FETCH_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   */
  @GetMapping(PathConstant.BY_ID_MAPPING)
  ResponseEntity<ApiResponseDto> getUserById(@PathVariable Long id) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {
      UserResponseDto userResponseDto =
          userMapper.userToResponseDto(userService.getUserById(id));

      apiResponseDto.setBody(userResponseDto);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);

    } catch (NoSuchElementException exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_NOT_FOUND);

    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_FETCH_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }


  /**
   * Gets user by email.
   *
   * @param email the email
   * @return the user by email
   */
  @GetMapping(PathConstant.BY_EMAIL_MAPPING)
  ResponseEntity<ApiResponseDto> getUserByEmail(@PathVariable String email) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try {
      UserResponseDto userResponseDto =
          userMapper.userToResponseDto(userService.getUserByEmail(email));

      apiResponseDto.setBody(userResponseDto);
      apiResponseDto.setStatus(MessageConstant.ACCEPTED);
      apiResponseDto.setMessage(MessageConstant.SUCCESS);

    } catch (NoSuchElementException exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_NOT_FOUND);

    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_FETCH_FAILED);
    }

    return ResponseEntity.ok(apiResponseDto);
  }

  /**
   * Gets user by token.
   *
   * @param bearer the bearer
   * @return the user by token
   */
  @GetMapping(PathConstant.BY_TOKEN_MAPPING)
  ResponseEntity<ApiResponseDto> getUserByToken(
      @RequestHeader(Constant.SECURITY_HEADER) String bearer) {
    ApiResponseDto apiResponseDto = new ApiResponseDto();

    String email = jwtTokenUtil.getEmailFromToken(bearer.substring(7));
    return getUserByEmail(email);

  }

  /**
   * Update user response entity.
   *
   * @param bearer         the bearer
   * @param userRequestDto the user request dto
   * @return the response entity
   */
  @PutMapping
  ResponseEntity<ApiResponseDto> updateUser(
      @RequestHeader(Constant.SECURITY_HEADER) String bearer,
      @RequestBody UserRequestDto userRequestDto) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    // Authorize user and update
      try{
        if (authService.isUserAuthorized(bearer)) {

          UserResponseDto userResponseDto =
              userMapper.userToResponseDto(
                  userService.updateUser(
                      userMapper.requestDtoToUser(userRequestDto),
                      userService
                          .getUserByEmail(jwtTokenUtil.getEmailFromToken(bearer.substring(7)))
                          .getId()
                  )
              );

          apiResponseDto.setBody(userResponseDto);
          apiResponseDto.setStatus(MessageConstant.ACCEPTED);
          apiResponseDto.setMessage(MessageConstant.SUCCESS);
        } else {
          apiResponseDto.setStatus(MessageConstant.DENIED);
          apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED_ACCESS);
        }

      } catch (NoSuchElementException exception) {
        exception.printStackTrace();

        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.USER_NOT_FOUND);

      } catch (Exception exception) {
        exception.printStackTrace();

        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.USER_UPDATE_FAILED);
      }


    return ResponseEntity.ok(apiResponseDto);

  }

  /**
   * Delete user.
   *
   * @param bearer the bearer
   * @return the response entity
   */
  @DeleteMapping
  ResponseEntity<ApiResponseDto> deleteUser(
      @RequestHeader(Constant.SECURITY_HEADER) String bearer) {

    ApiResponseDto apiResponseDto = new ApiResponseDto();

    try{
      // Authorize user and delete

      if (authService.isUserAuthorized(bearer)) {
        userService.deleteUser(
            userService
                .getUserByEmail(jwtTokenUtil.getEmailFromToken(bearer.substring(7)))
                .getId()
        );
        apiResponseDto.setStatus(MessageConstant.ACCEPTED);
        apiResponseDto.setMessage(MessageConstant.SUCCESS);

      } else {
        apiResponseDto.setStatus(MessageConstant.DENIED);
        apiResponseDto.setMessage(MessageConstant.UNAUTHORIZED_ACCESS);
      }

    } catch (NoSuchElementException exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_NOT_FOUND);

    } catch (Exception exception) {
      exception.printStackTrace();

      apiResponseDto.setStatus(MessageConstant.DENIED);
      apiResponseDto.setMessage(MessageConstant.USER_DELETION_FAILED);
    }


    return ResponseEntity.ok(apiResponseDto);

  }


}
