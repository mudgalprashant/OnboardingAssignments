package com.newsletter.email.dto;

import com.newsletter.email.enums.Category;
import lombok.Data;

/**
 * The type Subscription dto.
 */
@Data
public class SubscriptionDto {
  private String id;
  private String name;
  private String email;
  private Category category;
  private Double price;
  private Boolean renewable;
  private Long duration;
}
