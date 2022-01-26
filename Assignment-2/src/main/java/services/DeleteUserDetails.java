package services;

import constants.Constant;
import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * The type Delete user details.
 */
public class DeleteUserDetails {

  /**
   * Instantiates a new Delete user details.
   *
   * @param userList   the user list
   * @param rollNumber the roll number
   */
  public DeleteUserDetails(PriorityQueue<User> userList, String rollNumber) {
    final Validator validator = new Validator();
    try {
      List<User> matchedUser = userList.stream()
          .filter(user -> user.getRollNumber().equals(rollNumber))
          .collect(Collectors.toList());

      if (matchedUser.size() == 0) {
        System.out.println(Constant.ROLL_NUMBER_DOES_NOT_EXIST);
      }
      userList.remove(matchedUser.get(0));
      System.out.println(Constant.SUCCESSFUL_DELETION_TEXT);
    } catch (Exception exception) {
      Log log = LogFactory.getLog(DeleteUserDetails.class);
      log.error(exception);
    }
  }
}
