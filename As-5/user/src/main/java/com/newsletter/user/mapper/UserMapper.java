package com.newsletter.user.mapper;

import com.newsletter.user.dto.UserRequestDto;
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
   * @param userRequestDto the user request dto
   * @return the user
   */
  User RequestDtoToUser(UserRequestDto userRequestDto);

  /**
   * User to User Response Dto.
   *
   * @param user the user
   * @return the user dto
   */

  UserResponseDto userToResponseDto(User user);

}
