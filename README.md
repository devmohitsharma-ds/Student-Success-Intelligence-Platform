# Student Success Intelligence Platform (SSIP)

A Python-based Student Management System developed to manage and analyze essential student information in a structured and efficient manner.

The project is built using Python and SQLite and follows a modular architecture where each feature is developed independently and integrated into a single application through a menu-driven interface.

---

## Project Overview

Student Success Intelligence Platform (SSIP) is designed to maintain student records and provide a centralized system for managing academic-related information.

The application currently supports:

- Student Management
- Attendance Management
- Academic Management

Each module provides complete CRUD (Create, Read, Update and Delete) functionality and stores data using SQLite.

---

## Technologies Used

- Python 3
- SQLite3
- Git
- GitHub
- VS Code

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
│   ├── student.py
│   ├── attendance.py
│   ├── academic.py
│   ├── menu.py
│   └── config.py
│
├── main.py
├── README.md
├── requirements.txt
└── .gitignore
```

---

## Implemented Modules

### Module 1 - Database Setup

- SQLite Database
- Table Creation
- Database Connection
- Project Structure

---

### Module 2 - Student Management

Features:

- Add Student
- View Students
- Search Student
- Update Student
- Delete Student

Student information includes:

- Name
- Gender
- Department
- Semester
- Email
- Phone Number
- Admission Year

---

### Module 3 - Attendance Management

Features:

- Add Attendance
- View Attendance
- Search Attendance
- Update Attendance
- Delete Attendance
- Attendance Report

Attendance information includes:

- Student ID
- Attendance Percentage

---

### Module 4 - Academic Management

Features:

- Add Academic Record
- View Academic Records
- Search Academic Record
- Update Academic Record
- Delete Academic Record
- Academic Report

Academic information includes:

- Student ID
- CGPA
- Backlogs
- Quiz Average

---

## Database Tables

The project currently contains the following tables:

- students
- attendance
- academics
- lifestyle *(under development)*

---

## Current Progress

| Module | Status |
|---------|--------|
| Database Setup | Completed |
| Student Management | Completed |
| Attendance Management | Completed |
| Academic Management | Completed |
| Lifestyle Management | In Progress |
| Analytics | Planned |
| Dashboard | Planned |

---

## How to Run

Clone the repository:

```bash
git clone https://github.com/Mohitjeet007/Student-Success-Intelligence-Platform.git
```

Move into the project directory:

```bash
cd Student-Success-Intelligence-Platform
```

Run the application:

```bash
python main.py
```

---

## Future Enhancements

The following features are planned for future development:

- Lifestyle Management
- Student Performance Analytics
- Risk Prediction
- Dashboard
- Reports
- Data Visualization
- Export Reports
- User Authentication

---

## Version History

- Module 2 Completed
- Module 3 Completed
- Module 4 Completed

Git tags are maintained for completed project milestones.

---

## Author

Mohit Sharma
Hemant Raj
