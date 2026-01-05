package org.example;

import java.util.ArrayList;
import java.util.Scanner;

// Faculty class to represents a faculty member in the college database
public class Faculty {
    // Private fields to store faculty information
    private int ID;
    private String firstName;
    private String lastName;
    private String department;



    // Constructor to creates a new Faculty object with all required information
    public Faculty(int ID, String firstName, String lastName, String department) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }


    // Getter and Setter methods to allow access to private fields
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //toString method  converts Faculty object to a readable string

    @Override
    public String toString() {
        return "Faculty{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

