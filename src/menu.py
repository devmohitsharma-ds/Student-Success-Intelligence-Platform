from src.student import (
    add_student,
    view_students,
    search_student,
    update_student,
    delete_student
)

from src.attendance import (
    add_attendance,
    view_attendance,
    search_attendance,
    update_attendance,
    delete_attendance
)

from src.academic import (
    add_academic,
    view_academics,
    search_academic,
    update_academic,
    delete_academic,
    academic_report
)

from src.lifestyle import (
    add_lifestyle,
    view_lifestyle,
    search_lifestyle,
    update_lifestyle,
    delete_lifestyle,
    lifestyle_report
)

from src.analytics import (
    student_summary,
    attendance_analysis,
    overall_student_report,
    risk_analysis
)


# =====================================
# MAIN MENU
# =====================================

def start_menu():

    while True:

        print("\n========================================")
        print(" Student Success Intelligence Platform")
        print("========================================")

        print("1. Student Management")
        print("2. Attendance Management")
        print("3. Academic Management")
        print("4. Lifestyle Management")
        print("5. Analytics & Reports")
        print("6. Exit")

        choice = input("\nEnter Choice : ")

        if choice == "1":

            student_menu()

        elif choice == "2":

            attendance_menu()

        elif choice == "3":

            academic_menu()

        elif choice == "4":

            lifestyle_menu()

        elif choice == "5":

            analytics_menu()

        elif choice == "6":

            print("\nGoodbye!! Thank you for using the Student Success Intelligence Platform.\n")
            print("Have a Nice Day!!")

            break

        else:

            print("\n Invalid Choice!\n")


# =====================================
# STUDENT MENU
# =====================================

def student_menu():

    while True:

        print("\n========== STUDENT MENU ==========")

        print("1. Add Student")
        print("2. View Students")
        print("3. Search Student")
        print("4. Update Student")
        print("5. Delete Student")
        print("6. Back")

        choice = input("\nEnter Choice : ")

        if choice == "1":

            full_name = input("Name : ")
            gender = input("Gender : ")
            department = input("Department : ")
            semester = int(input("Semester : "))
            email = input("Email : ")
            phone = input("Phone : ")
            admission_year = int(input("Admission Year : "))

            add_student(
                full_name,
                gender,
                department,
                semester,
                email,
                phone,
                admission_year
            )

        elif choice == "2":

            view_students()

        elif choice == "3":

            email = input("Email : ")

            search_student(email)

        elif choice == "4":

            email = input("Email : ")
            phone = input("New Phone : ")

            update_student(email, phone)

        elif choice == "5":

            email = input("Email : ")

            delete_student(email)

        elif choice == "6":

            break

        else:

            print("\n Invalid Choice!\n")


# =====================================
# ATTENDANCE MENU
# =====================================

def attendance_menu():

    while True:

        print("\n======= ATTENDANCE MENU =======")

        print("1. Add Attendance")
        print("2. View Attendance")
        print("3. Search Attendance")
        print("4. Update Attendance")
        print("5. Delete Attendance")
        print("6. Back")

        choice = input("\nEnter Choice : ")

        if choice == "1":

            student_id = int(input("Student ID : "))
            percentage = float(input("Attendance % : "))

            add_attendance(student_id, percentage)

        elif choice == "2":

            view_attendance()

        elif choice == "3":

            student_id = int(input("Student ID : "))

            search_attendance(student_id)

        elif choice == "4":

            student_id = int(input("Student ID : "))
            percentage = float(input("New Attendance % : "))

            update_attendance(student_id, percentage)

        elif choice == "5":

            student_id = int(input("Student ID : "))

            delete_attendance(student_id)

        elif choice == "6":

            break

        else:

            print("\n Invalid Choice!\n")


# =====================================
# ACADEMIC MENU
# =====================================

def academic_menu():

    while True:

        print("\n======= ACADEMIC MENU =======")

        print("1. Add Academic Record")
        print("2. View Academic Records")
        print("3. Search Academic Record")
        print("4. Update Academic Record")
        print("5. Delete Academic Record")
        print("6. Academic Report")
        print("7. Back")

        choice = input("\nEnter Choice : ")

        if choice == "1":

            student_id = int(input("Student ID : "))
            cgpa = float(input("CGPA : "))
            backlogs = int(input("Backlogs : "))
            quiz_average = float(input("Quiz Average : "))

            add_academic(
                student_id,
                cgpa,
                backlogs,
                quiz_average
            )

        elif choice == "2":

            view_academics()

        elif choice == "3":

            student_id = int(input("Student ID : "))

            search_academic(student_id)

        elif choice == "4":

            student_id = int(input("Student ID : "))
            new_cgpa = float(input("New CGPA : "))

            update_academic(student_id, new_cgpa)

        elif choice == "5":

            student_id = int(input("Student ID : "))

            delete_academic(student_id)

        elif choice == "6":

            academic_report()

        elif choice == "7":

            break

        else:

            print("\n Invalid Choice!\n")


# =====================================
# LIFESTYLE MENU
# =====================================

def lifestyle_menu():

    while True:

        print("\n======= LIFESTYLE MENU =======")

        print("1. Add Lifestyle Record")
        print("2. View Lifestyle Records")
        print("3. Search Lifestyle Record")
        print("4. Update Lifestyle Record")
        print("5. Delete Lifestyle Record")
        print("6. Lifestyle Report")
        print("7. Back")

        choice = input("\nEnter Choice : ")

        if choice == "1":

            student_id = int(input("Student ID : "))
            sleep_hours = float(input("Sleep Hours : "))
            water_intake = float(input("Water Intake (L) : "))
            exercise_minutes = int(input("Exercise Minutes : "))
            screen_time = float(input("Screen Time (Hours) : "))

            add_lifestyle(
                student_id,
                sleep_hours,
                water_intake,
                exercise_minutes,
                screen_time
            )

        elif choice == "2":

            view_lifestyle()

        elif choice == "3":

            student_id = int(input("Student ID : "))

            search_lifestyle(student_id)

        elif choice == "4":

            student_id = int(input("Student ID : "))
            new_sleep_hours = float(input("New Sleep Hours : "))

            update_lifestyle(student_id, new_sleep_hours)

        elif choice == "5":

            student_id = int(input("Student ID : "))

            delete_lifestyle(student_id)

        elif choice == "6":

            lifestyle_report()

        elif choice == "7":

            break

        else:

            print("\n Invalid Choice!\n")

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