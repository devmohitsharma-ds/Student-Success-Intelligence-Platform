from src.database import get_connection
import sqlite3


# =====================================
# VALIDATE LIFESTYLE DATA
# =====================================

def validate_lifestyle_data(
    sleep_hours,
    water_intake,
    exercise_minutes,
    screen_time
):

    if not isinstance(sleep_hours, (int, float)):
        raise ValueError(
            "Sleep hours must be a number"
        )

    if sleep_hours < 0:
        raise ValueError(
            "Sleep hours cannot be negative"
        )

    if not isinstance(water_intake, (int, float)):
        raise ValueError(
            "Water intake must be a number"
        )

    if water_intake < 0:
        raise ValueError(
            "Water intake cannot be negative"
        )

    if not isinstance(exercise_minutes, (int, float)):
        raise ValueError(
            "Exercise minutes must be a number"
        )

    if exercise_minutes < 0:
        raise ValueError(
            "Exercise minutes cannot be negative"
        )

    if not isinstance(screen_time, (int, float)):
        raise ValueError(
            "Screen time must be a number"
        )

    if screen_time < 0:
        raise ValueError(
            "Screen time cannot be negative"
        )


# =====================================
# GET ALL LIFESTYLE RECORDS
# =====================================

def get_all_lifestyle():

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                lifestyle_id,
                student_id,
                sleep_hours,
                water_intake,
                exercise_minutes,
                screen_time
            FROM lifestyle
            ORDER BY lifestyle_id
        """)

        records = cursor.fetchall()

        lifestyle_records = []

        for record in records:

            lifestyle_records.append({
                "lifestyle_id": record[0],
                "student_id": record[1],
                "sleep_hours": record[2],
                "water_intake": record[3],
                "exercise_minutes": record[4],
                "screen_time": record[5]
            })

        return lifestyle_records

    finally:
        connection.close()


# =====================================
# GET LIFESTYLE BY STUDENT ID
# =====================================

def get_lifestyle_by_student_id(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                lifestyle_id,
                student_id,
                sleep_hours,
                water_intake,
                exercise_minutes,
                screen_time
            FROM lifestyle
            WHERE student_id = ?
        """, (student_id,))

        record = cursor.fetchone()

        if record is None:
            return None

        return {
            "lifestyle_id": record[0],
            "student_id": record[1],
            "sleep_hours": record[2],
            "water_intake": record[3],
            "exercise_minutes": record[4],
            "screen_time": record[5]
        }

    finally:
        connection.close()


# =====================================
# CREATE LIFESTYLE RECORD
# =====================================

def create_lifestyle(
    student_id,
    sleep_hours,
    water_intake,
    exercise_minutes,
    screen_time
):

    validate_lifestyle_data(
        sleep_hours,
        water_intake,
        exercise_minutes,
        screen_time
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

        # Check duplicate lifestyle record
        cursor.execute("""
            SELECT 1
            FROM lifestyle
            WHERE student_id = ?
        """, (student_id,))

        if cursor.fetchone() is not None:
            raise ValueError(
                "Lifestyle record already exists for this student"
            )

        # Create lifestyle record
        cursor.execute("""
            INSERT INTO lifestyle (
                student_id,
                sleep_hours,
                water_intake,
                exercise_minutes,
                screen_time
            )
            VALUES (?, ?, ?, ?, ?)
        """, (
            student_id,
            sleep_hours,
            water_intake,
            exercise_minutes,
            screen_time
        ))

        connection.commit()

        return cursor.lastrowid

    except ValueError:
        connection.rollback()
        raise

    except sqlite3.IntegrityError:
        connection.rollback()

        raise ValueError(
            "Lifestyle record could not be created"
        )

    finally:
        connection.close()


# =====================================
# UPDATE LIFESTYLE RECORD
# =====================================

def update_lifestyle(
    student_id,
    sleep_hours,
    water_intake,
    exercise_minutes,
    screen_time
):

    validate_lifestyle_data(
        sleep_hours,
        water_intake,
        exercise_minutes,
        screen_time
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

        # Update lifestyle record
        cursor.execute("""
            UPDATE lifestyle
            SET
                sleep_hours = ?,
                water_intake = ?,
                exercise_minutes = ?,
                screen_time = ?
            WHERE student_id = ?
        """, (
            sleep_hours,
            water_intake,
            exercise_minutes,
            screen_time,
            student_id
        ))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()


# =====================================
# DELETE LIFESTYLE
# =====================================

def delete_lifestyle(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            DELETE FROM lifestyle
            WHERE student_id = ?
        """, (student_id,))

        connection.commit()

        return cursor.rowcount

    finally:
        connection.close()
