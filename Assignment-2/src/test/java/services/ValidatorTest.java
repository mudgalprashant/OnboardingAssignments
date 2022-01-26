package services;

import enums.Course;

import java.util.HashSet;
import java.util.PriorityQueue;

import models.User;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class ValidatorTest {
  private final Validator validator = new Validator();

  @Test
  void validateTask() {

    /**
     * Tests for valid tasks.
     */
    String task = "0";
    assertTrue(validator.validateTask(task));

    task = "1";
    assertTrue(validator.validateTask(task));

    task = "2";
    assertTrue(validator.validateTask(task));

    task = "3";
    assertTrue(validator.validateTask(task));

    task = "4";
    assertTrue(validator.validateTask(task));

    /**
     * Tests for invalid tasks.
     */
    task = "-1";
    assertFalse(validator.validateTask(task));

    task = "10";
    assertFalse(validator.validateTask(task));

    task = "random";
    assertFalse(validator.validateTask(task));

    task = "";
    assertFalse(validator.validateTask(task));
  }

  @Test
  void validateName() {
    /**
     * Tests for valid name.
     */
    String name = "Prashant";
    assertTrue(validator.validateName(name));

    name = "Prashant Mudgal";
    assertTrue(validator.validateName(name));

    /**
     * Tests for invalid name.
     */
    name = "";
    assertFalse(validator.validateName(name));

    name = "    ";
    assertFalse(validator.validateName(name));

    name = "12134";
    assertFalse(validator.validateName(name));

  }

  @Test
  void validateAge() {
    /**
     * Test for valid age.
     */
    String age = "21";
    assertTrue(validator.validateAge(age));

    /**
     * Tests for invalid age.
     */
    age = "-21";
    assertFalse(validator.validateAge(age));

    age = " ";
    assertFalse(validator.validateAge(age));

    age = "abc";
    assertFalse(validator.validateAge(age));

  }

  @Test
  void validateAddress() {
    /**
     * Test for valid address.
     */
    String address = "Morena - 476001";
    assertTrue(validator.validateAddress(address));

    /**
     * Test for invalid address.
     */
    address = "    ";
    assertFalse(validator.validateAddress(address));
  }

  @Test
  void validateRollNumber() {

    User user = User.builder().build();

    user.setName("Prashant");
    user.setAge(21);
    user.setAddress("Morena");
    user.setRollNumber("181060");

    HashSet<Course> courses = new HashSet<>();

    courses.add(Course.A);
    courses.add(Course.B);
    courses.add(Course.C);
    courses.add(Course.D);
    user.setCourses(courses);

    PriorityQueue<User> userList = new PriorityQueue<>();
    userList.add(user);

    /**
     * Tests for valid roll number.
     */

    String rollNumber = "181122";
    assertTrue(validator.validateRollNumber(userList, rollNumber));

    rollNumber = "1811225298347590";
    assertTrue(validator.validateRollNumber(userList, rollNumber));

    /**
     * Tests for invalid roll number.
     */
    rollNumber = "181060"; //Repeated
    assertFalse(validator.validateRollNumber(userList, rollNumber));

    rollNumber = "test"; //Not-numeric
    assertFalse(validator.validateRollNumber(userList, rollNumber));
  }

  @Test
  void validateCourse() {
    /**
     * Test for valid course.
     */
    String course = "a";
    assertTrue(validator.validateCourse(course));

    /**
     * Test for invalid course.
     */
    course = "   ";
    assertFalse(validator.validateCourse(course));

    course = "123";
    assertFalse(validator.validateCourse(course));

    course = "abcd";
    assertFalse(validator.validateCourse(course));

  }

  @Test
  void isCourseNotRepeated() {

    HashSet<Course> courses = new HashSet<>();

    courses.add(Course.A);
    courses.add(Course.B);

    /**
     * Tests for not repeated.
     */
    assertTrue(validator.isCourseNotRepeated(courses, Course.C));

    assertTrue(validator.isCourseNotRepeated(courses, Course.D));

    assertTrue(validator.isCourseNotRepeated(courses, Course.E));

    assertTrue(validator.isCourseNotRepeated(courses, Course.F));

    /**
     * Test for repeated.
     */

    assertFalse(validator.isCourseNotRepeated(courses, Course.A));

    assertFalse(validator.isCourseNotRepeated(courses, Course.B));

  }
}