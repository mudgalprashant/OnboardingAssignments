package services;

import exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Validator test.
 */
class ValidatorTest {
    /**
     * The Exception to check if validator function is returning any exception or not(null).
     */
// Exception variable
    Exception exception;

    /**
     * Test validateName method in Validator Service.
     */
    @Test
    void validateName() {

        String name;
        // Test for valid name
        name = "abc123";
        Validator validator = new Validator();
        exception = validator.validateName(name);
        assertNull(exception);

        // Test for invalid name
        // Case 1: Empty string
        name = "";
        exception = validator.validateName(name);
        assertInstanceOf(InvalidEntryException.class, exception);

        // Case 2: Blank string or string with whitespaces only
        name = "    ";
        exception = validator.validateName(name);
        assertInstanceOf(InvalidEntryException.class, exception);

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
        exception = validator.validatePrice(price);
        assertNull(exception);

        // Test for negative price
        price = "-231.52";
        exception = validator.validatePrice(price);
        assertInstanceOf(InvalidEntryException.class, exception);

        // Test for not-a-number price
        price = "123hello";
        exception = validator.validatePrice(price);
        assertInstanceOf(java.lang.NumberFormatException.class, exception);

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
        exception = validator.validateCategory(category);
        assertNull(exception);

        category = "raw";
        exception = validator.validateCategory(category);
        assertNull(exception);

        category = "RAW";
        exception = validator.validateCategory(category);
        assertNull(exception);

        // Case 2: Manufactured category
        category = "Manufactured";
        exception = validator.validateCategory(category);
        assertNull(exception);

        category = "manufactured";
        exception = validator.validateCategory(category);
        assertNull(exception);

        category = "MANUFACTURED";
        exception = validator.validateCategory(category);
        assertNull(exception);

        // Case 3: Imported category
        category = "Imported";
        exception = validator.validateCategory(category);
        assertNull(exception);

        category = "imported";
        exception = validator.validateCategory(category);
        assertNull(exception);

        category = "IMPORTED";
        exception = validator.validateCategory(category);
        assertNull(exception);

        // Invalid Category test
        category = "Exported";
        exception = validator.validateCategory(category);
        assertInstanceOf(IllegalArgumentException.class, exception);

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
        exception = validator.validateQuantity(quantity);
        assertNull(exception);

        // Invalid Quantity test
        // Case 1: negative quantity
        quantity = "-35";
        exception = validator.validateQuantity(quantity);
        assertInstanceOf(InvalidEntryException.class, exception);

        // Case 2: fractional quantity
        quantity = "4.5";
        exception = validator.validateQuantity(quantity);
        assertInstanceOf(NumberFormatException.class, exception);

        // Case 3: non-integer quantity
        quantity = "abc123";
        exception = validator.validateQuantity(quantity);
        assertInstanceOf(NumberFormatException.class, exception);

    }
}