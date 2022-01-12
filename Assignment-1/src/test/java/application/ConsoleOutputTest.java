package application;


import models.Item;
import enums.Category;
import org.junit.jupiter.api.Test;
import services.Taxation;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleOutputTest {

    @Test
    void printTaxInfo() {

        Taxation taxation = new Taxation();

        /**
         * List of test items
         */
        ArrayList<Item> itemList = new ArrayList<>();

        /**
         * First item of the list
         */
        Item item1 = Item.builder().build();
        item1.setName("Iron");
        item1.setPrice(1000);
        item1.setCategory(Category.IMPORTED);
        item1.setQuantity(20);
        taxation.calculateTax(item1);
        itemList.add(item1);

        /**
         * Second Item of the list
         */
        Item item2 = Item.builder().build();
        item2.setName("Steel");
        item2.setPrice(2000);
        item2.setCategory(Category.IMPORTED);
        item2.setQuantity(10);
        taxation.calculateTax(item2);
        itemList.add(item2);
        ConsoleOutput consoleOutput = new ConsoleOutput();
        assertEquals(0, consoleOutput.printTaxInfo(itemList));

    }
}