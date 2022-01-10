package services;
import constants.*;
import enums.*;
import exceptions.*;

// Validate input format
public class Validator {

    // Validate input name
    public static Exception validateName(String name){
        try{
            if (name.trim().equals(Constant.EMPTY_STRING)) {
                throw new InvalidEntryException(Constant.INVALID_ENTRY);
            }
        }catch(InvalidEntryException invalidEntryException){
            return invalidEntryException;
        }
        return null;
    }

    // Validate input Price
    public static Exception validatePrice(String price) {
        try {
            double numericPrice = Double.parseDouble(price);
            if (numericPrice < 0) {
                throw new InvalidEntryException(Constant.INVALID_ENTRY);
            }
        } catch (NumberFormatException | InvalidEntryException invalidEntryException) {
            return invalidEntryException;
        }
        return null;
    }

    // Validate input category
    public static Exception validateCategory(String category){
        try {
            Category.valueOf(category.toUpperCase());
        }catch(IllegalArgumentException invalidEntryException){
            return invalidEntryException;
        }
        return null;
    }

    // Validate input quantity
    public static Exception validateQuantity(String quantity){
        try{
            int numericQuantity = Integer.parseInt(quantity);
            if (numericQuantity < 0){
                throw new InvalidEntryException(Constant.INVALID_ENTRY);
            }
        }catch(NumberFormatException | InvalidEntryException invalidEntryException){
            return invalidEntryException;
        }
        return null;
    }
}
