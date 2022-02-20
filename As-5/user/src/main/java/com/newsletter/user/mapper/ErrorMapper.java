package com.newsletter.user.mapper;

import com.newsletter.user.dto.ErrorResponseDto;
import com.newsletter.user.exceptions.ErrorResponse;
import org.mapstruct.Mapper;

/**
 * The interface Error mapper.
 */
@Mapper
public interface ErrorMapper {
  /**
   * Error response to dto error response dto.
   *
   * @param errorResponse the error response
   * @return the error response dto
   */
  ErrorResponseDto errorResponseToDto (ErrorResponse errorResponse);
}
