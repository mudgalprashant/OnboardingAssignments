package application;

import java.util.*;
import entities.*;
import enums.*;
import org.junit.jupiter.api.Test;
import services.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleOutputTest {

    @Test
    void printTaxInfo() {

        // List of test items
        ArrayList<Item> itemList = new ArrayList<>();

        // First item of list
        Item item1 = new Item();
        item1.setName("Iron");
        item1.setPrice(1000);
        item1.setCategory(Category.IMPORTED);
        item1.setQuantity(20);
        Taxation.calculateTax(item1);
        itemList.add(item1);

        // second item of list
        Item item2 = new Item();
        item2.setName("Steel");
        item2.setPrice(2000);
        item2.setCategory(Category.IMPORTED);
        item2.setQuantity(10);
        Taxation.calculateTax(item2);
        itemList.add(item2);

        assertEquals(0, ConsoleOutput.printTaxInfo(itemList));

    }
}