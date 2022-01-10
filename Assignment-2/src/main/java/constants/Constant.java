package constants;

import java.util.Scanner;

public class Constant {


    /**
     * String constants
     */

    // Utility strings
    public static final String EMPTY_STRING = "";

    // Input message strings
    public static final String INPUT_NAME_TEXT = "Enter user's full name: ";
    public static final String INPUT_AGE_TEXT = "Enter user's age: ";
    public static final String INPUT_ADDRESS_TEXT = "Enter user's address: ";
    public static final String INPUT_ROLL_NUMBER_TEXT = "Enter user's roll no.: ";
    public static final String COURSE_OPTIONS_TEXT = "Select any %d courses. Course options:";
    public static final String INPUT_COURSE_NAME_TEXT = "Enter user's #%d course: ";

    // Invalid entry texts
    public static final String INVALID_NAME_TEXT = "Please enter a non-empty alphabetic name only!!";
    public static final String INVALID_AGE_TEXT = "Please enter a positive numeric age!!";
    public static final String INVALID_ADDRESS_TEXT  = "Please enter a non-empty address!!";
    public static final String INVALID_ROLL_NUMBER_TEXT = "Please enter a numeric roll No.!!";
    public static final String INVALID_COURSE_TEXT = "Please enter name of one of the specified courses!!";
    public static final String REPEATED_COURSE_TEXT = "Entered course is already selected. Please select another course!!";


    /**
     * regex constants
     */
    public static final String REGEX_FOR_ALPHABETIC_STRING_WITH_SPACES = "^[a-zA-Z ]*$";
    public static final String REGEX_FOR_NUMERIC_STRING = "[0-9]+";

    /**
     * Numeric constants
     */
    public static final int MINIMUM_AGE = 0;
    public static final int COUNTER = 1;
    public static final int NUMBER_OF_COURSES = 4;

    /**
     * System constants
     */
    public static final Scanner SCANNER = new Scanner(System.in);
}
