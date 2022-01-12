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
  private String name;
  private int age;
  private String address;
  private String rollNumber;
  private HashSet<Course> courses;


  @Override
  public String toString() {
    return "User{"
        + "name='" + name + "'"
        + ", age=" + age
        + ", address='" + address + "'"
        + ", rollNumber=" + rollNumber
        + ", courses=" + courses
        + "}\n";
  }

  @Override
  public int compareTo(User newUser) {
    if (this.name.compareTo(newUser.getName()) == 0) {
      return this.rollNumber.compareTo(newUser.getRollNumber());
    } else {
      return this.name.compareTo(newUser.getName());
    }
  }


}
