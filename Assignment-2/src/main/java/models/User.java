package models;

import enums.Course;

import java.io.Serializable;
import java.util.HashSet;

import lombok.Builder;
import lombok.Data;


/**
 * The type User.
 */
@Builder
@Data
public class User implements Serializable, Comparable<User> {
  /**
   * Name of user
   */
  private String name;
  /**
   * Age of user
   */
  private int age;
  /**
   * Address of user
   */
  private String address;
  /**
   * Roll number of user
   */
  private String rollNumber;
  /**
   * Courses selected by the user
   */
  private HashSet<Course> courses;


  @Override
  public String toString() {
    return (new StringBuilder())
        .append("User{")
        .append("name='")
        .append(name)
        .append("'")
        .append(", age=")
        .append(age)
        .append(", address='")
        .append(address)
        .append("'")
        .append(", rollNumber=")
        .append(rollNumber)
        .append(", courses=")
        .append(courses)
        .append("}\n").toString();
  }

  /**
   *
   * @param newUser : to be compared with the base user
   * @return positive integer, negative integer or zero
   */
  @Override
  public int compareTo(User newUser) {
    if (this.name.compareTo(newUser.getName()) == 0) {
      return this.rollNumber.compareTo(newUser.getRollNumber());
    } else {
      return this.name.compareTo(newUser.getName());
    }
  }


}
