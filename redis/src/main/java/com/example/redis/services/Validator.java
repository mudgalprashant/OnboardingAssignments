package com.example.redis.services;

import com.example.redis.constants.Constant;
import com.example.redis.enums.Category;
import com.example.redis.exceptions.InvalidItemException;
import com.example.redis.models.Item;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The type Validator for console input validations.
 */
public class Validator {

  private final Log log = LogFactory.getLog(Validator.class);

  /**
   * Validate item boolean.
   *
   * @param item the item
   * @return the boolean
   */
  public boolean validateItem(Item item) {
    boolean isValid;
    isValid = validateName(item.getName())
        && validatePrice(Double.toString(item.getPrice()))
        && validateCategory(item.getCategory().toString())
        && validateQuantity(Integer.toString(item.getQuantity()));
    return isValid;
  }

  /**
   * Validate input name.
   *
   * @param name the name
   * @return the exception
   */
  public boolean validateName(String name) {
    boolean isValid = true;
    try {
      if (name.isBlank()) {
        throw new InvalidItemException(Constant.INVALID_ENTRY);
      }
    } catch (InvalidItemException exception) {
      log.error(exception);
      isValid = false;
    }
    return isValid;
  }

  /**
   * Validate validate price.
   *
   * @param price the price
   * @return the exception
   */
  public boolean validatePrice(String price) {
    boolean isValid = true;
    try {
      final double numericPrice = Double.parseDouble(price);
      if (numericPrice < Constant.MINIMUM_PRICE) {
        throw new InvalidItemException(Constant.INVALID_ENTRY);
      }
    } catch (NumberFormatException | InvalidItemException exception) {
      log.error(exception);
      isValid = false;
    }
    return isValid;
  }

  /**
   * Validate input Category.
   *
   * @param category the category
   * @return the exception
   */
  public boolean validateCategory(String category) {
    boolean isValid = true;
    try {
      Category.valueOf(category.toUpperCase());
    } catch (IllegalArgumentException exception) {
      log.error(exception);
      isValid = false;
    }
    return isValid;
  }

  /**
   * Validate input quantity.
   *
   * @param quantity the quantity
   * @return the exception
   */
  public boolean validateQuantity(String quantity) {
    boolean isValid = true;
    try {
      final int numericQuantity = Integer.parseInt(quantity);
      if (numericQuantity < Constant.MINIMUM_QUANTITY) {
        throw new InvalidItemException(Constant.INVALID_ENTRY);
      }
    } catch (NumberFormatException | InvalidItemException exception) {
      log.error(exception);
      isValid = false;
    }
    return isValid;
  }
}
