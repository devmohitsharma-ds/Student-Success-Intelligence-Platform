from src.database import get_connection
python backend/app.py


# =====================================
# ADD ACADEMIC RECORD
# =====================================

def add_academic(student_id, cgpa, backlogs, quiz_average):

    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("""

        INSERT INTO academics(

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
    connection.close()

    print("\n Academic Record Added Successfully!\n")


# =====================================
# VIEW ALL ACADEMIC RECORDS
# =====================================

def view_academics():

    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("SELECT * FROM academics")

    records = cursor.fetchall()

    if len(records) == 0:

        print("\nNo Academic Records Found.\n")

    else:

        print("\n========== ACADEMIC RECORDS ==========\n")

        for record in records:

            print(record)

    connection.close()


# =====================================
# SEARCH ACADEMIC RECORD
# =====================================

def search_academic(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("""

        SELECT * FROM academics

        WHERE student_id = ?

    """, (student_id,))

    record = cursor.fetchone()

    if record:

        print("\n========== ACADEMIC RECORD FOUND ==========\n")

        print(f"Academic ID : {record[0]}")
        print(f"Student ID  : {record[1]}")
        print(f"CGPA        : {record[2]}")
        print(f"Backlogs    : {record[3]}")
        print(f"Quiz Average: {record[4]}")

    else:

        print("\n Academic Record Not Found!\n")

    connection.close()


# =====================================
# UPDATE ACADEMIC RECORD
# =====================================

def update_academic(student_id, new_cgpa):

    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("""

        UPDATE academics

        SET cgpa = ?

        WHERE student_id = ?

    """, (

        new_cgpa,
        student_id

    ))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n Academic Record Updated Successfully!\n")

    else:

        print("\n Academic Record Not Found!\n")

    connection.close()


# =====================================
# DELETE ACADEMIC RECORD
# =====================================

def delete_academic(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("""

        DELETE FROM academics

        WHERE student_id = ?

    """, (student_id,))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n Academic Record Deleted Successfully!\n")

    else:

        print("\n Academic Record Not Found!\n")

    connection.close()


# =====================================
# ACADEMIC REPORT
# =====================================

def academic_report():

    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute("SELECT COUNT(*) FROM academics")
    total_records = cursor.fetchone()[0]

    cursor.execute("SELECT AVG(cgpa) FROM academics")
    average_cgpa = cursor.fetchone()[0]

    if average_cgpa is None:
        average_cgpa = 0

    print("\n====================================")
    print("       ACADEMIC REPORT")
    print("====================================")
    print(f"Total Academic Records : {total_records}")
    print(f"Average CGPA           : {average_cgpa:.2f}")
    print("====================================")

    connection.close()