package com.newsletter.order.dto;

import com.newsletter.order.enums.OrderStatus;
import com.newsletter.order.enums.PaymentStatus;
import lombok.Data;

import java.sql.Timestamp;

/**
 * The type Order response dto.
 */
@Data
public class OrderResponseDto {
  private Long id;
  private String subscriberId;
  private String subscriptionId;
  private PaymentStatus paymentStatus;
  private OrderStatus orderStatus;
  private Timestamp createdAt;

}
