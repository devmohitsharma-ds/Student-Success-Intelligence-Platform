# Student Success Intelligence Platform (SSIP)

## Overview

Student Success Intelligence Platform (SSIP) is a console-based application developed using Python and SQLite to manage and analyze student-related information. The project follows a modular architecture, where each module focuses on a specific area such as student management, attendance, academics, lifestyle, and analytics.

The application provides CRUD operations, report generation, and basic analytical features to help organize and evaluate student data efficiently.

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
│   ├── lifestyle.py
│   └── analytics.py
│
├── main.py
├── README.md
├── requirements.txt
└── .gitignore
```

---

# Features

## Module 1 – Database Setup

- SQLite database connection
- Database initialization
- Automatic table creation
- Centralized database configuration

---

## Module 2 – Student Management

- Add Student
- View Students
- Search Student
- Update Student
- Delete Student

### Student Details

- Full Name
- Gender
- Department
- Semester
- Email
- Phone Number
- Admission Year

---

## Module 3 – Attendance Management

- Add Attendance Record
- View Attendance Records
- Search Attendance Record
- Update Attendance Record
- Delete Attendance Record
- Attendance Report

### Attendance Details

- Student ID
- Attendance Percentage

---

## Module 4 – Academic Management

- Add Academic Record
- View Academic Records
- Search Academic Record
- Update Academic Record
- Delete Academic Record
- Academic Report

### Academic Details

- Student ID
- CGPA
- Backlogs
- Quiz Average

---

## Module 5 – Lifestyle Management

- Add Lifestyle Record
- View Lifestyle Records
- Search Lifestyle Record
- Update Lifestyle Record
- Delete Lifestyle Record
- Lifestyle Report

### Lifestyle Details

- Student ID
- Sleep Hours
- Water Intake
- Exercise Minutes
- Screen Time

---

## Module 6 – Analytics

- Student Summary
- Attendance Analysis
- Overall Student Report
- Student Risk Analysis

### Analytics Includes

- Student Profile Summary
- Attendance Status
- Academic Performance Summary
- Lifestyle Summary
- Overall Student Report
- Risk Classification

---

# Database Tables

The project currently uses the following tables:

- students
- attendance
- academics
- lifestyle

---

# Project Workflow

```
Student Information
        │
        ▼
Attendance Records
        │
        ▼
Academic Records
        │
        ▼
Lifestyle Records
        │
        ▼
Analytics & Reports
```

---

# How to Run

Clone the repository

```bash
git clone https://github.com/Mohitjeet007/Student-Success-Intelligence-Platform.git
```

Move into the project directory

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
| Database Setup | ✅ Completed |
| Student Management | ✅ Completed |
| Attendance Management | ✅ Completed |
| Academic Management | ✅ Completed |
| Lifestyle Management | ✅ Completed |
| Analytics | ✅ Completed |
| Dashboard | 🔄 In Progress |

---

# Future Enhancements

The following features are planned for future development:

- Dashboard Interface
- Data Visualization
- Student Performance Prediction
- Export Reports
- Authentication System
- Faculty Management
- Parent Portal
- Notifications

---

# Version History

- Module 1 – Database Setup
- Module 2 – Student Management
- Module 3 – Attendance Management
- Module 4 – Academic Management
- Module 5 – Lifestyle Management
- Module 6 – Analytics

---

# Author

**Mohit Sharma and Hemant Raj**

B.Tech CSE (Data Science)

Chandigarh University
