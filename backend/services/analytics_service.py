from src.database import get_connection


# =====================================
# GET COMPLETE STUDENT ANALYTICS
# =====================================

def get_student_analytics(student_id):

    connection = get_connection()
    cursor = connection.cursor()

    try:

        cursor.execute("""
            SELECT
                s.student_id,
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

        if record is None:
            return None

        attendance = record[4]
        cgpa = record[5]
        backlogs = record[6]
        quiz_average = record[7]

        sleep = record[8]
        water = record[9]
        exercise = record[10]
        screen_time = record[11]

        # =====================================
        # ATTENDANCE ANALYSIS
        # =====================================

        if attendance is None:
            attendance_status = "No Data"
        elif attendance >= 90:
            attendance_status = "Excellent"
        elif attendance >= 75:
            attendance_status = "Good"
        elif attendance >= 60:
            attendance_status = "Average"
        else:
            attendance_status = "Poor"

        # =====================================
        # ACADEMIC ANALYSIS
        # =====================================

        if cgpa is None:
            academic_status = "No Data"
        elif cgpa >= 8.5:
            academic_status = "Excellent"
        elif cgpa >= 7.0:
            academic_status = "Good"
        elif cgpa >= 6.0:
            academic_status = "Average"
        else:
            academic_status = "At Risk"

        # =====================================
        # LIFESTYLE ANALYSIS
        # =====================================

        lifestyle_warning = False
        lifestyle_reasons = []

        if sleep is not None and sleep < 6:
            lifestyle_warning = True
            lifestyle_reasons.append("Low sleep")

        if water is not None and water < 2:
            lifestyle_warning = True
            lifestyle_reasons.append("Low water intake")

        if exercise is not None and exercise < 20:
            lifestyle_warning = True
            lifestyle_reasons.append("Low exercise")

        if screen_time is not None and screen_time > 8:
            lifestyle_warning = True
            lifestyle_reasons.append("High screen time")

        if (
            sleep is None
            and water is None
            and exercise is None
            and screen_time is None
        ):
            lifestyle_status = "No Data"

        elif lifestyle_warning:
            lifestyle_status = "Needs Improvement"

        else:
            lifestyle_status = "Good"

        # =====================================
        # RISK ANALYSIS
        # =====================================

        risk_score = 0

        if attendance is not None and attendance < 75:
            risk_score += 1

        if cgpa is not None and cgpa < 6.0:
            risk_score += 1

        if backlogs is not None and backlogs > 0:
            risk_score += 1

        if lifestyle_warning:
            risk_score += 1

        # =====================================
        # NO DATA CHECK
        # =====================================

        no_attendance_data = attendance is None
        no_academic_data = (
            cgpa is None
            and backlogs is None
            and quiz_average is None
        )

        no_lifestyle_data = (
            sleep is None
            and water is None
            and exercise is None
            and screen_time is None
        )

        if (
            no_attendance_data
            and no_academic_data
            and no_lifestyle_data
        ):
            overall_risk = "NO_DATA"

        elif risk_score == 0:
            overall_risk = "LOW"

        elif risk_score == 1:
            overall_risk = "MODERATE"

        else:
            overall_risk = "HIGH"

        # =====================================
        # RETURN ANALYTICS
        # =====================================

        return {
            "student": {
                "student_id": record[0],
                "full_name": record[1],
                "department": record[2],
                "semester": record[3]
            },

            "attendance": {
                "percentage": attendance,
                "status": attendance_status
            },

            "academic": {
                "cgpa": cgpa,
                "backlogs": backlogs,
                "quiz_average": quiz_average,
                "status": academic_status
            },

            "lifestyle": {
                "sleep_hours": sleep,
                "water_intake": water,
                "exercise_minutes": exercise,
                "screen_time": screen_time,
                "status": lifestyle_status,
                "warnings": lifestyle_reasons
            },

            "risk_analysis": {
                "risk_score": risk_score,
                "overall_risk": overall_risk
            }
        }

    finally:
        connection.close()
