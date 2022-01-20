package services;

import constants.Constant;
import enums.Category;
import models.Item;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * Takes input from console
 */
public class ThreadUtility {

  // To log error/info messages
  private static final Log log = LogFactory.getLog(ThreadUtility.class);

  /**
   * The Taxation.
   */
// To use tax functionalities
  Taxation taxation = new Taxation();

  /**
   * The Item list.
   */
// To store items
  LinkedList<Item> itemList = new LinkedList<>();

  /**
   * The Result set.
   */
// To store result from database queries
  ResultSet resultSet;

  /**
   * Add items in the itemList from console input
   */
  public void runThreads() {

    // Connect to the database
    final DatabaseUtility databaseUtility = new DatabaseUtility();
    databaseUtility.connectDatabase();

    // Fetch all items in "items" table
    resultSet = databaseUtility.getAllItems();

    // Thread to read from database
    Thread inputThread = new Thread(() -> fetchItems(resultSet), "Input-Thread");

    // Thread to write on console
    Thread outputThread = new Thread(this::printItems, "Output-Thread");

    // Start Threads
    inputThread.start();
    outputThread.start();

    // Join threads
    try {
      inputThread.join();
      outputThread.join();
    } catch (InterruptedException exception) {
      log.error(exception);
    }

  }

  /**
   * @param resultSet : result of the database query.
   */
  private void fetchItems(ResultSet resultSet) {
    synchronized (this) {
      try {
        while (resultSet.next()) {

          // Cast row of table into Item object
          Item item = Item.builder().build();
          item.setName(
              resultSet.getString("name"));
          item.setPrice(
              resultSet.getDouble("price"));
          item.setCategory(
              Category.valueOf(
                  resultSet.getString("category").toUpperCase()));
          item.setQuantity(
              resultSet.getInt("quantity"));

          // Add item to list
          itemList.add(item);
          notify();
        }
        resultSet.close();
      } catch (SQLException exception) {
        log.error(exception);
      }
    }
  }

  private void printItems() {
    synchronized (this) {
      int counter = Constant.COUNTER;
      while (!itemList.isEmpty()) {
        Item item = itemList.pollFirst();
        taxation.calculateTax(item);
        System.out.printf(Constant.INFO_OUTPUT_TEXT, counter++);
        System.out.printf(Constant.NAME_OUTPUT_TEXT, item.getName());
        System.out.printf(Constant.PRICE_PER_ITEM_OUTPUT_TEXT, item.getPrice());
        System.out.printf(Constant.TAX_PER_ITEM_OUTPUT_TEXT, item.getTax());
        System.out.printf(Constant.TOTAL_FINAL_PRICE_OUTPUT_TEXT, item.getQuantity(), (item.getPrice() + item.getTax()) * item.getQuantity());
        System.out.println(Constant.DIVIDER);
        notify();
      }
    }
  }

}


