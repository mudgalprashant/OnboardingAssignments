package com.example.redis.models;

import com.example.redis.enums.Category;
import lombok.Data;

/**
 * The type Item dto.
 */
@Data
public class ItemDto {
  /**
   * id of the item.
   */
  private int id;
  /**
   * Name of the item.
   */
  private String name;
  /**
   * Category of the item.
   */
  private Category category;
  /**
   * Price of the item.
   */
  private double price;
  /**
   * Quantity of the item.
   */
  private int quantity;
}
