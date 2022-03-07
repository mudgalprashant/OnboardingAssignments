package com.newsletter.order.models;

import com.newsletter.order.enums.OrderStatus;
import com.newsletter.order.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private Long subscriberId;

  @Column(nullable = false)
  private Long subscriptionId;

  private PaymentStatus paymentStatus;

  private OrderStatus orderStatus;

  @Column(insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp createdAt;
}
