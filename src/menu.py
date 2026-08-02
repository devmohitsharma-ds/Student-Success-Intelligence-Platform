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


def start_menu():

    while True:

        print("\n========================================")
        print(" Student Success Intelligence Platform")
        print("========================================")

        print("1. Student Management")
        print("2. Attendance Management")
        print("3. Exit")

        choice = input("\nEnter Choice : ")

        if choice == "1":

            student_menu()

        elif choice == "2":

            attendance_menu()

        elif choice == "3":

            print("\nGoodbye!!🚀")
            print("Have a nice day!!")

            break

        else:

            print("\nInvalid Choice\n")


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