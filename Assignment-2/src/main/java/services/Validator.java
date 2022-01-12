package services;

import constants.Constant;
import enums.Course;

import exceptions.BlankAddressException;
import exceptions.BlankNameException;
import exceptions.InvalidTaskException;
import exceptions.NonAlphabeticNameException;
import exceptions.NonNumericRollNumberException;
import exceptions.NonPositiveAgeException;
import exceptions.RepeatedCourseException;

import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Validate the inputs from console.
 */
public class Validator {

  private final Log log = LogFactory.getLog(Validator.class);

  /**
   * Validates the task number input by the user.
   *
   * @param task the task
   * @return the boolean
   */
  public boolean validateTask(String task) {
    try {
      final int taskNumber = Integer.parseInt(task);
      if (taskNumber >= Constant.NUMBER_OF_TASKS || taskNumber < 0) {
        throw new InvalidTaskException(Constant.INVALID_TASK_TEXT);
      }
    } catch (InvalidTaskException | NumberFormatException exception) {
      log.error(exception);
      return false;
    }
    return true;
  }

  /**
   * Validates name against empty string and non-alphabetic string.
   *
   * @param name the name
   * @return the boolean
   */
  public boolean validateName(String name) {
    try {
      if (name.isBlank()) {
        throw new BlankNameException(Constant.INVALID_NAME_TEXT);
      } else if (!name.matches(Constant.REGEX_FOR_ALPHABETIC_STRING_WITH_SPACES)) {
        throw new NonAlphabeticNameException(Constant.INVALID_NAME_TEXT);
      }
      return true;
    } catch (BlankNameException | NonAlphabeticNameException exception) {
      log.error(exception);
      return false;
    }
  }

  /**
   * Validates age against negative or non-integer entry.
   *
   * @param age the age
   * @return the boolean
   */
  public boolean validateAge(String age) {
    try {
      final int numericAge = Integer.parseInt(age);
      if (numericAge <= Constant.MINIMUM_AGE) {
        throw new NonPositiveAgeException(Constant.INVALID_AGE_TEXT);
      }
      return true;
    } catch (NumberFormatException | NonPositiveAgeException exception) {
      log.error(exception);
      return false;
    }
  }

  /**
   * Validates Address against empty string.
   *
   * @param address the address
   * @return the boolean
   */
  public boolean validateAddress(String address) {
    try {
      if (address.isBlank()) {
        throw new BlankAddressException(Constant.INVALID_ADDRESS_TEXT);
      }
      return true;
    } catch (BlankAddressException exception) {
      log.error(exception);
      return false;
    }
  }

  /**
   * Validate roll no. against empty or non-numeric entry.
   *
   * @param rollNumber the roll number
   * @return the boolean
   */
  public boolean validateRollNumber(String rollNumber) {
    try {
      if (!rollNumber.matches(Constant.REGEX_FOR_NUMERIC_STRING)) {
        throw new NonNumericRollNumberException(Constant.INVALID_ROLL_NUMBER_TEXT);
      }
    } catch (NonNumericRollNumberException exception) {
      log.error(exception);
      return false;
    }
    return true;
  }

  /**
   * Validate courses against available courses.
   *
   * @param course the course
   * @return the boolean
   */
  public boolean validateCourse(String course) {
    try {
      Course.valueOf(course.toUpperCase());
    } catch (IllegalArgumentException exception) {
      log.error(exception);
      return false;
    }
    return true;
  }

  /**
   * Check if user is repeating the course.
   *
   * @param courses the courses
   * @param course  the course
   * @return the boolean
   */
  public boolean isCourseNotRepeated(HashSet<Course> courses, Course course) {
    try {
      if (courses.contains(course)) {
        throw new RepeatedCourseException(Constant.REPEATED_COURSE_TEXT);
      }
    } catch (RepeatedCourseException exception) {
      log.error(exception);
      return false;
    }
    return true;
  }
}
