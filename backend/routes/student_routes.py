from flask import Blueprint, request

from backend.services.student_service import (
    get_all_students,
    get_student_by_id,
    create_student,
    update_student,
    delete_student
)


student_bp = Blueprint("students", __name__)


# =====================================
# GET ALL STUDENTS
# =====================================

@student_bp.route("/students", methods=["GET"])
def students():

    return get_all_students()


# =====================================
# GET STUDENT BY ID
# =====================================

@student_bp.route("/students/<int:student_id>", methods=["GET"])
def get_student(student_id):

    student = get_student_by_id(student_id)

    if student is None:

        return {
            "error": "Student not found"
        }, 404

    return student


# =====================================
# CREATE STUDENT
# =====================================

@student_bp.route("/students", methods=["POST"])
def add_student():

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    required_fields = [
        "full_name",
        "gender",
        "department",
        "semester",
        "email",
        "phone",
        "admission_year"
    ]

    for field in required_fields:

        if field not in data:

            return {
                "error": f"Missing field: {field}"
            }, 400

    try:

        student_id = create_student(
            data["full_name"],
            data["gender"],
            data["department"],
            data["semester"],
            data["email"],
            data["phone"],
            data["admission_year"]
        )

        return {
            "message": "Student created successfully",
            "student_id": student_id
        }, 201

    except Exception as error:

        return {
            "error": str(error)
        }, 400

# =====================================
# UPDATE STUDENT
# =====================================

@student_bp.route("/students/<int:student_id>", methods=["PUT"])
def edit_student(student_id):

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    required_fields = [
        "full_name",
        "gender",
        "department",
        "semester",
        "email",
        "phone",
        "admission_year"
    ]

    for field in required_fields:

        if field not in data:

            return {
                "error": f"Missing field: {field}"
            }, 400

    try:

        updated = update_student(
            student_id,
            data["full_name"],
            data["gender"],
            data["department"],
            data["semester"],
            data["email"],
            data["phone"],
            data["admission_year"]
        )

        if updated == 0:

            return {
                "error": "Student not found"
            }, 404

        return {
            "message": "Student updated successfully",
            "student_id": student_id
        }, 200

    except Exception as error:

        return {
            "error": str(error)
        }, 400

# =====================================
# DELETE STUDENT
# =====================================

@student_bp.route("/students/<int:student_id>", methods=["DELETE"])
def remove_student(student_id):

    deleted = delete_student(student_id)

    if deleted == 0:

        return {
            "error": "Student not found"
        }, 404

    return {
        "message": "Student deleted successfully",
        "student_id": student_id
    }, 200