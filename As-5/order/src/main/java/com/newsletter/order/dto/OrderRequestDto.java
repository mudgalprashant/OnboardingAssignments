package com.newsletter.order.dto;

import com.newsletter.order.enums.PaymentStatus;
import lombok.Data;

/**
 * The type Order request dto.
 */
@Data
public class OrderRequestDto {
  private String subscriberId;
  private String subscriptionId;
  private PaymentStatus paymentStatus;
}
