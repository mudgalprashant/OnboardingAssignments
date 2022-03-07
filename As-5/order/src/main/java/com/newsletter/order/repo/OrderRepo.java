package com.newsletter.order.repo;

import com.newsletter.order.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Order repo.
 */
@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
}
