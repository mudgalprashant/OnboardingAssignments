package com.newsletter.order.mapper;

import com.newsletter.order.dto.OrderRequestDto;
import com.newsletter.order.dto.OrderResponseDto;
import com.newsletter.order.models.Order;
import org.mapstruct.Mapper;

/**
 * The interface Order mapper.
 */
@Mapper
public interface OrderMapper {

  /**
   * Request dto to order.
   *
   * @param orderRequestDto the order request dto
   * @return the order
   */
  Order requestDtoToOrder(OrderRequestDto orderRequestDto);

  /**
   * Order to response dto order response dto.
   *
   * @param order the order
   * @return the order response dto
   */
  OrderResponseDto orderToResponseDto(Order order);

}
