from src.database import get_connection


# ==============================
# ADD ATTENDANCE
# ==============================

def add_attendance(student_id, attendance_percentage):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        INSERT INTO attendance
        (
            student_id,
            attendance_percentage
        )

        VALUES (?, ?)

    """, (

        student_id,
        attendance_percentage

    ))

    connection.commit()

    connection.close()

    print("\n✅ Attendance Added Successfully!\n")


# ==============================
# VIEW ATTENDANCE
# ==============================

def view_attendance():

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("SELECT * FROM attendance")

    records = cursor.fetchall()

    print("\n========== ATTENDANCE RECORDS ==========\n")

    if len(records) == 0:

        print("No Attendance Records Found")

    else:

        for record in records:

            print(f"""
Attendance ID          : {record[0]}
Student ID             : {record[1]}
Attendance Percentage  : {record[2]}
------------------------------------------
""")

    connection.close()

    input("Press Enter to continue...")


# ==============================
# SEARCH ATTENDANCE
# ==============================

def search_attendance(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        SELECT * FROM attendance

        WHERE student_id = ?

    """, (student_id,))

    record = cursor.fetchone()

    if record:

        print("\n========== ATTENDANCE FOUND ==========\n")

        print(f"""
Attendance ID          : {record[0]}
Student ID             : {record[1]}
Attendance Percentage  : {record[2]}
""")

    else:

        print("\n❌ Attendance Record Not Found\n")

    connection.close()

    input("Press Enter to continue...")


# ==============================
# UPDATE ATTENDANCE
# ==============================

def update_attendance(student_id, new_percentage):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        UPDATE attendance

        SET attendance_percentage = ?

        WHERE student_id = ?

    """, (

        new_percentage,
        student_id

    ))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n✅ Attendance Updated Successfully!\n")

    else:

        print("\n❌ Student Attendance Not Found!\n")

    connection.close()

# ==============================
# DELETE ATTENDANCE
# ==============================

def delete_attendance(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        DELETE FROM attendance

        WHERE student_id = ?

    """, (student_id,))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n✅ Attendance Deleted Successfully!\n")

    else:

        print("\n❌ Attendance Record Not Found!\n")

    connection.close()

# ==============================
# ATTENDANCE REPORT
# ==============================

def attendance_report():

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("SELECT COUNT(*) FROM attendance")

    total_records = cursor.fetchone()[0]

    cursor.execute("SELECT AVG(attendance_percentage) FROM attendance")

    average = cursor.fetchone()[0]

    print("\n====================================")
    print("       ATTENDANCE REPORT")
    print("====================================")

    print(f"Total Attendance Records : {total_records}")

    if average is None:
        average = 0

    print(f"Average Attendance       : {average:.2f}%")

    print("====================================")

    connection.close()