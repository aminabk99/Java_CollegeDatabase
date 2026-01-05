package org.example;

public class Course {
    private int ID;
    private String department;
    private int num;
    private String title;
    private String credits;

    public Course(int ID, String department, int num, String title, String credits) {
        this.ID = ID;
        this.department = department;
        this.num = num;
        this.title = title;
        this.credits = credits;
    }

    public String getCredits() {
        return credits;
    }

    public String getTitle() {
        return title;
    }

    public int getNum() {
        return num;
    }

    public String getDepartment() {
        return department;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", department='" + department + '\'' +
                ", num=" + num +
                ", title=" + title +
                ", credits=" + credits +
                '}';
    }
}
