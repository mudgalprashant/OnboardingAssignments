package services;

import entities.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import enums.*;

// Test Taxation service
class TaxationTest {

    @Test
    void calculateTax() {

        // Raw category tax calculation test
        Item item = new Item();
        item.setName("Iron");
        item.setPrice(1000);
        item.setCategory(Category.RAW);
        item.setQuantity(10);
        Taxation.calculateTax(item);
        assertEquals(125, item.getTax());

        // Manufactured category tax calculation test
        item.setName("Iron");
        item.setPrice(1000);
        item.setCategory(Category.MANUFACTURED);
        item.setQuantity(10);
        Taxation.calculateTax(item);
        assertEquals(147.5, item.getTax());

        // Imported category tax calculation test
        // Case 1: (Price + Import Duty) is not more than 100 rupees
        item.setName("Iron");
        item.setPrice(10);
        item.setCategory(Category.IMPORTED);
        item.setQuantity(10);
        Taxation.calculateTax(item);
        assertEquals(6, item.getTax());

        // Imported category tax calculation test
        // Case 2: (Price + Import Duty) is more than 100 rupees but not more than 200 rupees
        item.setName("Iron");
        item.setPrice(100);
        item.setCategory(Category.IMPORTED);
        item.setQuantity(10);
        Taxation.calculateTax(item);
        assertEquals(20, item.getTax());

        // Imported category tax calculation test
        // Case 3: (Price + Import Duty) is more than 200 rupees
        item.setName("Iron");
        item.setPrice(1000);
        item.setCategory(Category.IMPORTED);
        item.setQuantity(10);
        Taxation.calculateTax(item);
        assertEquals(155, item.getTax());

    }
}
