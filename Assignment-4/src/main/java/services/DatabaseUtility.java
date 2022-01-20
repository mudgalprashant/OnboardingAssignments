package services;

import constants.Constant;
import models.Item;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;

/**
 * The type Database utility.
 */
public class DatabaseUtility {

  /**
   * The logger.
   */
  private final Log log = LogFactory.getLog(DatabaseUtility.class);
  private Connection connection;

  /**
   * Connect database.
   */
  public void connectDatabase() {
    try {
      Class.forName(Constant.DATABASE_DRIVER);
      connection = DriverManager.getConnection(
          Constant.HOST,
          Constant.USERNAME,
          Constant.PASSWORD
      );
      if (connection != null) {
        log.info(Constant.DATABASE_CONNECTED);
      }
    } catch (SQLException | ClassNotFoundException exception) {
      log.error(exception);
    }
  }

  /**
   * Gets all items.
   *
   * @return the all items
   */
  public ResultSet getAllItems() {
    ResultSet resultSet = null;
    ArrayList<Item> itemList = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      resultSet = statement.executeQuery(Constant.SELECT_ALL_QUERY);
    } catch (SQLException exception) {
      log.error(exception);
    }
    return resultSet;
  }

  /**
   * Insert item.
   *
   * @param item the item
   */
  public void insertItem(Item item) {
    try {
      Statement statement = connection.createStatement();
      final String insertQuery = String.format(Constant.INSERT_QUERY,
          item.getName(),
          item.getPrice(),
          item.getCategory(),
          item.getQuantity());
      statement.executeQuery(insertQuery);
    } catch (SQLException exception) {
      log.error(exception);
    }
  }
}
