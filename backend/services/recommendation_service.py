from backend.services.analytics_service import get_student_analytics


# =====================================
# GET STUDENT RECOMMENDATIONS
# =====================================

def get_student_recommendations(student_id):

    analytics = get_student_analytics(student_id)

    if analytics is None:
        return None

    recommendations = []

    academic = analytics["academic"]
    attendance = analytics["attendance"]
    lifestyle = analytics["lifestyle"]
    risk = analytics["risk_analysis"]

    # =====================================
    # ACADEMIC RECOMMENDATIONS
    # =====================================

    cgpa = academic["cgpa"]
    backlogs = academic["backlogs"]
    quiz_average = academic["quiz_average"]

    if cgpa is not None:

        if cgpa < 6.0:
            recommendations.append({
                "category": "Academic",
                "priority": "HIGH",
                "message": "Focus on improving academic performance and create a structured study plan."
            })

        elif cgpa < 7.0:
            recommendations.append({
                "category": "Academic",
                "priority": "MEDIUM",
                "message": "Improve your CGPA by focusing on weaker subjects and maintaining a consistent study routine."
            })

    if backlogs is not None and backlogs > 0:

        recommendations.append({
            "category": "Academic",
            "priority": "HIGH",
            "message": f"Prioritize clearing your {backlogs} backlog(s) to reduce academic risk."
        })

    if quiz_average is not None and quiz_average < 60:

        recommendations.append({
            "category": "Academic",
            "priority": "MEDIUM",
            "message": "Improve quiz performance through regular revision and practice tests."
        })

    # =====================================
    # ATTENDANCE RECOMMENDATIONS
    # =====================================

    attendance_percentage = attendance["percentage"]

    if attendance_percentage is not None:

        if attendance_percentage < 75:

            recommendations.append({
                "category": "Attendance",
                "priority": "HIGH",
                "message": "Improve class attendance and maintain at least 75% attendance."
            })

        elif attendance_percentage < 85:

            recommendations.append({
                "category": "Attendance",
                "priority": "MEDIUM",
                "message": "Try to improve attendance further to maintain a healthy academic routine."
            })

    # =====================================
    # LIFESTYLE RECOMMENDATIONS
    # =====================================

    sleep = lifestyle["sleep_hours"]
    water = lifestyle["water_intake"]
    exercise = lifestyle["exercise_minutes"]
    screen_time = lifestyle["screen_time"]

    if sleep is not None and sleep < 6:

        recommendations.append({
            "category": "Lifestyle",
            "priority": "HIGH",
            "message": "Increase sleep duration and maintain a consistent sleep schedule."
        })

    if water is not None and water < 2:

        recommendations.append({
            "category": "Lifestyle",
            "priority": "MEDIUM",
            "message": "Increase daily water intake and maintain regular hydration."
        })

    if exercise is not None and exercise < 20:

        recommendations.append({
            "category": "Lifestyle",
            "priority": "MEDIUM",
            "message": "Increase daily physical activity and include regular exercise in your routine."
        })

    if screen_time is not None and screen_time > 8:

        recommendations.append({
            "category": "Lifestyle",
            "priority": "HIGH",
            "message": "Reduce excessive screen time and take regular breaks from screens."
        })

    # =====================================
    # OVERALL RISK RECOMMENDATION
    # =====================================

    overall_risk = risk["overall_risk"]

    if overall_risk == "HIGH":

        recommendations.append({
            "category": "Overall Risk",
            "priority": "HIGH",
            "message": "Your current risk level is HIGH. Prioritize academic improvement and lifestyle changes."
        })

    elif overall_risk == "MODERATE":

        recommendations.append({
            "category": "Overall Risk",
            "priority": "MEDIUM",
            "message": "Your risk level is MODERATE. Focus on the identified weak areas before they become serious problems."
        })

    elif overall_risk == "LOW":

        recommendations.append({
            "category": "Overall Risk",
            "priority": "LOW",
            "message": "Your current risk level is LOW. Maintain your academic, attendance, and lifestyle habits."
        })

    elif overall_risk == "NO_DATA":

        recommendations.append({
            "category": "Overall Risk",
            "priority": "INFO",
            "message": "Not enough academic, attendance, or lifestyle data is available to generate meaningful recommendations."
        })

    # =====================================
    # RETURN RECOMMENDATIONS
    # =====================================

    return {
        "student": analytics["student"],
        "risk_analysis": risk,
        "recommendations": recommendations
    }
