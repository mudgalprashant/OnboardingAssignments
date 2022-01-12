package controller;

import constants.Constant;

import java.io.File;
import java.util.PriorityQueue;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.AddUserDetails;
import services.LoadUserDetails;
import services.SaveUserDetails;
import services.Validator;


/**
 * ApplicationUtility class for menu display and working.
 */
public class ApplicationUtility {

  private final File userDataFile = new File(
      Constant.USER_DATA_FILE_PATH + File.separator + Constant.USER_DATA_FILE);

  private final PriorityQueue<User> userList = new PriorityQueue<>();

  private static final Log log = LogFactory.getLog(ApplicationUtility.class);

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
    addUserDetails.addAllDetails(user);
    userList.add(user);
  }

  private void displayUsers() { // Todo
    System.out.println(userList);
  }

  private void saveUsers() {
    new SaveUserDetails(userDataFile, userList);
  }

  private void deleteUser() { //Todo
  }
}
