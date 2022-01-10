package services;

import constants.Constant;
import enums.CourseName;
import exceptions.InvalidEntryException;

import java.util.Locale;

/**
 *  Validate the inputs from console
 */
public class Validator {


    /**
     * Validates name against empty string and non-alphabetic string
     */
    public static boolean validateName(String name){
        return (!name.equals(Constant.EMPTY_STRING))
                && (name.matches(Constant.REGEX_FOR_ALPHABETIC_STRING_WITH_SPACES));
    }

    /**
     * Validates age against negative or non-integer entry
     */
    public static boolean validateAge(String age){
        try{
            int numericAge = Integer.parseInt(age);
            if (numericAge <= Constant.MINIMUM_AGE){
                throw new InvalidEntryException("Age must be positive Integer");
            }
            return true;
        }catch(NumberFormatException | InvalidEntryException e){
            return false;
        }
    }

    /**
     * Validates Address against empty string
     */
    public static boolean validateAddress(String address){
        return !address.equals(Constant.EMPTY_STRING);
    }

    /**
     * Validate roll no. against empty or non-numeric entry
     */
    public static boolean validateRollNumber(String rollNumber){
        return (!rollNumber.equals(Constant.EMPTY_STRING))
                && (rollNumber.matches(Constant.REGEX_FOR_NUMERIC_STRING));
    }

    /**
     * Validate courses against available courses
     */
    public static boolean validateCourse(String course){
        try{
            CourseName.valueOf(course.toUpperCase());
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }

}
