package constants;

import java.util.Scanner;

public class Constant {

  // Input menu
  public static final String PRINT_MENU_TEXT =
      "Choose any of the given options to function accordingly:\n"
          + "To Add a node enter 1 \n"
          + "To add dependency enter 2\n"
          + "To get parents of a node enter 3\n"
          + "To get children of a node enter 4\n"
          + "To get ancestors of a node enter 5 \n"
          + "To get descendants a node enter 6 \n"
          + "To delete a node enter 7 \n"
          + "To delete a dependency enter 8 \n"
          + "To Exit enter 0\n";


  // System constants
  public static final Scanner SCANNER = new Scanner(System.in);

  // Exception Strings
  public static final String BLANK_ID_EXCEPTION_TEXT =
      "Node Id cannot be blank. Please enter a valid Id!";
  public static final String NON_ALPHANUMERIC_ID_EXCEPTION_TEXT =
      "Please enter an Id with digits and English alphabets only";
  public static final String NODE_ID_ALREADY_EXISTS_TEXT =
      "Entered node Id belongs to some previously added node." +
          "\n Please Enter a valid Id!";
  public static final String NODE_ID_DOES_NOT_EXIST_TEXT =
      "Entered node Id does not exist. Please enter a valid Id";
  public static final String INVALID_TASK_TEXT =
      "Please enter one one of the mentioned numbers to perform a task";
  public static final String CYCLIC_DEPENDENCY_TEXT =
      "Child-parent pair entered is invalid. It is creating cyclic dependency.";

  // Input text Strings
  public static final String YES = "y";
  public static final String NODE_ID_INPUT_TEXT =
      "Enter node Id: ";
  public static final String NODE_NAME_INPUT_TEXT =
      "Enter name of the node: ";
  public static final String METADATA_QUERY_TEXT =
      "Do you want to attach any other information(Metadata) with this node?(y/n): ";
  public static final String INPUT_METADATA_KEY_TEXT =
      "Enter key for metadata: ";
  public static final String INPUT_METADATA_VALUE_TEXT =
      "Enter value for metadata: ";
  public static final String INPUT_DEPENDENCY_PARENT_ID =
      "Enter the parent node Id: ";
  public static final String INPUT_DEPENDENCY_CHILD_ID =
      "Enter the child node Id: ";

  // Output strings
  public static final String DIVIDER =
      "--------------------------------------------------------------------------------------------------";
  public static final String SUCCESSFUL_EXIT_TEXT = "Exit Successful";
  public static final String SUCCESSFUL_DELETION_TEXT = "Deletion Successful";

  // Regex Strings
  public static final String REGEX_FOR_NODE_ID = "^[a-zA-Z0-9]*$";

  //Numeric constants
  public static final int NUMBER_OF_TASKS = 9;

}
