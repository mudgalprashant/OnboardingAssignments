package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Validator test.
 */
class ValidatorTest {

  /**
   * Test validateName method in Validator Service.
   */
  @Test
  void validateName() {

    String name;
    // Test for valid name
    name = "abc123";
    Validator validator = new Validator();
    assertTrue(validator.validateName(name));

    // Test for invalid name
    // Case 1: Empty string
    name = "";
    assertFalse(validator.validateName(name));

    // Case 2: Blank string or string with whitespaces only
    name = "    ";
    assertFalse(validator.validateName(name));

  }

  /**
   * Test for validatePrice method in Validator Service.
   */
  @Test
  void validatePrice() {
    String price;

    // Test for valid price
    price = "2342.532";
    Validator validator = new Validator();
    assertTrue(validator.validatePrice(price));

    // Test for negative price
    price = "-231.52";
    assertFalse(validator.validatePrice(price));

    // Test for not-a-number price
    price = "123hello";
    assertFalse(validator.validatePrice(price));

  }

  /**
   * Test for validateCategory method in Validator Service.
   */
  @Test
  void validateCategory() {
    String category;

    // Test for valid category
    // Case 1: Raw category
    category = "Raw";
    Validator validator = new Validator();
    assertTrue(validator.validateCategory(category));

    category = "raw";
    assertTrue(validator.validateCategory(category));

    category = "RAW";
    assertTrue(validator.validateCategory(category));

    // Case 2: Manufactured category
    category = "Manufactured";
    assertTrue(validator.validateCategory(category));

    category = "manufactured";
    assertTrue(validator.validateCategory(category));

    category = "MANUFACTURED";
    assertTrue(validator.validateCategory(category));

    // Case 3: Imported category
    category = "Imported";
    assertTrue(validator.validateCategory(category));

    category = "imported";
    assertTrue(validator.validateCategory(category));

    category = "IMPORTED";
    assertTrue(validator.validateCategory(category));

    // Invalid Category test
    category = "Exported";
    assertFalse(validator.validateCategory(category));

  }

  /**
   * Test for validateQuantity method of Validator Service.
   */
  @Test
  void validateQuantity() {

    String quantity;

    // Valid Quantity test
    quantity = "35"; // Positive integer
    Validator validator = new Validator();
    assertTrue(validator.validateQuantity(quantity));

    // Invalid Quantity test
    // Case 1: negative quantity
    quantity = "-35";
    assertFalse(validator.validateQuantity(quantity));

    // Case 2: fractional quantity
    quantity = "4.5";
    assertFalse(validator.validateQuantity(quantity));

    // Case 3: non-integer quantity
    quantity = "abc123";
    assertFalse(validator.validateQuantity(quantity));

  }
}