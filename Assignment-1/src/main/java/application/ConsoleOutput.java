package application;
import java.util.*;

import constants.*;
import models.*;

/**
 * Controls output on Console
 */
public class ConsoleOutput {

    /**
     * Prints tax info of all items
     *
     * @param itemList : list of items
     * @return 0 to confirm proper working
     */
    public int printTaxInfo( ArrayList<Item> itemList ){
        int counter = Constant.COUNTER;
        for (Item item: itemList){
            System.out.printf(Constant.INFO_OUTPUT_TEXT,counter++);
            System.out.printf(Constant.NAME_OUTPUT_TEXT, item.getName());
            System.out.printf(Constant.PRICE_PER_ITEM_OUTPUT_TEXT, item.getPrice());
            System.out.printf(Constant.TAX_PER_ITEM_OUTPUT_TEXT,item.getTax());
            System.out.printf(Constant.TOTAL_FINAL_PRICE_OUTPUT_TEXT, item.getQuantity(),(item.getPrice() + item.getTax())*item.getQuantity());
            System.out.println(Constant.DIVIDER);
        }
        return 0;
    }
}
