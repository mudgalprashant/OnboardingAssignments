package com.newsletter.subscribe.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SubRequestDto {
  private Long subscriberId;
  private String subscriptionId;
  private Timestamp startTimestamp;
  private Timestamp endTimestamp;
}
