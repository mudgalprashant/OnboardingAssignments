package constants;

import java.util.Scanner;

/**
 * Constant.
 */
public class Constant {


  /**
   * Print menu text.
   */
  public static final String PRINT_MENU_TEXT =
      (new StringBuilder())
          .append("Choose any of the given options to function accordingly:\n")
          .append("To Add user details enter 1 \n")
          .append("To Display user details enter 2\n")
          .append("To Delete user details enter 3\n")
          .append("To Save user details to disk enter 4\n")
          .append("To Exit enter 0\n").toString();

  /**
   * User data display header
   */
  public static final String USER_DATA_DISPLAY_FORMAT =
      "%-20s    %-16s    %-3s    %-30s   %s";

  /**
   * Utility strings.
   */
  public static final String DIVIDER =
      "--------------------------------------------------------------------------------------------------";

  /**
   * Input message strings.
   */
  public static final String INPUT_NAME_TEXT = "Enter user's full name: ";
  /**
   * The constant INPUT_AGE_TEXT.
   */
  public static final String INPUT_AGE_TEXT = "Enter user's age: ";
  /**
   * The constant INPUT_ADDRESS_TEXT.
   */
  public static final String INPUT_ADDRESS_TEXT = "Enter user's address: ";
  /**
   * The constant INPUT_ROLL_NUMBER_TEXT.
   */
  public static final String INPUT_ROLL_NUMBER_TEXT = "Enter user's roll no.: ";
  /**
   * The constant COURSE_OPTIONS_TEXT.
   */
  public static final String COURSE_OPTIONS_TEXT = "Select any %d courses. Course options:\n";
  /**
   * The constant INPUT_COURSE_NAME_TEXT.
   */
  public static final String INPUT_COURSE_NAME_TEXT = "Enter user's #%d course: ";
  /**
   * The constant INPUT_ROLL_NUMBER_TO_DELETE.
   */
  public static final String INPUT_ROLL_NUMBER_TO_DELETE = "Enter the roll no. of User to be deleted:";
  /**
   * The constant ASK_TO_SAVE_TEXT.
   */
  public static final String ASK_TO_SAVE_TEXT =
      "Do you want to save any unsaved data before exiting the application?(y/n): ";
  /**
   * The constant YES.
   */
  public static final String YES = "y";
  /**
   * The constant SUCCESSFUL_EXIT_TEXT.
   */
  public static final String SUCCESSFUL_EXIT_TEXT = "Exit Successful";
  /**
   * The constant SUCCESSFUL_DELETION_TEXT.
   */
  public static final String SUCCESSFUL_DELETION_TEXT = "Deletion Successful";
  /**
   * The constant ROLL_NUMBER_DOES_NOT_EXIST.
   */
  public static final String ROLL_NUMBER_DOES_NOT_EXIST = "Entered roll no. does not exist";

  /**
   * Invalid entry texts.
   */
  public static final String INVALID_TASK_TEXT =
      "Please enter one one of the mentioned numbers to perform a task";
  /**
   * The constant INVALID_NAME_TEXT.
   */
  public static final String INVALID_NAME_TEXT = "Please enter a non-empty alphabetic name only!!";
  /**
   * The constant INVALID_AGE_TEXT.
   */
  public static final String INVALID_AGE_TEXT = "Please enter a positive numeric age!!";
  /**
   * The constant INVALID_ADDRESS_TEXT.
   */
  public static final String INVALID_ADDRESS_TEXT = "Please enter a non-empty address!!";
  /**
   * The constant INVALID_ROLL_NUMBER_TEXT.
   */
  public static final String INVALID_ROLL_NUMBER_TEXT = "Please enter a numeric roll No.!!";
  /**
   * The constant INVALID_COURSE_TEXT.
   */
  public static final String INVALID_COURSE_TEXT =
      "Please enter name of one of the specified courses!!";
  /**
   * The constant REPEATED_COURSE_TEXT.
   */
  public static final String REPEATED_COURSE_TEXT =
      "Entered course is already selected. Please select another course!!";
  /**
   * The constant REPEATED_ROLL_NUMBER_TEXT.
   */
  public static final String REPEATED_ROLL_NUMBER_TEXT =
      "Entered roll no. already exists. Please enter a unique roll no.!!";
  /**
   * The constant INPUT_LIMIT_REACHED.
   */
  public static final String INPUT_LIMIT_REACHED =
      String.format(
          "You have entered invalid input %d times.\nProgram Exited.",
          Constant.INVALID_INPUT_LIMIT);

  /**
   * regex constants.
   */
  public static final String REGEX_FOR_ALPHABETIC_STRING_WITH_SPACES = "^[a-zA-Z ]*$";
  /**
   * The constant REGEX_FOR_NUMERIC_STRING.
   */
  public static final String REGEX_FOR_NUMERIC_STRING = "[0-9]+";

  /**
   * Numeric constants.
   */
  public static final int MINIMUM_AGE = 0;
  /**
   * The constant COUNTER.
   */
  public static final int COUNTER = 1;
  /**
   * The constant NUMBER_OF_COURSES.
   */
  public static final int NUMBER_OF_COURSES = 4;
  /**
   * The constant NUMBER_OF_TASKS.
   */
  public static final int NUMBER_OF_TASKS = 5;
  /**
   * The constant INVALID_TASK_LIMIT.
   */
  public static final int INVALID_INPUT_LIMIT = 5;

  /**
   * System constants.
   */
  public static final Scanner SCANNER = new Scanner(System.in);

  /**
   * File names.
   */
  public static final String USER_DATA_FILE = "userData.txt";

  /**
   * File paths.
   */
  public static final String USER_DATA_FILE_PATH = "data";

}
