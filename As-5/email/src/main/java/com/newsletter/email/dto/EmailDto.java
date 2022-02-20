package com.newsletter.email.dto;

import lombok.Data;

@Data
public class EmailDto {
  private String receiver;
  private String subject;
  private String content;
}
