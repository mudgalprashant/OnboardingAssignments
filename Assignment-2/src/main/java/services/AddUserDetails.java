package services;

import constants.Constant;
import enums.Course;

import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

import models.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * The type Add user details.
 */
public class AddUserDetails {
  private final Validator validator = new Validator();
  private static final Log log = LogFactory.getLog(AddUserDetails.class);

  /**
   * Add all details user.
   *
   * @param userList the user list
   * @param user     the user
   */
  public boolean addAllDetails(PriorityQueue<User> userList, User user) {

    boolean detailAdded;
    detailAdded = addName(user);

    if (detailAdded) {
      detailAdded = addAge(user);
    }
    if (detailAdded) {
      detailAdded = addAddress(user);
    }
    if (detailAdded) {
      detailAdded = addRollNumber(userList, user);
    }
    if (detailAdded) {
      detailAdded = addCourses(user);
    }
    return detailAdded;
  }

  /**
   * Add name to the user details.
   *
   * @param user the user
   */
  public boolean addName(User user) {

    int inputCounter = Constant.COUNTER;
    boolean success = false;
    while (inputCounter++ <= Constant.INVALID_INPUT_LIMIT) {
      // Input name from console
      System.out.println(Constant.INPUT_NAME_TEXT);
      final String name = Constant.SCANNER.nextLine();

      // Check for valid name and if invalid name is entered, ask for input again
      if (validator.validateName(name)) {
        user.setName(name);
        success = true;
        break;
      } else {
        log.error(Constant.INVALID_NAME_TEXT);
      }
    }
    if (inputCounter >= Constant.INVALID_INPUT_LIMIT) {
      System.out.println(Constant.INPUT_LIMIT_REACHED);
    }
    return success;
  }

  /**
   * Add age to user details.
   *
   * @param user the user
   */
  public boolean addAge(User user) {

    int inputCounter = Constant.COUNTER;
    boolean success = false;
    while (inputCounter++ <= Constant.INVALID_INPUT_LIMIT) {
      // Input age from console
      System.out.println(Constant.INPUT_AGE_TEXT);
      final String age = Constant.SCANNER.nextLine();

      // Check for valid age and if invalid age is entered, ask for input again
      if (validator.validateAge(age)) {
        int numericAge = Integer.parseInt(age);
        user.setAge(numericAge);
        success = true;
        break;
      } else {
        log.error(Constant.INVALID_AGE_TEXT);
      }
    }
    if (inputCounter >= Constant.INVALID_INPUT_LIMIT) {
      System.out.println(Constant.INPUT_LIMIT_REACHED);
    }
    return success;
  }

  /**
   * Add address to user details.
   *
   * @param user the user
   */
  public boolean addAddress(User user) {

    int inputCounter = Constant.COUNTER;
    boolean success = false;
    while (inputCounter++ <= Constant.INVALID_INPUT_LIMIT) {
      // Input address from console
      System.out.println(Constant.INPUT_ADDRESS_TEXT);
      String address = Constant.SCANNER.nextLine();

      // Check for valid address and if invalid address is entered, ask for input again
      if (validator.validateAddress(address)) {
        user.setAddress(address);
        success = true;
        break;
      } else {
        log.error(Constant.INVALID_ADDRESS_TEXT);
      }
    }
    if (inputCounter >= Constant.INVALID_INPUT_LIMIT) {
      System.out.println(Constant.INPUT_LIMIT_REACHED);
    }
    return success;
  }

  /**
   * Add roll no. to user details.
   *
   * @param userList the user list
   * @param user     the user
   */
  public boolean addRollNumber(PriorityQueue<User> userList, User user) {

    int inputCounter = Constant.COUNTER;
    boolean success = false;
    while (inputCounter++ <= Constant.INVALID_INPUT_LIMIT) {
      // Input roll No. from console
      System.out.println(Constant.INPUT_ROLL_NUMBER_TEXT);
      String rollNumber = Constant.SCANNER.nextLine();

      // Check for valid roll No. and if invalid roll No. is entered, ask for input again
      if (validator.validateRollNumber(userList, rollNumber)) {
        user.setRollNumber(rollNumber);
        success = true;
        break;
      } else {
        log.error(Constant.INVALID_ROLL_NUMBER_TEXT);
      }
    }
    if (inputCounter >= Constant.INVALID_INPUT_LIMIT) {
      System.out.println(Constant.INPUT_LIMIT_REACHED);
    }
    return success;
  }

  /**
   * Add Courses to user details.
   *
   * @param user the user
   */
  public boolean addCourses(User user) {

    boolean success = false;
    // Show course options
    System.out.printf((Constant.COURSE_OPTIONS_TEXT), Constant.NUMBER_OF_COURSES);
    for (Course c : Course.values()) {
      System.out.println(c);
    }

    HashSet<Course> courses = new HashSet<>();

    // Select and add the defined no. of courses
    int courseNumber = Constant.COUNTER;
    while (courseNumber <= Constant.NUMBER_OF_COURSES) {
      success = addCourse(courses, courseNumber);
      if (!success) {
        break;
      }
      courseNumber++;
    }
    user.setCourses(courses);
    return success;
  }

  /**
   * Verify and add every single course to courses.
   *
   * @param courses      the courses
   * @param courseNumber the course number
   */
  public boolean addCourse(HashSet<Course> courses, int courseNumber) {

    int inputCounter = Constant.COUNTER;
    boolean success = false;
    while (inputCounter++ <= Constant.INVALID_INPUT_LIMIT) {
      // Input course from console
      System.out.printf(Constant.INPUT_COURSE_NAME_TEXT, courseNumber);
      String course = Constant.SCANNER.nextLine();

      // Check for valid and non-repeated course and if invalid course is entered, ask for input again
      if (validator.validateCourse(course)
          && validator.isCourseNotRepeated(courses, Course.valueOf(course.toUpperCase()))) {

        courses.add(Course.valueOf(course.toUpperCase()));
        success = true;
        break;

      } else {
        log.error(Constant.INVALID_COURSE_TEXT);
      }
    }
    if (inputCounter >= Constant.INVALID_INPUT_LIMIT) {
      System.out.println(Constant.INPUT_LIMIT_REACHED);
    }
    return success;
  }
}
