package models;

import enums.Course;
import lombok.Builder;
import lombok.Data;


import java.io.Serializable;
import java.util.HashSet;

@Builder
@Data
public class User implements Serializable {
    private String name;
    private int age;
    private String address;
    private String rollNumber;
    private HashSet<Course> courses;


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


}
