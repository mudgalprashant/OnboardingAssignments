package com.newsletter.user.mapper;

import com.newsletter.user.dto.CreateUserRequestDto;
import com.newsletter.user.dto.UserResponseDto;
import com.newsletter.user.models.User;
import org.mapstruct.Mapper;

/**
 * The interface User mapper.
 */
@Mapper
public interface UserMapper {


  /**
   * Request dto to user.
   *
   * @param createUserRequestDto the user request dto
   * @return the user
   */
  User RequestDtoToUser(CreateUserRequestDto createUserRequestDto);

  /**
   * User to User Response Dto.
   *
   * @param user the user
   * @return the user dto
   */
  UserResponseDto userToResponseDto(User user);

}
