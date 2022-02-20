package com.newsletter.user.controller;

import com.newsletter.user.dto.UserRequestDto;
import com.newsletter.user.dto.UserResponseDto;
import com.newsletter.user.mapper.UserMapper;
import com.newsletter.user.models.User;
import com.newsletter.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User crud controller.
 */
@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class UserCrudController {

  @Autowired
  private final UserService userService;

  private final UserMapper userMapper;


  /**
   * Create user response entity.
   *
   * @param userRequestDto the user dto
   * @return the response entity
   */
  @PostMapping
  @CachePut(value = "users")
  ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {

    return ResponseEntity.ok(
        userMapper
            .userToResponseDto(
                userService
                    .createUser(
                    userMapper
                        .RequestDtoToUser(userRequestDto)
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
  @Cacheable(value = "users")
  ResponseEntity<List<UserResponseDto>> getAllUsers() {
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
  @Cacheable(value = "users", key = "#id")
  ResponseEntity<UserResponseDto> getUserById(@PathVariable long id) {
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
  @Cacheable(value = "users", key = "#email")
  ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
    return ResponseEntity.ok(
        userMapper.userToResponseDto(userService.getUserByEmail(email))
    );
  }

  /**
   * Update user response entity.
   *
   * @param userRequestDto the user dto
   * @param id             the id
   * @return the response entity
   */
  @PutMapping("/{id}")
  @Cacheable(value = "users", key = "#id")
  ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable long id) {
    User user = userMapper.RequestDtoToUser(userRequestDto);
    user = userService.updateUser(user, id);
    return ResponseEntity.ok(
        userMapper.userToResponseDto(user)
    );
  }

  /**
   * Delete user.
   *
   * @param id the id
   */
  @DeleteMapping("/{id}")
  @CacheEvict(value = "users")
  void deleteUser(@PathVariable long id) {
    userService.deleteUser(id);
  }


}
