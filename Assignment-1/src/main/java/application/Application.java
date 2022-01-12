package application;

import models.Item;
import java.util.ArrayList;

/**
 * Root class of the project
 */
public class Application {
    public static void main(String[] args) {

        //Input to list of items
        final ConsoleInput consoleInput = new ConsoleInput();
        final ArrayList<Item> itemList = consoleInput.inputItems();

        //List of items to Output
        final ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.printTaxInfo(itemList);

    }
}

