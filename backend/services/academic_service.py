from src.database import get_connection
import sqlite3


# =====================================
# VALIDATE ACADEMIC DATA
# =====================================

def validate_academic_data(cgpa, backlogs, quiz_average):

    if not isinstance(cgpa, (int, float)):
        raise ValueError("CGPA must be a number")

    if cgpa < 0 or cgpa > 10:
        raise ValueError("CGPA must be between 0 and 10")

    if not isinstance(backlogs, int):
        raise ValueError("Backlogs must be an integer")

    if backlogs < 0:
        raise ValueError("Backlogs cannot be negative")

    if not isinstance(quiz_average, (int, float)):
        raise ValueError("Quiz average must be a number")

    if quiz_average < 0 or quiz_average > 100:
        raise ValueError("Quiz average must be between 0 and 100")


# =====================================
# GET ALL ACADEMIC RECORDS
# =====================================

def get_all_academics():

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                academic_id,
                student_id,
                cgpa,
                backlogs,
                quiz_average
            FROM academics
            ORDER BY academic_id
        """)

        records = cursor.fetchall()

        academics = []

        for record in records:

            academics.append({
                "academic_id": record[0],
                "student_id": record[1],
                "cgpa": record[2],
                "backlogs": record[3],
                "quiz_average": record[4]
            })

        return academics

    finally:
        connection.close()


# =====================================
# GET ACADEMIC RECORD BY STUDENT ID
# =====================================

def get_academic_by_student_id(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                academic_id,
                student_id,
                cgpa,
                backlogs,
                quiz_average
            FROM academics
            WHERE student_id = ?
        """, (student_id,))

        record = cursor.fetchone()

        if record is None:
            return None

        return {
            "academic_id": record[0],
            "student_id": record[1],
            "cgpa": record[2],
            "backlogs": record[3],
            "quiz_average": record[4]
        }

    finally:
        connection.close()


# =====================================
# CREATE ACADEMIC RECORD
# =====================================

def create_academic(
    student_id,
    cgpa,
    backlogs,
    quiz_average
):

    validate_academic_data(
        cgpa,
        backlogs,
        quiz_average
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

        # Check duplicate academic record
        cursor.execute("""
            SELECT 1
            FROM academics
            WHERE student_id = ?
        """, (student_id,))

        if cursor.fetchone() is not None:
            raise ValueError(
                "Academic record already exists for this student"
            )

        # Create academic record
        cursor.execute("""
            INSERT INTO academics (
                student_id,
                cgpa,
                backlogs,
                quiz_average
            )
            VALUES (?, ?, ?, ?)
        """, (
            student_id,
            cgpa,
            backlogs,
            quiz_average
        ))

        connection.commit()

        return cursor.lastrowid

    except ValueError:
        connection.rollback()
        raise

    except sqlite3.IntegrityError:
        connection.rollback()

        raise ValueError(
            "Academic record could not be created"
        )

    finally:
        connection.close()


# =====================================
# UPDATE ACADEMIC RECORD
# =====================================

def update_academic(
    student_id,
    cgpa,
    backlogs,
    quiz_average
):

    validate_academic_data(
        cgpa,
        backlogs,
        quiz_average
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

        # Update academic record
        cursor.execute("""
            UPDATE academics
            SET
                cgpa = ?,
                backlogs = ?,
                quiz_average = ?
            WHERE student_id = ?
        """, (
            cgpa,
            backlogs,
            quiz_average,
            student_id
        ))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()


# =====================================
# DELETE ACADEMIC RECORD
# =====================================

def delete_academic(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            DELETE FROM academics
            WHERE student_id = ?
        """, (student_id,))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()
