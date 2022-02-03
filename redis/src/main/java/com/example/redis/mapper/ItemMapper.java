package com.example.redis.mapper;

import com.example.redis.models.Item;
import com.example.redis.models.ItemDto;
import org.mapstruct.Mapper;

/**
 * The interface Item mapper.
 */
@Mapper
public interface ItemMapper {

  /**
   * To item dto item dto.
   *
   * @param item the item
   * @return the item dto
   */
  ItemDto toItemDto(Item item);

  /**
   * To item item.
   *
   * @param itemDto the item dto
   * @return the item
   */
  Item toItem(ItemDto itemDto);
}
