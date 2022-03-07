package com.newsletter.order.controller;

import com.newsletter.order.constants.Constant;
import com.newsletter.order.dto.OrderRequestDto;
import com.newsletter.order.dto.OrderResponseDto;
import com.newsletter.order.mapper.OrderMapper;
import com.newsletter.order.models.Order;
import com.newsletter.order.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Order controller.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private final OrderService orderService;

  @Autowired
  private final OrderMapper orderMapper;

  /**
   * Place order response entity.
   *
   * @param orderRequestDto the order request dto
   * @return the response entity
   */
  @PostMapping(Constant.ORDERS_MAPPING)
  ResponseEntity<Object> placeOrder(@RequestBody OrderRequestDto orderRequestDto) { // todo: verify order on the basis of status
    Order order = orderMapper.requestDtoToOrder(orderRequestDto);
    return ResponseEntity.ok(order);
  }


}
