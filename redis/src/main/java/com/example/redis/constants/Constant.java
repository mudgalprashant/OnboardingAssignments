package com.example.redis.constants;


/**
 * Constants for various uses
 */
public final class Constant {

  /**
   * String constants for output
   * <p>
   * The constant INVALID_ENTRY.
   */
  public static final String INVALID_ENTRY = "Invalid input! Please Try again!";
  /**
   * The constant DIVIDER.
   */
  public static final String DIVIDER =
      "-----------------------------------------------";
  /**
   * The constant INFO_OUTPUT_TEXT.
   */
  public static final String INFO_OUTPUT_TEXT = "ITEM INFO: #%d\n";
  /**
   * The constant NAME_OUTPUT_TEXT.
   */
  public static final String NAME_OUTPUT_TEXT = "Name of item: %s\n";
  /**
   * The constant PRICE_PER_ITEM_OUTPUT_TEXT.
   */
  public static final String PRICE_PER_ITEM_OUTPUT_TEXT = "Price per item: %f\n";
  /**
   * The constant TAX_PER_ITEM_OUTPUT_TEXT.
   */
  public static final String TAX_PER_ITEM_OUTPUT_TEXT = "Tax per item: %f\n";
  /**
   * The constant TOTAL_FINAL_PRICE_OUTPUT_TEXT.
   */
  public static final String TOTAL_FINAL_PRICE_OUTPUT_TEXT = "Final price (for %d items): %f\n";


  /**
   * Numeric Constants
   * <p>
   * The constant COUNTER.
   */
  public static final int COUNTER = 1;
  /**
   * The constant MINIMUM_PRICE.
   */
  public static final int MINIMUM_PRICE = 0;
  /**
   * The constant MINIMUM_QUANTITY.
   */
  public static final int MINIMUM_QUANTITY = 1;
  /**
   * The constant RAW_TAX_PERCENT.
   */
  public static final double RAW_TAX_PERCENT = 12.5;
  /**
   * The constant MANUFACTURED_TAX_PERCENT.
   */
  public static final double MANUFACTURED_TAX_PERCENT = 12.5;
  /**
   * The constant MANUFACTURED_TAX_SURCHARGE_PERCENT.
   */
  public static final double MANUFACTURED_TAX_SURCHARGE_PERCENT = 2;
  /**
   * The constant IMPORT_DUTY_PERCENT.
   */
  public static final double IMPORT_DUTY_PERCENT = 10;
  /**
   * The constant IMPORT_SURCHARGE_LIMIT_1.
   */
  public static final double IMPORT_SURCHARGE_LIMIT_1 = 100;
  /**
   * The constant IMPORT_SURCHARGE_LIMIT_2.
   */
  public static final double IMPORT_SURCHARGE_LIMIT_2 = 200;
  /**
   * The constant IMPORT_SURCHARGE_TAX_PERCENT.
   */
  public static final double IMPORT_SURCHARGE_TAX_PERCENT = 5;
  /**
   * The constant IMPORT_SURCHARGE_LIMIT_1_TAX.
   */
  public static final double IMPORT_SURCHARGE_LIMIT_1_TAX = 5;
  /**
   * The constant IMPORT_SURCHARGE_LIMIT_2_TAX.
   */
  public static final double IMPORT_SURCHARGE_LIMIT_2_TAX = 10;
  /**
   * The constant DIVISOR_FOR_PERCENT.
   */
  public static final double DIVISOR_FOR_PERCENT = 100;

  /**
   * Database operations constants.
   * <p>
   * The constant HOST.
   */
  public static final String HOST = "jdbc:mysql://localhost:3306/redisDemo";
  /**
   * The constant USERNAME.
   */
  public static final String USERNAME = "testuser";
  /**
   * The constant PASSWORD.
   */
  public static final String PASSWORD = "testuser";
  /**
   * The constant DATABASE_CONNECTED.
   */
  public static final String DATABASE_CONNECTED = "MySQL DataBase connected!";
  /**
   * The constant DATABASE_DRIVER.
   */
  public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
  /**
   * The constant INSERT_QUERY.
   */
  public static final String INSERT_QUERY =
      "INSERT INTO `items` (`name`, `price`, `quantity`, `type`) "
          + "VALUES (\"%s\", %s, %s, \"%s\");";
  /**
   * The constant SELECT_ALL_QUERY.
   */
  public static final String SELECT_ALL_QUERY =
      "SELECT * FROM `items`";

  /**
   * Database constants.
   * <p>
   * The constant COLUMN_LABEL_NAME.
   */
  public static final String COLUMN_LABEL_NAME = "name";
  /**
   * The constant COLUMN_LABEL_PRICE.
   */
  public static final String COLUMN_LABEL_PRICE = "price";
  /**
   * The constant COLUMN_LABEL_CATEGORY.
   */
  public static final String COLUMN_LABEL_CATEGORY = "category";
  /**
   * The constant COLUMN_LABEL_QUANTITY.
   */
  public static final String COLUMN_LABEL_QUANTITY = "quantity";

  /**
   * The constant INPUT_THREAD_NAME.
   */
  public static final String INPUT_THREAD_NAME = "Input-Thread";
  /**
   * The constant OUTPUT_THREAD_NAME.
   */
  public static final String OUTPUT_THREAD_NAME = "Output-Thread";
}
