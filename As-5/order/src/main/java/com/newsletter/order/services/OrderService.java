package com.newsletter.order.services;

import com.newsletter.order.models.Order;
import org.springframework.stereotype.Service;

/**
 * The interface Order service.
 */
@Service
public interface OrderService {

  /**
   * Subscribe order.
   *
   * @param order the order
   * @return the order
   */
  Order subscribe(Order order);

  /**
   * Renew order.
   *
   * @param order the order
   * @return the order
   */
  Order renew(Order order);

  /**
   * Un subscribe.
   *
   * @param id the id
   */
  void unSubscribe(Long id);

}
