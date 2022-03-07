package com.newsletter.order.services;

import com.newsletter.order.models.Order;
import com.newsletter.order.repo.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private final OrderRepo orderRepo;

  @Override
  @CachePut(value = "orders")
  public Order subscribe(Order order) {
    return orderRepo.save(order);
  }

  @Override
  @CachePut(value = "orders")
  public Order renew(Order order) {
    return orderRepo.save(order);
  }

  @Override
  @CacheEvict(value = "orders")
  public void unSubscribe(Long id) {
    orderRepo.deleteById(id);
  }
}
