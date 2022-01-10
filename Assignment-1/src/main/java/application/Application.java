package application;

import entities.Item;
import java.util.ArrayList;

// Application is Root class of the project
public class Application {
    public static void main(String[] args) {

        // Input to list
        ArrayList<Item> itemList = ConsoleInput.inputItems();

        // List to output with tax
        ConsoleOutput.printTaxInfo(itemList);

    }
}

