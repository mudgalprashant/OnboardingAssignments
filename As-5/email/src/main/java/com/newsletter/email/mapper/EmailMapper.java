package com.newsletter.email.mapper;

import com.newsletter.email.dto.EmailDto;
import com.newsletter.email.models.Email;
import org.mapstruct.Mapper;

/**
 * The interface Email mapper.
 */
@Mapper(componentModel = "spring")
public interface EmailMapper {

  /**
   * Dto to email email.
   *
   * @param emailDto the email dto
   * @return the email
   */
  Email dtoToEmail(EmailDto emailDto);

  /**
   * Email to dto email dto.
   *
   * @param email the email
   * @return the email dto
   */
  EmailDto emailToDto(Email email);

}
