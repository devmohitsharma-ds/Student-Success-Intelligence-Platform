from src.database import get_connection
import sqlite3


# =====================================
# VALIDATE ATTENDANCE DATA
# =====================================

def validate_attendance_data(attendance_percentage):

    if not isinstance(attendance_percentage, (int, float)):
        raise ValueError(
            "Attendance percentage must be a number"
        )

    if attendance_percentage < 0 or attendance_percentage > 100:
        raise ValueError(
            "Attendance percentage must be between 0 and 100"
        )


# =====================================
# GET ALL ATTENDANCE
# =====================================

def get_all_attendance():

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                attendance_id,
                student_id,
                attendance_percentage
            FROM attendance
            ORDER BY attendance_id
        """)

        records = cursor.fetchall()

        attendance_records = []

        for record in records:

            attendance_records.append({
                "attendance_id": record[0],
                "student_id": record[1],
                "attendance_percentage": record[2]
            })

        return attendance_records

    finally:
        connection.close()


# =====================================
# GET ATTENDANCE BY STUDENT ID
# =====================================

def get_attendance_by_student_id(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                attendance_id,
                student_id,
                attendance_percentage
            FROM attendance
            WHERE student_id = ?
        """, (student_id,))

        record = cursor.fetchone()

        if record is None:
            return None

        return {
            "attendance_id": record[0],
            "student_id": record[1],
            "attendance_percentage": record[2]
        }

    finally:
        connection.close()


# =====================================
# CREATE ATTENDANCE
# =====================================

def create_attendance(
    student_id,
    attendance_percentage
):

    validate_attendance_data(
        attendance_percentage
    )

    connection = get_connection()
    cursor = connection.cursor()

    try:

        # Check student exists
        cursor.execute("""
            SELECT 1
            FROM students
            WHERE student_id = ?
        """, (student_id,))

        if cursor.fetchone() is None:
            raise ValueError("Student does not exist")

        # Check duplicate attendance record
        cursor.execute("""
            SELECT 1
            FROM attendance
            WHERE student_id = ?
        """, (student_id,))

        if cursor.fetchone() is not None:
            raise ValueError(
                "Attendance record already exists for this student"
            )

        # Create attendance record
        cursor.execute("""
            INSERT INTO attendance (
                student_id,
                attendance_percentage
            )
            VALUES (?, ?)
        """, (
            student_id,
            attendance_percentage
        ))

        connection.commit()

        return cursor.lastrowid

    except ValueError:
        connection.rollback()
        raise

    except sqlite3.IntegrityError:
        connection.rollback()

        raise ValueError(
            "Attendance record could not be created"
        )

    finally:
        connection.close()


# =====================================
# UPDATE ATTENDANCE
# =====================================

def update_attendance(
    student_id,
    attendance_percentage
):

    validate_attendance_data(
        attendance_percentage
    )

    connection = get_connection()
    cursor = connection.cursor()

    try:

        # Check student exists
        cursor.execute("""
            SELECT 1
            FROM students
            WHERE student_id = ?
        """, (student_id,))

        if cursor.fetchone() is None:
            raise ValueError("Student does not exist")

        # Update attendance record
        cursor.execute("""
            UPDATE attendance
            SET attendance_percentage = ?
            WHERE student_id = ?
        """, (
            attendance_percentage,
            student_id
        ))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()


# =====================================
# DELETE ATTENDANCE
# =====================================

def delete_attendance(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            DELETE FROM attendance
            WHERE student_id = ?
        """, (student_id,))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()
