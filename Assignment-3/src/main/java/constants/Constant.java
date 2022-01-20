package constants;

import java.util.Scanner;

/**
 * The type Constant.
 */
public class Constant {

  /**
   * The constant PRINT_MENU_TEXT.
   * Input menu
   */
  public static final String PRINT_MENU_TEXT =
      (new StringBuilder())
          .append("Choose any of the given options to function accordingly:\n")
          .append("To Add a node enter 1 \n")
          .append("To add dependency enter 2\n")
          .append("To get parents of a node enter 3\n")
          .append("To get children of a node enter 4\n")
          .append("To get ancestors of a node enter 5 \n")
          .append("To get descendants a node enter 6 \n")
          .append("To delete a node enter 7 \n")
          .append("To delete a dependency enter 8 \n")
          .append("To Exit enter 0\n").toString();


  /**
   * System Constants.
   * The constant SCANNER.
   */
  public static final Scanner SCANNER = new Scanner(System.in);

  /**
   * Exception Constants
   * The constant BLANK_ID_EXCEPTION_TEXT.
   */
  public static final String BLANK_ID_EXCEPTION_TEXT =
      "Node Id cannot be blank. Please enter a valid Id!";
  /**
   * The constant NON_ALPHANUMERIC_ID_EXCEPTION_TEXT.
   */
  public static final String NON_ALPHANUMERIC_ID_EXCEPTION_TEXT =
      "Please enter an Id with digits and English alphabets only";
  /**
   * The constant NODE_ID_ALREADY_EXISTS_TEXT.
   */
  public static final String NODE_ID_ALREADY_EXISTS_TEXT =
      "Entered node Id belongs to some previously added node." +
          "\n Please Enter a valid Id!";
  /**
   * The constant NODE_ID_DOES_NOT_EXIST_TEXT.
   */
  public static final String NODE_ID_DOES_NOT_EXIST_TEXT =
      "Entered node Id does not exist. Please enter a valid Id";
  /**
   * The constant INVALID_TASK_TEXT.
   */
  public static final String INVALID_TASK_TEXT =
      "Please enter one one of the mentioned numbers to perform a task";
  /**
   * The constant CYCLIC_DEPENDENCY_TEXT.
   */
  public static final String CYCLIC_DEPENDENCY_TEXT =
      "Child-parent pair entered is invalid. It is creating cyclic dependency.";

  /**
   * Input text Strings
   * The constant YES.
   */
  public static final String YES = "y";
  /**
   * The constant NODE_ID_INPUT_TEXT.
   */
  public static final String NODE_ID_INPUT_TEXT =
      "Enter node Id: ";
  /**
   * The constant NODE_NAME_INPUT_TEXT.
   */
  public static final String NODE_NAME_INPUT_TEXT =
      "Enter name of the node: ";
  /**
   * The constant METADATA_QUERY_TEXT.
   */
  public static final String METADATA_QUERY_TEXT =
      "Do you want to attach any other information(Metadata) with this node?(y/n): ";
  /**
   * The constant INPUT_METADATA_KEY_TEXT.
   */
  public static final String INPUT_METADATA_KEY_TEXT =
      "Enter key for metadata: ";
  /**
   * The constant INPUT_METADATA_VALUE_TEXT.
   */
  public static final String INPUT_METADATA_VALUE_TEXT =
      "Enter value for metadata: ";
  /**
   * The constant INPUT_DEPENDENCY_PARENT_ID.
   */
  public static final String INPUT_DEPENDENCY_PARENT_ID =
      "Enter the parent node Id: ";
  /**
   * The constant INPUT_DEPENDENCY_CHILD_ID.
   */
  public static final String INPUT_DEPENDENCY_CHILD_ID =
      "Enter the child node Id: ";

  /**
   * Output text strings.
   * The constant DIVIDER.
   */
  public static final String DIVIDER =
      "--------------------------------------------------------------------------------------------------";
  /**
   * The constant SUCCESSFUL_EXIT_TEXT.
   */
  public static final String SUCCESSFUL_EXIT_TEXT = "Exit Successful";
  /**
   * The constant SUCCESSFUL_DELETION_TEXT.
   */
  public static final String SUCCESSFUL_DELETION_TEXT = "Deletion Successful";

  /**
   * Regex strings.
   * The constant REGEX_FOR_NODE_ID.
   */
  public static final String REGEX_FOR_NODE_ID = "^[a-zA-Z0-9]*$";

  /**
   * Numeric constants.
   * The constant NUMBER_OF_TASKS.
   */
  public static final int NUMBER_OF_TASKS = 9;

}
