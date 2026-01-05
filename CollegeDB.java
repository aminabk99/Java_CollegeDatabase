package org.example;

import java.sql.*;
import java.util.*;
import java.util.ArrayList;

public class CollegeDB {

    private static Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:college.db";
        Connection connection = DriverManager.getConnection(dbUrl);
        return connection;
    }

    public static void updateStudent(student student) {
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update Student set FirstName = ?, LastName = ?, Major = ?, GPA = ? where ID = ?");

            // first value is index 1, it's ok to cry
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getMajor());
            preparedStatement.setDouble(4, student.getGpa());
            preparedStatement.setInt(5, student.getID());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static ArrayList<student> getStudent(int id) {
        ArrayList<student> students = new ArrayList<student>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Student where id = ?");

            preparedStatement.setInt(1, id);

            ResultSet studentsQuery = preparedStatement.executeQuery();

            while (studentsQuery.next()) {
                String firstName = studentsQuery.getString("FirstName");
                String lastName = studentsQuery.getString("LastName");
                String major = studentsQuery.getString("Major");
                double gpa = studentsQuery.getDouble("GPA");

                students.add(new student(id, firstName, lastName, major, gpa));
            }
            studentsQuery.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return students;
    }

    public static ArrayList<student> getStudents() {
        ArrayList<student> students = new ArrayList<student>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet studentsQuery = statement.executeQuery(
                    "SELECT * FROM Student");

            while (studentsQuery.next()) {
                int id = studentsQuery.getInt("ID");
                String firstName = studentsQuery.getString("FirstName");
                String lastName = studentsQuery.getString("LastName");
                String major = studentsQuery.getString("Major");
                double gpa = studentsQuery.getDouble("GPA");

                students.add(new student(id, firstName, lastName, major, gpa));
            }
            studentsQuery.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return students;
    }

    public static void addStudent(student student) {
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into Student ( FirstName, LastName, Major, GPA ) values ( ?, ?, ?, ? )");

            // first value is index 1, it's ok to cry
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getMajor());
            preparedStatement.setDouble(4, student.getGpa());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void addFaculty(Faculty faculty) {
        try {
            // Get connection to database
            Connection connection = getConnection();

            // The ? symbols are placeholders for values we'll add later
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT into Faculty ( FirstName, LastName, Department ) values ( ?, ?, ? )");

            // Set the values for each ? placeholder
            // Note: index starts at 1, not 0
            preparedStatement.setString(1, faculty.getFirstName());
            preparedStatement.setString(2, faculty.getLastName());
            preparedStatement.setString(3, faculty.getDepartment());

            // Execute the insert
            preparedStatement.execute();

            // Always close connections to free up resources
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    // READ operation for all Faculty, Gets all faculty members from the database

    public static ArrayList<Faculty> getFaculty() {
        ArrayList<Faculty> facultyList = new ArrayList<Faculty>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            // Execute query to get all faculty
            ResultSet facultyQuery = statement.executeQuery("SELECT * FROM Faculty");

            // Loop through each row in the results
            while (facultyQuery.next()) {
                // Extract data from current row
                int id = facultyQuery.getInt("ID");
                String firstName = facultyQuery.getString("FirstName");
                String lastName = facultyQuery.getString("LastName");
                String department = facultyQuery.getString("Department");

                // Create Faculty object and add to list
                facultyList.add(new Faculty(id, firstName, lastName, department));
            }
            facultyQuery.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return facultyList;
    }

    // READ operation for a single Faculty member by ID
    public static ArrayList<Faculty> getFaculty(int id) {
        ArrayList<Faculty> facultyList = new ArrayList<Faculty>();

        try {
            Connection connection = getConnection();

            // Use PreparedStatement to safely query by ID
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Faculty where id = ?");

            preparedStatement.setInt(1, id);

            // executeQuery() returns a ResultSet
            ResultSet facultyQuery = preparedStatement.executeQuery();

            // Loop through all results (should be 0 or 1 for a specific ID)
            while (facultyQuery.next()) {
                String firstName = facultyQuery.getString("FirstName");
                String lastName = facultyQuery.getString("LastName");
                String department = facultyQuery.getString("Department");

                facultyList.add(new Faculty(id, firstName, lastName, department));
            }
            facultyQuery.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return facultyList;
    }

    // UPDATE operation for Faculty

    public static void updateFaculty(Faculty faculty) {
        try {
            Connection connection = getConnection();

            // PreparedStatement with UPDATE query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update Faculty set FirstName = ?, LastName = ?, Department = ? where ID = ?");

            // Set the values for each ? placeholder
            preparedStatement.setString(1, faculty.getFirstName());
            preparedStatement.setString(2, faculty.getLastName());
            preparedStatement.setString(3, faculty.getDepartment());
            preparedStatement.setInt(4, faculty.getID());  // WHERE clause - which record to update

            // Execute the update
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static ArrayList<Course> getCourses(){
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet coursesQuery = statement.executeQuery(
                    "SELECT * FROM Course");

            while (coursesQuery.next()) {
                int id = coursesQuery.getInt("ID");
                String department = coursesQuery.getString("Department");
                int num = coursesQuery.getInt("Number");
                String title = coursesQuery.getString("Title");
                String course = String.valueOf(coursesQuery.getInt("Credits"));

                courses.add(new Course(id, department, num, title, course));
            }
            coursesQuery.close();

            connection.close();
        } catch ( SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return courses;
    }

    public static ArrayList<Course> getCourse(int id){
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Course where ID = ?");

            preparedStatement.setInt(1, id);

            ResultSet coursesQuery = preparedStatement.executeQuery();

            while (coursesQuery.next()) {
                String department = coursesQuery.getString("Department");
                int num = coursesQuery.getInt("Number");
                String title = coursesQuery.getString("Title");
                String course = String.valueOf(coursesQuery.getInt("Credits"));

                courses.add(new Course(id, department, num, title, course));
            }
            coursesQuery.close();

            connection.close();
        } catch ( SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return courses;
    }

    public static void updateCourse(Course course){
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update Course set Department = ?, Number = ?, Title = ?, Credits = ? where ID = ?");

            preparedStatement.setString(1, course.getDepartment());
            preparedStatement.setInt(2, course.getNum());
            preparedStatement.setString(3, course.getTitle());
            preparedStatement.setInt(4, Integer.parseInt(course.getCredits()));
            preparedStatement.setInt(5, course.getID());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void updateSection(Section section){
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update Section set CourseID = ?, FacultyID = ?, Semester = ?, Day = ?, StartTime = ?, EndTime = ?, Modality = ? where ID = ?");

            preparedStatement.setInt(1, section.getCourseID());
            preparedStatement.setInt(2, section.getFacultyID());
            preparedStatement.setString(3, section.getSemester());
            preparedStatement.setString(4, section.getDay());
            preparedStatement.setString(5, section.getStartT());
            preparedStatement.setString(6, section.getEndT());
            preparedStatement.setString(7, section.getModality());
            preparedStatement.setInt(8, section.getID());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static ArrayList<Section> getSections(){
        ArrayList<Section> sections = new ArrayList<Section>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet sectionsQuery = statement.executeQuery(
                    "SELECT * FROM Section");

            while (sectionsQuery.next()) {
                int id = sectionsQuery.getInt("ID");
                int courseID = sectionsQuery.getInt("CourseID");
                int facultyID = sectionsQuery.getInt("FacultyID");
                String semester = sectionsQuery.getString("Semester");
                String day = sectionsQuery.getString("Day");
                String startT = sectionsQuery.getString("StartTime");
                String endT = sectionsQuery.getString("EndTime");
                String modality = sectionsQuery.getString("Modality");

                sections.add(new Section(id, courseID, facultyID, semester, day, startT, endT, modality));
            }
            sectionsQuery.close();

            connection.close();
        } catch ( SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return sections;
    }

    public static ArrayList<Section> getSection(int id){
        ArrayList<Section> sections = new ArrayList<Section>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Section where ID = ?");

            preparedStatement.setInt(1, id);

            ResultSet sectionsQuery = preparedStatement.executeQuery();

            while (sectionsQuery.next()) {
                int courseID = sectionsQuery.getInt("CourseID");
                int facultyID = sectionsQuery.getInt("FacultyID");
                String semester = sectionsQuery.getString("Semester");
                String day = sectionsQuery.getString("Day");
                String startT = sectionsQuery.getString("StartTime");
                String endT = sectionsQuery.getString("EndTime");
                String modality = sectionsQuery.getString("Modality");

                sections.add(new Section(id, courseID, facultyID, semester, day, startT, endT, modality));
            }
            sectionsQuery.close();

            connection.close();
        } catch ( SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return sections;
    }

    public static void addCourse(Course course){
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT into Course ( Department, Number, Title, Credits ) values ( ?, ?, ?, ? )");

            preparedStatement.setString(1, course.getDepartment());
            preparedStatement.setInt(2, course.getNum());
            preparedStatement.setString(3, course.getTitle());
            preparedStatement.setInt(4, Integer.parseInt(course.getCredits()));

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void addSection(Section section){
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT into Section ( CourseID, FacultyID, Semester, Day, StartTime, EndTime, Modality ) values ( ?, ?, ?, ?, ?, ?, ? )");

            preparedStatement.setInt(1, section.getCourseID());
            preparedStatement.setInt(2, section.getFacultyID());
            preparedStatement.setString(3, section.getSemester());
            preparedStatement.setString(4, section.getDay());
            preparedStatement.setString(5, section.getStartT());
            preparedStatement.setString(6, section.getEndT());
            preparedStatement.setString(7, section.getModality());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void addEnrollment(Enrollment enrollment){
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT into Enrollment ( StudentID, SectionID, Grade ) values ( ?, ?, ? )");

            preparedStatement.setInt(1, enrollment.getStudentID());
            preparedStatement.setInt(2, enrollment.getSectionID());
            preparedStatement.setString(3, enrollment.getCourseName());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static ArrayList<Enrollment> getEnrollments(){
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet enrollmentsQuery = statement.executeQuery(
                    "SELECT * FROM Enrollment");

            while (enrollmentsQuery.next()) {
                int id = enrollmentsQuery.getInt("ID");
                int studentID = enrollmentsQuery.getInt("StudentID");
                int sectionID = enrollmentsQuery.getInt("SectionID");
                String grade = enrollmentsQuery.getString("Grade");

                enrollments.add(new Enrollment(id, studentID, sectionID, grade, ""));
            }
            enrollmentsQuery.close();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return enrollments;
    }

    public static ArrayList<Enrollment> getEnrollment(int id){
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Enrollment where ID = ?");

            preparedStatement.setInt(1, id);

            ResultSet enrollmentsQuery = preparedStatement.executeQuery();

            while (enrollmentsQuery.next()) {
                int studentID = enrollmentsQuery.getInt("StudentID");
                int sectionID = enrollmentsQuery.getInt("SectionID");
                String grade = enrollmentsQuery.getString("Grade");

                enrollments.add(new Enrollment(id, studentID, sectionID, grade, ""));
            }
            enrollmentsQuery.close();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }

        return enrollments;
    }

    public static void updateEnrollment(Enrollment enrollment){
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update Enrollment set StudentID = ?, SectionID = ?, Grade = ? where ID = ?");

            preparedStatement.setInt(1, enrollment.getStudentID());
            preparedStatement.setInt(2, enrollment.getSectionID());
            preparedStatement.setString(3, enrollment.getCourseName());
            preparedStatement.setInt(4, enrollment.getID());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void deleteEnrollment(int id){
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Enrollment where ID = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
