package com.example.redis.services;

import com.example.redis.exceptions.IdNotFoundException;
import com.example.redis.models.Item;

import java.util.List;

/**
 * The interface Item service.
 */
public interface ItemService {

  /**
   * Create item item.
   *
   * @param item the item
   * @return the item
   */
  Item createItem(Item item);

  /**
   * Gets all items.
   *
   * @return the all items
   */
  List<Item> getAllItems();

  /**
   * Gets item by id.
   *
   * @param id the id
   * @return the item by id
   * @throws IdNotFoundException the id not found exception
   */
  Item getItemById(Integer id) throws IdNotFoundException;

  /**
   * Update item item.
   *
   * @param item the item
   * @param id   the id
   * @return the item
   */
  Item updateItem(Item item, Integer id);

  /**
   * Delete item.
   *
   * @param id the id
   */
  void deleteItem(Integer id);

}
