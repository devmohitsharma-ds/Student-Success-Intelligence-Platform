from src.database import get_connection


# =====================================
# STUDENT SUMMARY
# =====================================

def student_summary(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        SELECT

            full_name,
            department,
            semester

        FROM students

        WHERE student_id = ?

    """, (student_id,))

    student = cursor.fetchone()

    if student:

        print("\n====================================")
        print("        STUDENT SUMMARY")
        print("====================================")

        print(f"Name       : {student[0]}")
        print(f"Department : {student[1]}")
        print(f"Semester   : {student[2]}")

        print("====================================")

    else:

        print("\n Student Not Found!\n")

    connection.close()

# =====================================
# ATTENDANCE ANALYSIS
# =====================================

def attendance_analysis(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        SELECT attendance_percentage

        FROM attendance

        WHERE student_id = ?

    """, (student_id,))

    record = cursor.fetchone()

    if record:

        attendance = record[0]

        if attendance >= 90:
            status = "Excellent"

        elif attendance >= 75:
            status = "Good"

        elif attendance >= 60:
            status = "Average"

        else:
            status = "Poor"

        print("\n====================================")
        print("     ATTENDANCE ANALYSIS")
        print("====================================")
        print(f"Attendance : {attendance}%")
        print(f"Status     : {status}")
        print("====================================")

    else:

        print("\n Attendance Record Not Found!\n")

    connection.close()

# =====================================
# OVERALL STUDENT REPORT
# =====================================

def overall_student_report(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        SELECT

            s.full_name,
            s.department,
            s.semester,

            a.attendance_percentage,

            ac.cgpa,
            ac.backlogs,
            ac.quiz_average,

            l.sleep_hours,
            l.water_intake,
            l.exercise_minutes,
            l.screen_time

        FROM students s

        LEFT JOIN attendance a
            ON s.student_id = a.student_id

        LEFT JOIN academics ac
            ON s.student_id = ac.student_id

        LEFT JOIN lifestyle l
            ON s.student_id = l.student_id

        WHERE s.student_id = ?

    """, (student_id,))

    record = cursor.fetchone()

    if record:

        print("\n==========================================")
        print("         OVERALL STUDENT REPORT")
        print("==========================================")

        print(f"Name              : {record[0]}")
        print(f"Department        : {record[1]}")
        print(f"Semester          : {record[2]}")

        print("------------------------------------------")

        print(f"Attendance        : {record[3]} %")

        print("------------------------------------------")

        print(f"CGPA              : {record[4]}")
        print(f"Backlogs          : {record[5]}")
        print(f"Quiz Average      : {record[6]}")

        print("------------------------------------------")

        print(f"Sleep Hours       : {record[7]}")
        print(f"Water Intake      : {record[8]} L")
        print(f"Exercise Minutes  : {record[9]}")
        print(f"Screen Time       : {record[10]} Hours")

        print("==========================================")

    else:

        print("\n Student Not Found!\n")

    connection.close()

# =====================================
# RISK ANALYSIS
# =====================================

def risk_analysis(student_id):

    connection = get_connection()

    cursor = connection.cursor()

    cursor.execute("""

        SELECT

            a.attendance_percentage,

            ac.cgpa,

            l.sleep_hours,
            l.water_intake,
            l.exercise_minutes,
            l.screen_time

        FROM students s

        LEFT JOIN attendance a
            ON s.student_id = a.student_id

        LEFT JOIN academics ac
            ON s.student_id = ac.student_id

        LEFT JOIN lifestyle l
            ON s.student_id = l.student_id

        WHERE s.student_id = ?

    """, (student_id,))

    record = cursor.fetchone()

    if not record:

        print("\n Student Not Found!\n")
        connection.close()
        return

    attendance = record[0]
    cgpa = record[1]
    sleep = record[2]
    water = record[3]
    exercise = record[4]
    screen = record[5]

    print("\n====================================")
    print("       STUDENT RISK ANALYSIS")
    print("====================================")

    risk_score = 0

    # Attendance
    if attendance is not None:
        if attendance >= 75:
            print("Attendance  : Good ")
        else:
            print("Attendance  : At Risk ")
            risk_score += 1

    # Academics
    if cgpa is not None:
        if cgpa >= 6.0:
            print("Academics   : Good ")
        else:
            print("Academics   : At Risk ")
            risk_score += 1

    # Lifestyle
    lifestyle_warning = False

    if sleep is not None and sleep < 6:
        lifestyle_warning = True

    if water is not None and water < 2:
        lifestyle_warning = True

    if exercise is not None and exercise < 20:
        lifestyle_warning = True

    if screen is not None and screen > 8:
        lifestyle_warning = True

    if lifestyle_warning:
        print("Lifestyle   : Needs Improvement ")
        risk_score += 1
    else:
        print("Lifestyle   : Good ")

    print("------------------------------------")

    if risk_score == 0:
        overall = "LOW"
    elif risk_score == 1:
        overall = "MODERATE"
    else:
        overall = "HIGH"

    print(f"Overall Risk : {overall}")

    print("====================================")

    connection.close()