package com.newsletter.order.services;

import com.newsletter.order.constants.Constant;
import com.newsletter.order.models.Order;
import com.newsletter.order.repo.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * The type Order service.
 */
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private final OrderRepo orderRepo;

  @Override
  @CachePut(value = Constant.CACHE_VALUE)
  public Order subscribe(Order order) {
    return orderRepo.save(order);
  }

  @Override
  @CachePut(value = Constant.CACHE_VALUE)
  public Order renew(Order order) {
    return orderRepo.save(order);
  }

  @Override
  @CacheEvict(value = Constant.CACHE_VALUE)
  public void unSubscribe(Long id) {
    orderRepo.deleteById(id);
  }
}
