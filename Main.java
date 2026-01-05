package org.example;

import org.example.CollegeDB;
import org.example.student;
import org.example.Faculty;
import org.example.Course;
import org.example.Section;
import java.util.*;


public class Main {

    public static student getStudentInfo(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter firstname, lastname, major, gpa");
        String text = keyboard.nextLine();
        String[] split = text.split(",");
        String firstName = split[0].trim();
        String lastName = split[1].trim();
        String major = split[2].trim();
        double gpa = Double.parseDouble(split[3]);
        return new student(0, firstName,lastName,major,gpa);
    }

    public static Faculty getFacultyInfo(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter firstname, lastname, department");
        String text = keyboard.nextLine();
        String[] split = text.split(",");
        String firstName = split[0].trim();
        String lastName = split[1].trim();
        String department = split[2].trim();
        return new Faculty(0, firstName, lastName, department);
    }

    public static Course getCourseInfo(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter department, num, title, credits");
        String text = keyboard.nextLine();
        String[] split = text.split(",");
        String dept = split[0].trim();
        int num = Integer.parseInt(split[1].trim());
        String title = split[2].trim();
        String credits = split[3].trim();
        return new Course(0, dept, num, title, credits);
    }

    public static Section getSectionInfo(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter courseID, facultyID, semester, day, startTime, endTime, modality");
        String text = keyboard.nextLine();
        String[] split = text.split(",");
        int courseID = Integer.parseInt(split[0].trim());
        int facultyID = Integer.parseInt(split[1].trim());
        String semester = split[2].trim();
        String day = split[3].trim();
        String startT = split[4].trim();
        String endT = split[5].trim();
        String modality = split[6].trim();
        return new Section(0, courseID, facultyID, semester, day, startT, endT, modality);
    }

    public static Enrollment getEnrollmentInfo(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter studentID, sectionID, grade");
        String text = keyboard.nextLine();
        String[] split = text.split(",");
        int studentID = Integer.parseInt(split[0].trim());
        int sectionID = Integer.parseInt(split[1].trim());
        String grade = split[2].trim();
        return new Enrollment(0, studentID, sectionID, grade, "");
    }

    public static void handleStudentMenu(Scanner keyboard){
        int choice = 0;
        while ( choice != 4 ) {

            System.out.println("Enter 1 to add, 2 to read, 3 to update, 4 to quit");
            choice = Integer.parseInt(keyboard.nextLine());

            if (choice == 1) {
                CollegeDB.addStudent(getStudentInfo());
            } else if (choice == 2) {
                ArrayList<student> students = CollegeDB.getStudents();

                for (student student : students) {
                    System.out.println(student);
                }

            }
            else if (choice == 3) {

                System.out.println("Enter ID of student to update");
                int id =  Integer.parseInt(keyboard.nextLine());

                ArrayList<student> students = CollegeDB.getStudent(id);

                boolean foundStudent = false;
                for ( student student : students) {
                    if ( student.getID() == id ) {
                        System.out.println(student);
                        student studentInfo = getStudentInfo();
                        studentInfo.setID(id);
                        CollegeDB.updateStudent(studentInfo);
                        foundStudent = true;
                        break;
                    }
                }
                if ( !foundStudent ) {
                    System.out.println("ID not found!");
                }
            }
        }
    }

    public static void handleFacultyMenu(Scanner keyboard){
        int choice = 0;
        while (choice != 4) {

            System.out.println("Enter 1 to add, 2 to read, 3 to update, 4 to quit");
            choice = Integer.parseInt(keyboard.nextLine());

            if (choice == 1) {
                CollegeDB.addFaculty(getFacultyInfo());
            } else if (choice == 2) {
                ArrayList<Faculty> facultyList = CollegeDB.getFaculty();

                for (Faculty faculty : facultyList) {
                    System.out.println(faculty);
                }

            }
            else if (choice == 3) {

                System.out.println("Enter ID of faculty member to update");
                int id = Integer.parseInt(keyboard.nextLine());

                ArrayList<Faculty> facultyList = CollegeDB.getFaculty(id);

                boolean foundFaculty = false;
                for (Faculty faculty : facultyList) {
                    if (faculty.getID() == id) {
                        System.out.println(faculty);
                        Faculty facultyInfo = getFacultyInfo();
                        facultyInfo.setID(id);
                        CollegeDB.updateFaculty(facultyInfo);
                        foundFaculty = true;
                        break;
                    }
                }
                if (!foundFaculty) {
                    System.out.println("ID not found!");
                }
            }
        }
    }

    public static void handleCourseMenu(Scanner keyboard){
        int choice = 0;
        while (choice != 4) {

            System.out.println("Enter 1 to add, 2 to read, 3 to update, 4 to quit");
            choice = Integer.parseInt(keyboard.nextLine());

            if (choice == 1) {
                CollegeDB.addCourse(getCourseInfo());
            } else if (choice == 2) {
                ArrayList<Course> courses = CollegeDB.getCourses();

                for (Course course : courses) {
                    System.out.println(course);
                }

            }
            else if (choice == 3) {

                System.out.println("Enter ID of course to update");
                int id = Integer.parseInt(keyboard.nextLine());

                ArrayList<Course> courses = CollegeDB.getCourse(id);

                boolean foundCourse = false;
                for (Course course : courses) {
                    if (course.getID() == id) {
                        System.out.println(course);
                        Course courseInfo = getCourseInfo();
                        courseInfo.setID(id);
                        CollegeDB.updateCourse(courseInfo);
                        foundCourse = true;
                        break;
                    }
                }
                if (!foundCourse) {
                    System.out.println("ID not found!");
                }
            }
        }
    }

    public static void handleSectionMenu(Scanner keyboard){
        int choice = 0;
        while (choice != 4) {

            System.out.println("Enter 1 to add, 2 to read, 3 to update, 4 to quit");
            choice = Integer.parseInt(keyboard.nextLine());

            if (choice == 1) {
                CollegeDB.addSection(getSectionInfo());
            } else if (choice == 2) {
                ArrayList<Section> sections = CollegeDB.getSections();

                for (Section section : sections) {
                    System.out.println(section);
                }

            }
            else if (choice == 3) {

                System.out.println("Enter ID of section to update");
                int id = Integer.parseInt(keyboard.nextLine());

                ArrayList<Section> sections = CollegeDB.getSection(id);

                boolean foundSection = false;
                for (Section section : sections) {
                    if (section.getID() == id) {
                        System.out.println(section);
                        Section sectionInfo = getSectionInfo();
                        sectionInfo.setID(id);
                        CollegeDB.updateSection(sectionInfo);
                        foundSection = true;
                        break;
                    }
                }
                if (!foundSection) {
                    System.out.println("ID not found!");
                }
            }
        }
    }

    public static void handleEnrollmentMenu(Scanner keyboard){
        int choice = 0;
        while (choice != 5) {

            System.out.println("Enter 1 to add, 2 to read, 3 to update, 4 to delete, 5 to quit");
            choice = Integer.parseInt(keyboard.nextLine());

            if (choice == 1) {
                CollegeDB.addEnrollment(getEnrollmentInfo());
            } else if (choice == 2) {
                ArrayList<Enrollment> enrollments = CollegeDB.getEnrollments();

                for (Enrollment enrollment : enrollments) {
                    System.out.println(enrollment);
                }

            }
            else if (choice == 3) {

                System.out.println("Enter ID of enrollment to update");
                int id = Integer.parseInt(keyboard.nextLine());

                ArrayList<Enrollment> enrollments = CollegeDB.getEnrollment(id);

                boolean foundEnrollment = false;
                for (Enrollment enrollment : enrollments) {
                    if (enrollment.getID() == id) {
                        System.out.println(enrollment);
                        Enrollment enrollmentInfo = getEnrollmentInfo();
                        enrollmentInfo.setID(id);
                        CollegeDB.updateEnrollment(enrollmentInfo);
                        foundEnrollment = true;
                        break;
                    }
                }
                if (!foundEnrollment) {
                    System.out.println("ID not found!");
                }
            }
            else if (choice == 4) {
                // DELETE - Only for Enrollment!
                System.out.println("Enter ID of enrollment to delete");
                int id = Integer.parseInt(keyboard.nextLine());

                ArrayList<Enrollment> enrollments = CollegeDB.getEnrollment(id);

                boolean foundEnrollment = false;
                for (Enrollment enrollment : enrollments) {
                    if (enrollment.getID() == id) {
                        System.out.println(enrollment);
                        System.out.println("Are you sure you want to delete? (yes/no)");
                        String confirm = keyboard.nextLine();
                        if (confirm.equalsIgnoreCase("yes")) {
                            CollegeDB.deleteEnrollment(id);
                            System.out.println("Enrollment deleted successfully!");
                        }
                        foundEnrollment = true;
                        break;
                    }
                }
                if (!foundEnrollment) {
                    System.out.println("ID not found!");
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {

            // 1) CHOOSE DATABASE TO EDIT
            System.out.println("\nChoose which database to work with: ");
            System.out.println("1. Students");
            System.out.println("2. Faculty");
            System.out.println("3. Course");
            System.out.println("4. Section");
            System.out.println("5. Enrollment");
            System.out.println("6. Quit");

            System.out.println("Enter choice: ");
            choice = Integer.parseInt(keyboard.nextLine());

            // 2) EDIT THAT DATABASE
            if (choice == 1) {
                handleStudentMenu(keyboard);
            } else if (choice == 2) {
                handleFacultyMenu(keyboard);
            } else if (choice == 3) {
                handleCourseMenu(keyboard);
            } else if (choice == 4) {
                handleSectionMenu(keyboard);
            } else if (choice == 5) {
                handleEnrollmentMenu(keyboard);
            } else if (choice == 6) {
                System.out.println("BYEEEEEEE!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Close the scanner when we're done
        keyboard.close();
    }

}