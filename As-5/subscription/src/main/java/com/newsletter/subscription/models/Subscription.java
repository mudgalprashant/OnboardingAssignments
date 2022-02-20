package com.newsletter.subscription.models;

import com.newsletter.subscription.enums.Category;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

@AllArgsConstructor
@Document(indexName = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
public class Subscription {

  @Id
  private String id;

  private String name;

  private String email;

  @Enumerated
  private Category category;

  private Double price;

  private Boolean renewable;

  // Duration in seconds
  private Long duration;
}
