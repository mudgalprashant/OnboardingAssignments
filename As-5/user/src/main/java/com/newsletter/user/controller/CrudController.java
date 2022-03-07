package com.newsletter.user.controller;

import com.newsletter.user.dto.CreateUserRequestDto;
import com.newsletter.user.dto.UnauthorizedResponseDto;
import com.newsletter.user.mapper.UserMapper;
import com.newsletter.user.models.User;
import com.newsletter.user.services.AuthServiceImpl;
import com.newsletter.user.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * The type User crud controller.
 */
@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class CrudController {

  @Autowired
  private final UserDetailsServiceImpl userService;

  @Autowired
  private final UserMapper userMapper;

  @Autowired
  private final AuthServiceImpl authService;


  /**
   * Create user response entity.
   *
   * @param createUserRequestDto the user request dto
   * @return the response entity
   */
  @PostMapping
  ResponseEntity<Object> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
    return ResponseEntity.ok(
        userMapper
            .userToResponseDto(
                userService
                    .createUser(
                    userMapper
                        .RequestDtoToUser(createUserRequestDto)
                )
            )
    );
  }

  /**
   * Gets all users.
   *
   * @return the all users
   */
  @GetMapping
  ResponseEntity<Object> getAllUsers() {
    return ResponseEntity.ok(
        userService
            .getAllUser()
            .stream()
            .map(userMapper::userToResponseDto)
            .collect(Collectors.toList())
    );
  }

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   */
  @GetMapping("/{id}")
  ResponseEntity<Object> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(
        userMapper.userToResponseDto(userService.getUserById(id))
    );
  }


  /**
   * Gets user by email.
   *
   * @param email the email
   * @return the user by email
   */
  @GetMapping("/email/{email}")
  ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
    return ResponseEntity.ok(
        userMapper.userToResponseDto(userService.getUserByEmail(email))
    );
  }

  /**
   * Update user response entity.
   *
   * @param token                the token
   * @param createUserRequestDto the user request dto
   * @param id                   the id
   * @return the response entity
   */
  @PutMapping("/{id}")
  ResponseEntity<Object> updateUser(
      @RequestHeader("Authorization") String token,
      @RequestBody CreateUserRequestDto createUserRequestDto,
      @PathVariable Long id) {
    if (
        !authService.isUserAuthorized(
        userMapper.RequestDtoToUser(createUserRequestDto), token)
    ) {
      return ResponseEntity.status(401).body(new UnauthorizedResponseDto());
    }
    User user = userMapper.RequestDtoToUser(createUserRequestDto);
    user = userService.updateUser(user, id);
    return ResponseEntity.ok(
        userMapper.userToResponseDto(user)
    );
  }

  /**
   * Delete user.
   *
   * @param token the token
   * @param id    the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deleteUser(
      @RequestHeader("Authorization") String token,
      @PathVariable Long id) {
    if (!authService.isUserAuthorized(userService.getUserById(id), token)) {
      return ResponseEntity.status(401).body(new UnauthorizedResponseDto());
    }
    userService.deleteUser(id);
    return ResponseEntity.accepted().build();
  }


}
