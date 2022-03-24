package com.newsletter.subscribe.mapper;

import com.newsletter.subscribe.dto.SubRequestDto;
import com.newsletter.subscribe.dto.SubResponseDto;
import com.newsletter.subscribe.models.Sub;
import org.mapstruct.Mapper;

/**
 * The interface Sub mapper.
 */
@Mapper
public interface SubMapper {

  /**
   * Request dto to sub sub.
   *
   * @param subRequestDto the sub request dto
   * @return the sub
   */
  Sub requestDtoToSub(SubRequestDto subRequestDto);

  /**
   * Sub to response dto sub response dto.
   *
   * @param sub the sub
   * @return the sub response dto
   */
  SubResponseDto subToResponseDto(Sub sub);

}
