package com.newsletter.subscribe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The type Sub.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sub {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private Long subscriberId;

  @Column(nullable = false)
  private String subscriptionId;

  @CreationTimestamp
  private Timestamp startTimestamp;

  private Timestamp endTimestamp;
}
