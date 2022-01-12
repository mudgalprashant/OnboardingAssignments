package application;

import constants.Constant;
import enums.Category;
import models.Item;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.Taxation;
import services.Validator;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Takes input from console
 */
public class ConsoleInput {

    private static final Log log = LogFactory.getLog(ConsoleInput.class);
    /**
     * Add items in the itemList from console input
     * @return List of items
     */
    public ArrayList<Item> inputItems() {

        //itemList to store input items
        ArrayList<Item> itemList = new ArrayList<>();

        String repeat;
        int counter = Constant.COUNTER;
        final Taxation taxation = new Taxation();

        //Input all items
        do {
            Item item = Item.builder().build();

            //Input one item
            inputName(item, counter);
            inputPrice(item, counter);
            inputCategory(item, counter);
            inputQuantity(item, counter);

            //Add tax to item
            taxation.calculateTax(item);

            //Add item to list
            itemList.add(item);

            //Check for another item
            log.info(Constant.REPEAT_INPUT_TEXT);
            repeat = Constant.SCANNER.nextLine();
            counter++;

        } while (repeat.equalsIgnoreCase(Constant.YES));

        Constant.SCANNER.close();
        return itemList;
    }


    /**
     * Input valid name from console
     *
     * @param item : Item to take input into
     * @param counter : # of item
     */
    private void inputName(Item item, int counter) {

        // Input name
        log.info(String.format(Constant.NAME_INPUT_TEXT, counter));
        final String name = Constant.SCANNER.nextLine();

        // Validate name
        final Validator validator = new Validator();
        final Exception exception = validator.validateCategory(name);
        if (Objects.isNull(exception)) {
            item.setName(name);
        } else {
            log.error(exception);
            inputName(item, counter);
        }
        Constant.SCANNER.close();
    }

    /**
     * Input valid price from console
     *
     * @param item : Item to take input into
     * @param counter : # of item
     */
    private void inputPrice(Item item, int counter) {

        // Input price
        log.info(String.format(Constant.PRICE_INPUT_TEXT, counter));
        final String price = Constant.SCANNER.nextLine();

        // Validate price
        final Validator validator = new Validator();
        final Exception exception = validator.validateCategory(price);
        if (Objects.isNull(exception)){
            item.setPrice(Double.parseDouble(price));
        } else {
            log.error(exception);
            inputPrice(item, counter);
        }
        Constant.SCANNER.close();
    }

    /**
     * Input valid category from console
     *
     * @param item : Item to take input into
     * @param counter : # of item
     */
    private void inputCategory(Item item, int counter) {

        // Input category
        log.info(String.format(Constant.CATEGORY_INPUT_TEXT, counter));
        final String category = Constant.SCANNER.nextLine();

        // Validate category
        final Validator validator = new Validator();
        final Exception exception = validator.validateCategory(category);
        if (Objects.isNull(exception)){
            item.setCategory(Category.valueOf(category.toUpperCase()));
        } else {
            log.error(exception);
            inputCategory(item, counter);
        }
        Constant.SCANNER.close();
    }

    /**
     * Input valid quantity from console
     *
     * @param item : Item to take input into
     * @param counter : # of item
     */
    private void inputQuantity(Item item, int counter){

        // Input quantity
        log.info(String.format(Constant.QUANTITY_INPUT_TEXT, counter));
        final String quantity = Constant.SCANNER.nextLine();

        // Validate quantity
        final Validator validator = new Validator();
        final Exception exception = validator.validateQuantity(quantity);
        if (Objects.isNull(exception)){
            item.setQuantity(Integer.parseInt(quantity));
        }else{
            log.error(exception);
            inputQuantity(item, counter);
        }
        Constant.SCANNER.close();
    }

}
