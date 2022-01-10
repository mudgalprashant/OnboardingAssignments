package services;

import exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Validator Service test
class ValidatorTest {
    // Exception variable to check if validator function is returning any exception or not(null)
    Exception exception;
    @Test
    // Test validateName method in Validator Service
    void validateName() {

        String name;
        // Test for valid name
        name = "abc123";
        exception = Validator.validateName(name);
        assertNull(exception);

        // Test for invalid name
        // Case 1: Empty string
        name = "";
        exception = Validator.validateName(name);
        assertInstanceOf(InvalidEntryException.class, exception);

        // Test for invalid name
        // Case 2: Blank string or string with whitespaces only
        name = "    ";
        exception = Validator.validateName(name);
        assertInstanceOf(InvalidEntryException.class, exception);

    }

    @Test
    // Test for validatePrice method in Validator Service
    void validatePrice() {
        String price;

        // Test for valid price
        price = "2342.532";
        exception = Validator.validatePrice(price);
        assertNull(exception);

        // Test for negative price
        price = "-231.52";
        exception = Validator.validatePrice(price);
        assertInstanceOf(InvalidEntryException.class, exception);

        // Test for not-a-number price
        price = "123hello";
        exception = Validator.validatePrice(price);
        assertInstanceOf(java.lang.NumberFormatException.class, exception);

    }

    @Test
    // Test for validateCategory method in Validator Service
    void validateCategory() {
        String category;

        // Test for valid category
        // Case 1: Raw category
        category = "Raw";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        category = "raw";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        category = "RAW";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        // Case 2: Manufactured category
        category = "Manufactured";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        category = "manufactured";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        category = "MANUFACTURED";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        // Case 3: Imported category
        category = "Imported";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        category = "imported";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        category = "IMPORTED";
        exception = Validator.validateCategory(category);
        assertNull(exception);

        // Invalid Category test
        category = "Exported";
        exception = Validator.validateCategory(category);
        assertInstanceOf(IllegalArgumentException.class, exception);

    }

    @Test
    // Test for validateQuantity method of Validator Service
    void validateQuantity() {

        String quantity;

        // Valid Quantity test
        quantity = "35"; // Positive integer
        exception = Validator.validateQuantity(quantity);
        assertNull(exception);

        // Invalid Quantity test
        // Case 1: negative quantity
        quantity = "-35";
        exception = Validator.validateQuantity(quantity);
        assertInstanceOf(InvalidEntryException.class, exception);

        // Case 2: fractional quantity
        quantity = "4.5";
        exception = Validator.validateQuantity(quantity);
        assertInstanceOf(NumberFormatException.class, exception);

        // Case 3: non-integer quantity
        quantity = "abc123";
        exception = Validator.validateQuantity(quantity);
        assertInstanceOf(NumberFormatException.class, exception);

    }
}