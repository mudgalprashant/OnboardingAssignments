package application;

import constants.Constant;
import entities.Item;
import enums.Category;
import java.util.ArrayList;
import services.Taxation;
import services.Validator;

// Handle console input
public class ConsoleInput {

    // Add items in itemList from console input
    public static ArrayList<Item> inputItems() {

        // itemList to store input items
        ArrayList<Item> itemList = new ArrayList<>();

        String repeat;
        int counter = Constant.COUNTER;

        // Input all items
        do {
            Item item = new Item();

            // Input one item
            inputName(item, counter);
            inputPrice(item, counter);
            inputCategory(item, counter);
            inputQuantity(item, counter);

            // Add tax to item
            Taxation.calculateTax(item);

            // Add item to list
            itemList.add(item);

            // Check for another item
            System.out.print(Constant.REPEAT_INPUT_TEXT);
            repeat = Constant.SCANNER.nextLine();
            counter++;

        } while (repeat.equalsIgnoreCase(Constant.YES));

        Constant.SCANNER.close();
        return itemList;
    }

    // Input valid name
    private static void inputName(Item item, int counter){

        // Input name
        System.out.printf(Constant.NAME_INPUT_TEXT, counter);
        String name = Constant.SCANNER.nextLine();

        // Validate name
        Exception exception;
        exception = Validator.validateName(name);
        if (exception == null){
            item.setName(name);
        }else{
            System.out.println(exception);
            inputName(item, counter);
        }
        Constant.SCANNER.close();
    }

    // Input valid price
    private static void inputPrice(Item item, int counter){

        // Input price
        System.out.printf(Constant.PRICE_INPUT_TEXT, counter);
        String price = Constant.SCANNER.nextLine();

        // Validate price
        Exception exception;
        exception = Validator.validatePrice(price);
        if (exception == null){
            item.setPrice(Double.parseDouble(price));
        }else{
            System.out.println(exception);
            inputPrice(item, counter);
        }
        Constant.SCANNER.close();
    }

    // Input valid category
    private static void inputCategory(Item item, int counter){

        // Input category
        System.out.printf(Constant.CATEGORY_INPUT_TEXT, counter);
        String category = Constant.SCANNER.nextLine();

        // Validate category
        Exception exception;
        exception = Validator.validateCategory(category);
        if (exception == null){
            item.setCategory(Category.valueOf(category.toUpperCase()));
        }else{
            System.out.println(exception);
            inputCategory(item, counter);
        }
        Constant.SCANNER.close();
    }

    // Input valid quantity
    private static void inputQuantity(Item item, int counter){

        // Input quantity
        System.out.printf(Constant.QUANTITY_INPUT_TEXT, counter);
        String quantity = Constant.SCANNER.nextLine();

        // Validate quantity
        Exception exception;
        exception = Validator.validateQuantity(quantity);
        if (exception == null){
            item.setQuantity(Integer.parseInt(quantity));
        }else{
            System.out.println(exception);
            inputQuantity(item, counter);
        }
        Constant.SCANNER.close();
    }

}
