package com.newsletter.order.services;

import com.newsletter.order.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

  Order subscribe(Order order);

  Order renew(Order order);

  void unSubscribe(Long id);

}
