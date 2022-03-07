package com.newsletter.subscribe.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * The type Sub response dto.
 */
@Data
public class SubResponseDto {
  private Long id;
  private Long subscriberId;
  private String subscriptionId;
  private Timestamp startTimestamp;
  private Timestamp endTimestamp;
}
