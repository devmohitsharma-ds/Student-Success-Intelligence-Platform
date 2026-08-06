from src.database import get_connection


# =====================================
# ADD LIFESTYLE RECORD
# =====================================

def add_lifestyle(

    student_id,
    sleep_hours,
    water_intake,
    exercise_minutes,
    screen_time

):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        INSERT INTO lifestyle(

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

    connection.close()

    print("\n Lifestyle Record Added Successfully!\n")


# =====================================
# VIEW ALL LIFESTYLE RECORDS
# =====================================

def view_lifestyle():

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("SELECT * FROM lifestyle")

    records = cursor.fetchall()

    if len(records) == 0:

        print("\nNo Lifestyle Records Found.\n")

    else:

        print("\n========== LIFESTYLE RECORDS ==========\n")

        for record in records:

            print(record)

    connection.close()

# =====================================
# SEARCH LIFESTYLE RECORD
# =====================================

def search_lifestyle(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        SELECT * FROM lifestyle

        WHERE student_id = ?

    """, (student_id,))

    record = cursor.fetchone()

    if record:

        print("\n========== LIFESTYLE RECORD FOUND ==========\n")

        print(f"Lifestyle ID      : {record[0]}")
        print(f"Student ID        : {record[1]}")
        print(f"Sleep Hours       : {record[2]}")
        print(f"Water Intake      : {record[3]} Liters")
        print(f"Exercise Minutes  : {record[4]}")
        print(f"Screen Time       : {record[5]} Hours")

    else:

        print("\n Lifestyle Record Not Found!\n")

    connection.close()

# =====================================
# UPDATE LIFESTYLE RECORD
# =====================================

def update_lifestyle(student_id, new_sleep_hours):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        UPDATE lifestyle

        SET sleep_hours = ?

        WHERE student_id = ?

    """, (

        new_sleep_hours,
        student_id

    ))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n Lifestyle Record Updated Successfully!\n")

    else:

        print("\n Lifestyle Record Not Found!\n")

    connection.close()

# =====================================
# DELETE LIFESTYLE RECORD
# =====================================

def delete_lifestyle(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        DELETE FROM lifestyle

        WHERE student_id = ?

    """, (student_id,))

    connection.commit()

    if cursor.rowcount > 0:

        print("\n Lifestyle Record Deleted Successfully!\n")

    else:

        print("\n Lifestyle Record Not Found!\n")

    connection.close()

# =====================================
# LIFESTYLE REPORT
# =====================================

def lifestyle_report():

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("SELECT COUNT(*) FROM lifestyle")

    total_records = cursor.fetchone()[0]

    cursor.execute("SELECT AVG(sleep_hours) FROM lifestyle")

    average_sleep = cursor.fetchone()[0]

    if average_sleep is None:

        average_sleep = 0

    print("\n====================================")
    print("       LIFESTYLE REPORT")
    print("====================================")
    print(f"Total Lifestyle Records : {total_records}")
    print(f"Average Sleep Hours     : {average_sleep:.2f}")
    print("====================================")

    connection.close()