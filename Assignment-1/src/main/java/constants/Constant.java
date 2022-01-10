package constants;

import java.util.Scanner;

public final class Constant {

    // Scanner for console input
    public static final Scanner SCANNER  = new Scanner(System.in);

    // String Constants
    public static final String EMPTY_STRING = "";
    public static final String YES = "y";
    public static final String INVALID_ENTRY = "Invalid input! Please Try again!";
    public static final String DIVIDER = "===========================================";

    // String Constants for input
    public static final String NAME_INPUT_TEXT = "Name <enter name of item no.%d>: ";
    public static final String PRICE_INPUT_TEXT = "Price <enter price of item no.%d>: ";
    public static final String CATEGORY_INPUT_TEXT = "Category <enter category of item no.%d>(raw/manufactured/imported): ";
    public static final String QUANTITY_INPUT_TEXT = "Quantity <enter quantity of item no.%d>: ";
    public static final String REPEAT_INPUT_TEXT = "Do you want to enter the details of any other item?(y/n): ";

    // String constants for output
    public static final String INFO_OUTPUT_TEXT = "ITEM INFO: #%d\n";
    public static final String NAME_OUTPUT_TEXT = "Name of item: %s\n";
    public static final String PRICE_PER_ITEM_OUTPUT_TEXT = "Price per item: %f\n";
    public static final String TAX_PER_ITEM_OUTPUT_TEXT = "Tax per item: %f\n";
    public static final String TOTAL_FINAL_PRICE_OUTPUT_TEXT = "Final price (for %d items): %f\n";


    // Numeric Constants
    public static final int COUNTER = 1;
    public static final double RAW_TAX_PERCENT = 12.5;
    public static final double MANUFACTURED_TAX_PERCENT = 12.5;
    public static final double MANUFACTURED_TAX_SURCHARGE_PERCENT = 2;
    public static final double IMPORT_DUTY_PERCENT = 10;
    public static final double IMPORT_SURCHARGE_LIMIT_1 = 100;
    public static final double IMPORT_SURCHARGE_LIMIT_2 = 200;
    public static final double IMPORT_SURCHARGE_TAX_PERCENT = 5;
    public static final double IMPORT_SURCHARGE_LIMIT_1_TAX = 5;
    public static final double IMPORT_SURCHARGE_LIMIT_2_TAX = 10;

}
