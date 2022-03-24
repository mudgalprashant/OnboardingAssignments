package com.newsletter.subscribe.dto;

import com.newsletter.subscribe.enums.Category;
import lombok.Data;

/**
 * The type Subscription dto.
 */
@Data
public class SubscriptionDto {
  private String id;
  private String name;
  private String email;
  private Long publisherId;
  private Category category;
  private Double price;
  private Boolean renewable;
  private Long duration;
}
