package services;

import constants.Constant;
import enums.Category;
import exceptions.InvalidEntryException;

/**
 * The type Validator for console input validations.
 */
public class Validator {

    /**
     * Validate input name.
     *
     * @param name the name
     * @return the exception
     */
    public Exception validateName(String name){
        try{
            if (name.isBlank()) {
                throw new InvalidEntryException(Constant.INVALID_ENTRY);
            }
        }catch(InvalidEntryException invalidEntryException){
            return invalidEntryException;
        }
        return null;
    }

    /**
     * Validate validate price.
     *
     * @param price the price
     * @return the exception
     */
    public Exception validatePrice(String price) {
        try {
            final double numericPrice = Double.parseDouble(price);
            if (numericPrice < Constant.MINIMUM_PRICE) {
                throw new InvalidEntryException(Constant.INVALID_ENTRY);
            }
        } catch (NumberFormatException | InvalidEntryException invalidEntryException) {
            return invalidEntryException;
        }
        return null;
    }

    /**
     * Validate input Category.
     *
     * @param category the category
     * @return the exception
     */
    public Exception validateCategory(String category){
        try {
            Category.valueOf(category.toUpperCase());
        }catch(IllegalArgumentException invalidEntryException){
            return invalidEntryException;
        }
        return null;
    }

    /**
     * Validate input quantity.
     *
     * @param quantity the quantity
     * @return the exception
     */
    public Exception validateQuantity(String quantity){
        try{
            final int numericQuantity = Integer.parseInt(quantity);
            if (numericQuantity < Constant.MINIMUM_QUANTITY){
                throw new InvalidEntryException(Constant.INVALID_ENTRY);
            }
        }catch(NumberFormatException | InvalidEntryException invalidEntryException){
            return invalidEntryException;
        }
        return null;
    }
}
