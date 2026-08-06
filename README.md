# Student Success Intelligence Platform (SSIP)

## Overview

Student Success Intelligence Platform (SSIP) is a Python-based console application developed to manage and organize student information using SQLite as the database.

The project is divided into different modules, where each module is responsible for handling a specific part of student data. The application follows a menu-driven approach and performs CRUD (Create, Read, Update and Delete) operations on the stored records.

---

## Technologies Used
 
- Python 3
- SQLite3
- Git
- GitHub
- Visual Studio Code

---

## Project Structure

```
Student-Success-Intelligence-Platform/
│
├── database/
│   └── ssip.db
│
├── src/
│   ├── database.py
│   ├── models.py
│   ├── menu.py
│   ├── student.py
│   ├── attendance.py
│   ├── academic.py
│   └── lifestyle.py
│
├── main.py
├── README.md
├── requirements.txt
└── .gitignore
```

---

# Modules

## Module 1 – Database Setup

- SQLite database connection
- Database initialization
- Table creation
- Project structure setup

---

## Module 2 – Student Management

Features

- Add Student
- View Students
- Search Student
- Update Student
- Delete Student

Student Information

- Full Name
- Gender
- Department
- Semester
- Email
- Phone Number
- Admission Year

---

## Module 3 – Attendance Management

Features

- Add Attendance
- View Attendance
- Search Attendance
- Update Attendance
- Delete Attendance
- Attendance Report

Attendance Information

- Student ID
- Attendance Percentage

---

## Module 4 – Academic Management

Features

- Add Academic Record
- View Academic Records
- Search Academic Record
- Update Academic Record
- Delete Academic Record
- Academic Report

Academic Information

- Student ID
- CGPA
- Backlogs
- Quiz Average

---

## Module 5 – Lifestyle Management

Features

- Add Lifestyle Record
- View Lifestyle Records
- Search Lifestyle Record
- Update Lifestyle Record
- Delete Lifestyle Record
- Lifestyle Report

Lifestyle Information

- Student ID
- Sleep Hours
- Water Intake
- Exercise Minutes
- Screen Time

---

# Database Tables

The project currently uses the following tables:

- students
- attendance
- academics
- lifestyle

---

# How to Run

Clone the repository

```bash
git clone https://github.com/Mohitjeet007/Student-Success-Intelligence-Platform.git
```

Go to the project directory

```bash
cd Student-Success-Intelligence-Platform
```

Run the application

```bash
python main.py
```

---

# Current Progress

| Module | Status |
|---------|--------|
| Database Setup | Completed |
| Student Management | Completed |
| Attendance Management | Completed |
| Academic Management | Completed |
| Lifestyle Management | Completed |
| Analytics | In Progress |
| Dashboard | Planned |

---

# Future Scope

The following modules will be added in future versions of the project.

- Student Analytics
- Performance Dashboard
- Student Performance Prediction
- Data Visualization
- Export Reports
- User Authentication

---

# Version History

- Module 2 Completed
- Module 3 Completed
- Module 4 Completed
- Module 5 Completed

---

# Author

Mohit Sharma and Hemant Raj
