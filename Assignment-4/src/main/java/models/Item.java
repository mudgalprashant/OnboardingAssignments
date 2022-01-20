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

    private String name;
    private Category category;
    private double price;
    private double tax;
    private int quantity;
}
