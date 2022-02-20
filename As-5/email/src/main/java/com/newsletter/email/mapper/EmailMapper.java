package com.newsletter.email.mapper;

import com.newsletter.email.dto.EmailDto;
import com.newsletter.email.models.Email;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {

  Email dtoToEmail(EmailDto emailDto);

  EmailDto emailToDto(Email email);

}
