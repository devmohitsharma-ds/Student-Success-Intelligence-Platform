from src.student import (
    add_student,
    view_students,
    search_student,
    update_student,
    delete_student
)


def start_menu():

    while True:

        print("\n==========================================")
        print(" Student Success Intelligence Platform")
        print("==========================================")

        print("1. Add Student")
        print("2. View Students")
        print("3. Search Student")
        print("4. Update Student")
        print("5. Delete Student")
        print("6. Exit")

        choice = input("\nEnter Your Choice : ")

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

            email = input("Enter Email : ")

            search_student(email)

            input("\nPress Enter to continue...")

        elif choice == "4":

            email = input("Enter Email : ")

            new_phone = input("Enter New Phone : ")

            update_student(email, new_phone)

            input("\nPress Enter to continue...")

        elif choice == "5":

            email = input("Enter Email : ")

            delete_student(email)

            input("\nPress Enter to continue...")

        elif choice == "6":

            print("\nThank you for using SSIP ❤️")
            break

        else:

            print("\nInvalid Choice!")

            input("\nPress Enter to continue...")