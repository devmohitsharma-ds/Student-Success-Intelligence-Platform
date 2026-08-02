from src.database import get_connection


def create_tables():

    connection = get_connection()
    cursor = connection.cursor()

    # Students Table
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS students(

        student_id INTEGER PRIMARY KEY AUTOINCREMENT,

        full_name TEXT NOT NULL,

        gender TEXT NOT NULL,

        department TEXT NOT NULL,

        semester INTEGER NOT NULL,

        email TEXT UNIQUE,

        phone TEXT,

        admission_year INTEGER NOT NULL

    )
    """)

    # Attendance Table
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS attendance(

        attendance_id INTEGER PRIMARY KEY AUTOINCREMENT,

        student_id INTEGER,

        attendance_percentage REAL,

        FOREIGN KEY(student_id)
        REFERENCES students(student_id)

    )
    """)

    # Academics Table
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS academics(

        academic_id INTEGER PRIMARY KEY AUTOINCREMENT,

        student_id INTEGER,

        cgpa REAL,

        backlogs INTEGER,

        quiz_average REAL,

        FOREIGN KEY(student_id)
        REFERENCES students(student_id)

    )
    """)

    # Lifestyle Table
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS lifestyle(

        lifestyle_id INTEGER PRIMARY KEY AUTOINCREMENT,

        student_id INTEGER,

        sleep_hours REAL,

        water_intake REAL,

        exercise_minutes INTEGER,

        screen_time REAL,

        FOREIGN KEY(student_id)
        REFERENCES students(student_id)

    )
    """)

    connection.commit()

    connection.close()

    print("All SSIP Tables Created Successfully!")