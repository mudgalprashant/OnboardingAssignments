package com.newsletter.email.dto;

import lombok.Data;

import java.util.List;

/**
 * The type Email dto.
 */
@Data
public class EmailDto {
  private String sender;
  private String subject;
  private String content;
}
