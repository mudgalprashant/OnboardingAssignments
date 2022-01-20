package services;

import constants.Constant;

import java.io.File;
import java.util.PriorityQueue;

import models.User;
import services.*;


/**
 * ApplicationUtility class for menu display and working.
 */
public class ApplicationUtility {

  private final File userDataFile = new File(
      (new StringBuilder())
          .append(Constant.USER_DATA_FILE_PATH)
          .append(File.separator)
          .append(Constant.USER_DATA_FILE)
          .toString());

  private final PriorityQueue<User> userList = new PriorityQueue<>();


  /**
   * Instantiates a new Application utility.
   */
  public ApplicationUtility() {
    new LoadUserDetails(userDataFile, userList);
  }

  /**
   * Perform tasks.
   */
  public void performTasks() {
    System.out.println(Constant.DIVIDER);
    System.out.println(Constant.PRINT_MENU_TEXT);

    final String inputTask = Constant.SCANNER.nextLine();

    Validator validator = new Validator();
    if (validator.validateTask(inputTask)) {
      final int taskNumber = Integer.parseInt(inputTask);
      switch (taskNumber) {
        case 1:
          addUser();
          break;
        case 2:
          displayUsers();
          break;
        case 3:
          deleteUser();
          break;
        case 4:
          saveUsers();
          break;
        case 0:
          System.out.println(Constant.ASK_TO_SAVE_TEXT);
          final String save = Constant.SCANNER.nextLine();
          if (save.equalsIgnoreCase(Constant.YES)) {
            saveUsers();
          }
          System.out.println(Constant.SUCCESSFUL_EXIT_TEXT);
          return;
        default:
          break;
      }
    } else {
      System.out.println(Constant.INVALID_TASK_TEXT);
    }
    performTasks();
  }

  private void addUser() {
    User user = User.builder().build();
    AddUserDetails addUserDetails = new AddUserDetails();
    addUserDetails.addAllDetails(userList, user);
    userList.add(user);
  }

  private void displayUsers() {
    final DisplayUserDetails displayUserDetails = new DisplayUserDetails();
    displayUserDetails.printUserList(userList);
  }

  private void saveUsers() {
    new SaveUserDetails(userDataFile, userList);
  }

  private void deleteUser() {
    System.out.println(Constant.INPUT_ROLL_NUMBER_TO_DELETE);
    String rollNumber = Constant.SCANNER.nextLine();
    new DeleteUserDetails(userList, rollNumber);
  }
}
