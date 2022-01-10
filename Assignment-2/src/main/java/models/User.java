package models;

import constants.Constant;
import enums.CourseName;
import lombok.Getter;
import lombok.Setter;
import services.Validator;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class User implements Serializable {
    private String name;
    private int age;
    private String address;
    private String rollNumber;
    private Set<Course> courses;


    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", address='" + address + '\''
                + ", rollNumber=" + rollNumber
                + ", courses=" + courses
                + '}';
    }

    /**
     * Add name to the user details
     */
    public void addUserName(){

        // Input name from console
        System.out.println(Constant.INPUT_NAME_TEXT);
        String name = Constant.SCANNER.nextLine();

        // Check for valid name and if invalid name is entered, ask for input again
        if (Validator.validateName(name)){
            this.setName(name);
        }else{
            System.out.println(Constant.INVALID_NAME_TEXT);
            this.addUserName();
        }
    }

    /**
     * Add age to user details
     */
    public void addUserAge(){

        // Input age from console
        System.out.println(Constant.INPUT_AGE_TEXT);
        String age = Constant.SCANNER.nextLine();

        // Check for valid age and if invalid age is entered, ask for input again
        if (Validator.validateAge(age)){
            int numericAge = Integer.parseInt(age);
            this.setAge(numericAge);
        }else{
            System.out.println(Constant.INVALID_AGE_TEXT);
            this.addUserAge();
        }
    }

    /**
     * Add address to user details
     */
    public void addUserAddress(){

        // Input address from console
        System.out.println(Constant.INPUT_ADDRESS_TEXT);
        String address = Constant.SCANNER.nextLine();

        // Check for valid address and if invalid address is entered, ask for input again
        if (Validator.validateAddress(address)){
            this.setAddress(address);
        }else{
            System.out.println(Constant.INVALID_ADDRESS_TEXT);
            this.addUserAddress();
        }
    }

    /**
     * Add roll no. to user details
     */
    public void addUserRollNumber(){

        // Input roll No. from console
        System.out.println(Constant.INPUT_ROLL_NUMBER_TEXT);
        String rollNumber = Constant.SCANNER.nextLine();

        // Check for valid roll No. and if invalid roll No. is entered, ask for input again
        if (Validator.validateRollNumber(rollNumber)){
            this.setRollNumber(rollNumber);
        }else{
            System.out.println(Constant.INVALID_ROLL_NUMBER_TEXT);
            this.addUserRollNumber();
        }
    }

    /**
     *  Add Courses to user details
     */
    public void addUserCourses(){

        // Show course options
        System.out.printf(Constant.COURSE_OPTIONS_TEXT, Constant.NUMBER_OF_COURSES);

        // Todo: printCourseOptions();

        // Select and add the defined no. of courses
        int courseNumber = Constant.COUNTER;
        while(courseNumber <= Constant.NUMBER_OF_COURSES){
            this.addUserCourse(courseNumber);
            courseNumber++;
        }

    }

    /**
     * Verify and add every single course to courses
     */
    public void addUserCourse(int courseNumber){

        // Input course from console
        System.out.printf(Constant.INPUT_COURSE_NAME_TEXT, courseNumber);
        String courseName = Constant.SCANNER.nextLine();

        // Check for valid course and if invalid course is entered, ask for input again
        if (Validator.validateCourse(courseName)){
            Course course = new Course();
            course.setName(CourseName.valueOf(courseName.toUpperCase()));
            this.courses.add(course);
        }else{
            System.out.println(Constant.INVALID_COURSE_TEXT);
            this.addUserCourse(courseNumber);
        }
    }
}
