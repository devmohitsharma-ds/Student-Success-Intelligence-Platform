# Student Success Intelligence Platform (SSIP)

## 📌 About the Project

The **Student Success Intelligence Platform (SSIP)** is a student-focused application designed to bring academic performance, attendance, lifestyle habits, analytics, and personalized recommendations into one place.

The main idea behind SSIP is simple: instead of looking at marks, attendance, and daily habits separately, the platform combines these factors to help identify students who may need attention and provides useful recommendations based on their current situation.

The project is being developed as a practical **B.Tech CSE (Data Science)** project with a focus on real data, backend APIs, database operations, and a functional desktop interface.

---

## 🎯 Project Objectives

SSIP aims to:

- Maintain student profiles and records.
- Track academic performance.
- Monitor attendance.
- Record important lifestyle habits.
- Analyse academic, attendance, and lifestyle data together.
- Calculate an overall student risk level.
- Provide personalized recommendations.
- Present important information through a simple dashboard.
- Allow administrators or authorized users to add, update, search, and delete student records.

---

## ✨ Main Features

### 👨‍🎓 Student Management

The Student Management module allows student records to be managed from the application.

Features include:

- Add student
- Update student
- Delete student
- Search student
- Refresh student records
- View student information in a table

Student information currently includes:

- Student ID
- Full Name
- Gender
- Department
- Semester
- Email
- Phone
- Admission Year

---

### 📚 Academic Performance

The Academic module manages important academic indicators.

Currently supported:

- CGPA
- Backlogs
- Quiz Average
- Academic Status
- Academic record update
- Academic record refresh

The module validates values before sending them to the backend.

For example:

- CGPA: 0–10
- Quiz Average: 0–100
- Backlogs: 0 or greater

---

### 📅 Attendance Management

The Attendance module allows the student's attendance percentage to be viewed and updated.

Features include:

- Current attendance percentage
- Attendance status
- Update attendance
- Refresh attendance
- Attendance guidelines

The current status categories are based on attendance percentage.

---

### 🌱 Lifestyle Tracking

The Lifestyle module tracks four important daily habits:

- Sleep hours
- Water intake
- Exercise duration
- Screen time

The module also provides a simple lifestyle status based on the recorded values.

Users can update their lifestyle information and refresh the latest values from the backend.

---

### 📊 Analytics

Analytics is one of the main components of SSIP.

It combines information from:

- Academic performance
- Attendance
- Lifestyle

The analytics page currently displays:

- CGPA
- Attendance
- Sleep
- Water intake
- Exercise
- Screen time
- Risk level
- Risk score
- Performance summary

The purpose of this module is to turn individual student records into a more useful overall picture of student performance.

---

### 🧠 Risk Analysis

The backend analyses student information and produces an overall risk assessment.

The result includes:

- Risk score
- Overall risk level

The risk information is also used by other parts of the application such as the dashboard and recommendations.

---

### 💡 Recommendations

The recommendation module is intended to convert analytics into practical actions.

Examples of recommendation areas include:

- Academic improvement
- Attendance improvement
- Lifestyle improvement
- High-risk student attention

The goal is not only to identify a problem but also to suggest what the student can do next.

---

### 📊 Dashboard

The dashboard provides a quick overview of the student's current situation.

It displays information such as:

- Student information
- CGPA
- Attendance
- Overall risk
- Backlogs
- Quiz average
- Lifestyle indicators
- Lifestyle warnings
- Recommendations

The dashboard receives its information from the backend rather than relying only on hard-coded values.

---

### ⚙️ Settings

The Settings module provides application preference controls such as:

- Notification preference
- Automatic dashboard refresh
- Refresh interval
- Account information

---

## 🏗️ System Architecture

The project follows a simple layered structure:

```text
                    SSIP
                     │
             ┌───────┴───────┐
             │               │
        Java Frontend     Flask Backend
             │               │
             │          REST API Routes
             │               │
             │          Service Layer
             │               │
             │           Database
             │
          Swing UI
