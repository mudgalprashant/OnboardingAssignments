package models;

import enums.Category;
import lombok.Builder;
import lombok.Data;

/**
 * The type Item.
 */
@Builder
@Data
public class Item {

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
     * Tax per item.
     */
    private double tax;
    /**
     * Quantity of the item.
     */
    private int quantity;
}
