from src.database import get_connection
import sqlite3


def add_student(full_name,
                gender,
                department,
                semester,
                email,
                phone,
                admission_year):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""

            INSERT INTO students(

                full_name,
                gender,
                department,
                semester,
                email,
                phone,
                admission_year

            )

            VALUES(?,?,?,?,?,?,?)

        """,

        (

            full_name,
            gender,
            department,
            semester,
            email,
            phone,
            admission_year

        ))

        connection.commit()

        print("\n Student Added Successfully!\n")

    except sqlite3.IntegrityError:

        print("\n Email already exists!\n")

    finally:

        connection.close()

def view_students():

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("SELECT * FROM students")

    students = cursor.fetchall()

    print("\n==============================================")
    print("              STUDENT LIST")
    print("==============================================")

    if len(students) == 0:

        print("No Student Found")

    else:

        for student in students:

            print(f"""
Student ID      : {student[0]}
Name            : {student[1]}
Gender          : {student[2]}
Department      : {student[3]}
Semester        : {student[4]}
Email           : {student[5]}
Phone           : {student[6]}
Admission Year  : {student[7]}
----------------------------------------------
""")

    connection.close()

    input("Press Enter to return to the menu...")

def search_student(email):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        SELECT * FROM students

        WHERE email = ?

    """, (email,))

    student = cursor.fetchone()

    if student:

        print("\n========== STUDENT FOUND ==========\n")

        print(f"Student ID      : {student[0]}")
        print(f"Name            : {student[1]}")
        print(f"Gender          : {student[2]}")
        print(f"Department      : {student[3]}")
        print(f"Semester        : {student[4]}")
        print(f"Email           : {student[5]}")
        print(f"Phone           : {student[6]}")
        print(f"Admission Year  : {student[7]}")

    else:

        print("\n❌ Student Not Found\n")

    connection.close()

def update_student(email, new_phone):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        UPDATE students

        SET phone = ?

        WHERE email = ?

    """, (

        new_phone,
        email

    ))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n✅ Student Updated Successfully!\n")

    else:

        print("\n❌ Student Not Found!\n")

    connection.close()

def delete_student(email):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        DELETE FROM students

        WHERE email = ?

    """, (email,))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n✅ Student Deleted Successfully!\n")

    else:

        print("\n❌ Student Not Found!\n")

    connection.close()