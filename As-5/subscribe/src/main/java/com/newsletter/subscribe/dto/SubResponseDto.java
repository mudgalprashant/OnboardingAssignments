package com.newsletter.subscribe.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SubResponseDto {
  private Long id;
  private Long subscriberId;
  private String subscriptionId;
  private Timestamp startTimestamp;
  private Timestamp endTimestamp;
}
