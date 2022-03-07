package com.newsletter.order.mapper;

import com.newsletter.order.dto.OrderRequestDto;
import com.newsletter.order.dto.OrderResponseDto;
import com.newsletter.order.models.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

  Order requestDtoToOrder(OrderRequestDto orderRequestDto);

  OrderResponseDto orderToResponseDto(Order order);

}
