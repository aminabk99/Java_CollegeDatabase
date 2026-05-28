<div align="center">

# 🎓 Java College Database
### OOP-Based Academic Data Management System

A Java console application built as a **team project** that models a college's core academic entities — students, faculty, courses, sections, and enrollments — with full **CRUD operations** backed by a SQLite database.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-Database-003B57?style=for-the-badge&logo=sqlite&logoColor=white)
![OOP](https://img.shields.io/badge/Paradigm-OOP-6A0DAD?style=for-the-badge&logo=buffer&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-SQL_Driver-CC0000?style=for-the-badge&logo=java&logoColor=white)

</div>

---

## How It Works

1. The program launches a console menu where you select which entity to manage: Students, Faculty, Courses, Sections, or Enrollments
2. For each entity you can **add**, **read**, or **update** records (Enrollments also support **delete**)
3. All operations go through `CollegeDB.java` which uses **JDBC PreparedStatements** to safely execute SQL against a local `college.db` SQLite file
4. Each entity is modeled as its own Java class with private fields, getters, setters, and a `toString()` override

**Entities managed:** 👤 Students · 🧑‍🏫 Faculty · 📚 Courses · 🗓️ Sections · 📝 Enrollments

---

## Setup

**Requirements:** Java 17+ · SQLite JDBC driver

**1. Clone the repo**
```bash
git clone https://github.com/aminabk99/Java_CollegeDatabase
cd Java_CollegeDatabase
```

**2. Add the SQLite JDBC driver to your classpath, then compile**
```bash
javac -cp ".;sqlite-jdbc.jar" *.java       # Windows
javac -cp ".:sqlite-jdbc.jar" *.java       # Mac/Linux
```

**3. Run**
```bash
java -cp ".;sqlite-jdbc.jar" Main          # Windows
java -cp ".:sqlite-jdbc.jar" Main          # Mac/Linux
```

---

```
## Project Structure
Java_CollegeDatabase/
├── Main.java          # Console menu and user input handling
├── CollegeDB.java     # All CRUD database operations via JDBC
├── Student.java       # Student entity model
├── Faculty.java       # Faculty entity model
├── Course.java        # Course entity model
├── Section.java       # Section entity model (links courses + faculty)
└── Enrollment.java    # Enrollment entity model (links students + sections)

```
---

## Hardest Part
**Managing relational integrity manually** — enrollments reference sections, sections reference both courses and faculty, so deleting or updating a parent record required carefully thinking through cascading effects without a full ORM to handle it automatically.

## Most Interesting
**PreparedStatements over raw SQL strings** — every query uses `?` placeholders rather than string concatenation, which prevents SQL injection and makes the parameter binding explicit. It's a small habit that has massive real-world security implications.

---

## Files

- `Main.java` — entry point and interactive console menu
- `CollegeDB.java` — centralized database layer with all CRUD logic
- `Student.java`, `Faculty.java`, `Course.java`, `Section.java`, `Enrollment.java` — entity models

---

<div align="center">
  <sub>Built by <a href="https://github.com/aminabk99">Amina Bilal</a> · <a href="https://linkedin.com/in/amina-bilal-926340382">LinkedIn</a></sub>
</div>
