package com.newsletter.subscribe.mapper;

import com.newsletter.subscribe.dto.SubRequestDto;
import com.newsletter.subscribe.dto.SubResponseDto;
import com.newsletter.subscribe.models.Sub;
import org.mapstruct.Mapper;

@Mapper
public interface SubMapper {

  Sub requestDtoToSub(SubRequestDto subRequestDto);

  SubResponseDto subToResponseDto(Sub sub);

}
