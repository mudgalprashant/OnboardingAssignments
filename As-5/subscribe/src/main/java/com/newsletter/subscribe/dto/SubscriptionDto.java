package com.newsletter.subscribe.dto;

import com.newsletter.subscribe.enums.Category;
import lombok.Data;

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
