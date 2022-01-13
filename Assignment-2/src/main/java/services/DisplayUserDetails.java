package services;

import java.util.PriorityQueue;

import constants.Constant;
import models.User;


/**
 * The type Display user details.
 */
public class DisplayUserDetails {
  /**
   * Print user list.
   *
   * @param userList the user list
   */
  public void printUserList(PriorityQueue<User> userList) {
    System.out.println(Constant.DIVIDER);
    System.out.printf(Constant.USER_DATA_DISPLAY_FORMAT,
        "Name", "Roll Number", "Age", "Address", "Courses");
    System.out.println();
    System.out.println(Constant.DIVIDER);
    for (User user : userList) {
      System.out.printf(Constant.USER_DATA_DISPLAY_FORMAT,
          user.getName(), user.getRollNumber(), user.getAge(), user.getAddress(), user.getCourses());
      System.out.println();
    }
    System.out.println(Constant.DIVIDER);
  }
}
