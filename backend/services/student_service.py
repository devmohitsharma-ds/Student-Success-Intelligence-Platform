from src.database import get_connection
import sqlite3
import re


# =====================================
# VALIDATE STUDENT DATA
# =====================================

def validate_student_data(
    full_name,
    gender,
    department,
    semester,
    email,
    phone,
    admission_year
):

    if not isinstance(full_name, str) or not full_name.strip():
        raise ValueError("Full name is required")

    if not isinstance(gender, str) or not gender.strip():
        raise ValueError("Gender is required")

    if not isinstance(department, str) or not department.strip():
        raise ValueError("Department is required")

    if not isinstance(semester, int):
        raise ValueError("Semester must be an integer")

    if semester < 1 or semester > 12:
        raise ValueError("Semester must be between 1 and 12")

    if not isinstance(email, str) or not email.strip():
        raise ValueError("Email is required")

    email_pattern = r"^[^@\s]+@[^@\s]+\.[^@\s]+$"

    if not re.match(email_pattern, email):
        raise ValueError("Invalid email format")

    if not isinstance(phone, (str, int)):
        raise ValueError("Phone must be a number or text")

    phone = str(phone)

    if not phone.isdigit():
        raise ValueError("Phone must contain only digits")

    if len(phone) < 10 or len(phone) > 15:
        raise ValueError("Phone number must contain between 10 and 15 digits")

    if not isinstance(admission_year, int):
        raise ValueError("Admission year must be an integer")

    if admission_year < 2000 or admission_year > 2100:
        raise ValueError("Invalid admission year")


# =====================================
# GET ALL STUDENTS
# =====================================

def get_all_students():

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                student_id,
                full_name,
                gender,
                department,
                semester,
                email,
                phone,
                admission_year
            FROM students
            ORDER BY student_id
        """)

        records = cursor.fetchall()

        students = []

        for record in records:

            students.append({
                "student_id": record[0],
                "full_name": record[1],
                "gender": record[2],
                "department": record[3],
                "semester": record[4],
                "email": record[5],
                "phone": record[6],
                "admission_year": record[7]
            })

        return students

    finally:
        connection.close()


# =====================================
# GET STUDENT BY ID
# =====================================

def get_student_by_id(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                student_id,
                full_name,
                gender,
                department,
                semester,
                email,
                phone,
                admission_year
            FROM students
            WHERE student_id = ?
        """, (student_id,))

        record = cursor.fetchone()

        if record is None:
            return None

        return {
            "student_id": record[0],
            "full_name": record[1],
            "gender": record[2],
            "department": record[3],
            "semester": record[4],
            "email": record[5],
            "phone": record[6],
            "admission_year": record[7]
        }

    finally:
        connection.close()


# =====================================
# CREATE STUDENT
# =====================================

def create_student(
    full_name,
    gender,
    department,
    semester,
    email,
    phone,
    admission_year
):

    validate_student_data(
        full_name,
        gender,
        department,
        semester,
        email,
        phone,
        admission_year
    )

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            INSERT INTO students (
                full_name,
                gender,
                department,
                semester,
                email,
                phone,
                admission_year
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """, (
            full_name,
            gender,
            department,
            semester,
            email,
            phone,
            admission_year
        ))

        connection.commit()

        return cursor.lastrowid

    except sqlite3.IntegrityError:

        connection.rollback()

        raise ValueError(
            "Student record could not be created"
        )

    finally:
        connection.close()


# =====================================
# UPDATE STUDENT
# =====================================

def update_student(
    student_id,
    full_name,
    gender,
    department,
    semester,
    email,
    phone,
    admission_year
):

    validate_student_data(
        full_name,
        gender,
        department,
        semester,
        email,
        phone,
        admission_year
    )

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            UPDATE students
            SET
                full_name = ?,
                gender = ?,
                department = ?,
                semester = ?,
                email = ?,
                phone = ?,
                admission_year = ?
            WHERE student_id = ?
        """, (
            full_name,
            gender,
            department,
            semester,
            email,
            phone,
            admission_year,
            student_id
        ))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()


# =====================================
# DELETE STUDENT
# =====================================

def delete_student(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            DELETE FROM students
            WHERE student_id = ?
        """, (student_id,))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()
