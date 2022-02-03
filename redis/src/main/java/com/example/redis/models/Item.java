package com.example.redis.models;

import com.example.redis.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Item.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "items")
public class Item implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
