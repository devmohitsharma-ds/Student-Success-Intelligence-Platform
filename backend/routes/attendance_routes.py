from flask import Blueprint, request

from backend.services.attendance_service import (
    get_all_attendance,
    get_attendance_by_student_id,
    create_attendance,
    update_attendance,
    delete_attendance
)


attendance_bp = Blueprint("attendance", __name__)


# =====================================
# GET ALL ATTENDANCE
# =====================================

@attendance_bp.route("/attendance", methods=["GET"])
def attendance():

    return get_all_attendance()


# =====================================
# GET ATTENDANCE BY STUDENT ID
# =====================================

@attendance_bp.route("/attendance/<int:student_id>", methods=["GET"])
def get_attendance(student_id):

    record = get_attendance_by_student_id(student_id)

    if record is None:

        return {
            "error": "Attendance record not found"
        }, 404

    return record


# =====================================
# CREATE ATTENDANCE
# =====================================

@attendance_bp.route("/attendance", methods=["POST"])
def add_attendance():

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    required_fields = [
        "student_id",
        "attendance_percentage"
    ]

    for field in required_fields:

        if field not in data:

            return {
                "error": f"Missing field: {field}"
            }, 400

    try:

        attendance_id = create_attendance(
            data["student_id"],
            data["attendance_percentage"]
        )

        return {
            "message": "Attendance created successfully",
            "attendance_id": attendance_id
        }, 201

    except Exception as error:

        return {
            "error": str(error)
        }, 400


# =====================================
# UPDATE ATTENDANCE
# =====================================

@attendance_bp.route("/attendance/<int:student_id>", methods=["PUT"])
def edit_attendance(student_id):

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    if "attendance_percentage" not in data:

        return {
            "error": "Missing field: attendance_percentage"
        }, 400

    try:

        updated = update_attendance(
            student_id,
            data["attendance_percentage"]
        )

        if updated == 0:

            return {
                "error": "Attendance record not found"
            }, 404

        return {
            "message": "Attendance updated successfully",
            "student_id": student_id
        }, 200

    except Exception as error:

        return {
            "error": str(error)
        }, 400


# =====================================
# DELETE ATTENDANCE
# =====================================

@attendance_bp.route("/attendance/<int:student_id>", methods=["DELETE"])
def remove_attendance(student_id):

    deleted = delete_attendance(student_id)

    if deleted == 0:

        return {
            "error": "Attendance record not found"
        }, 404

    return {
        "message": "Attendance deleted successfully",
        "student_id": student_id
    }, 200