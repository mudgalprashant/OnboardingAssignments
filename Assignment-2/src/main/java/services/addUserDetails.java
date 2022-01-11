package services;

import constants.Constant;
import enums.Course;
import models.User;

import java.util.HashSet;

public class addUserDetails {
    private final Validator validator = new Validator();

    public addUserDetails(User user){
        addName(user);
        addAge(user);
        addAddress(user);
        addRollNumber(user);
        addCourses(user);
    }

    /**
     * Add name to the user details
     */
    public void addName(User user){

        // Input name from console
        System.out.println(Constant.INPUT_NAME_TEXT);
        final String name = Constant.SCANNER.nextLine();

        // Check for valid name and if invalid name is entered, ask for input again
        if (validator.validateName(name)){
            user.setName(name);
        }else{
            System.out.println(Constant.INVALID_NAME_TEXT);
            addName(user);
        }
    }

    /**
     * Add age to user details
     */
    public void addAge(User user){

        // Input age from console
        System.out.println(Constant.INPUT_AGE_TEXT);
        final String age = Constant.SCANNER.nextLine();

        // Check for valid age and if invalid age is entered, ask for input again
        if (validator.validateAge(age)){
            int numericAge = Integer.parseInt(age);
            user.setAge(numericAge);
        }else{
            System.out.println(Constant.INVALID_AGE_TEXT);
            addAge(user);
        }
    }

    /**
     * Add address to user details
     */
    public void addAddress(User user){

        // Input address from console
        System.out.println(Constant.INPUT_ADDRESS_TEXT);
        String address = Constant.SCANNER.nextLine();

        // Check for valid address and if invalid address is entered, ask for input again
        if (validator.validateAddress(address)){
            user.setAddress(address);
        }else{
            System.out.println(Constant.INVALID_ADDRESS_TEXT);
            addAddress(user);
        }
    }

    /**
     * Add roll no. to user details
     */
    public void addRollNumber(User user){

        // Input roll No. from console
        System.out.println(Constant.INPUT_ROLL_NUMBER_TEXT);
        String rollNumber = Constant.SCANNER.nextLine();

        // Check for valid roll No. and if invalid roll No. is entered, ask for input again
        if (validator.validateRollNumber(rollNumber)){
            user.setRollNumber(rollNumber);
        }else{
            System.out.println(Constant.INVALID_ROLL_NUMBER_TEXT);
            addRollNumber(user);
        }
    }

    /**
     *  Add Courses to user details
     */
    public void addCourses(User user){

        // Show course options
        System.out.printf(Constant.COURSE_OPTIONS_TEXT, Constant.NUMBER_OF_COURSES);

        // Todo: printCourseOptions();

        HashSet<Course> courses = new HashSet<>();

        // Select and add the defined no. of courses
        int courseNumber = Constant.COUNTER;
        while(courseNumber <= Constant.NUMBER_OF_COURSES){
            addCourse(courses, courseNumber);
            courseNumber++;
        }

    }

    /**
     * Verify and add every single course to courses
     */
    public void addCourse(HashSet<Course> courses, int courseNumber){

        // Input course from console
        System.out.printf(Constant.INPUT_COURSE_NAME_TEXT, courseNumber);
        String course = Constant.SCANNER.nextLine();

        // Check for valid and non-repeated course and if invalid course is entered, ask for input again
        if (validator.validateCourse(course)
                || courses.contains(Course.valueOf(course.toUpperCase()))){
            courses.add(Course.valueOf(course.toUpperCase()));
        } else {
            System.out.println(Constant.INVALID_COURSE_TEXT);
            addCourse(courses, courseNumber);
        }
    }
}
