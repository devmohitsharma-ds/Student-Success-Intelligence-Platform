from flask import Blueprint, request

from backend.services.academic_service import (
    get_all_academics,
    get_academic_by_student_id,
    create_academic,
    update_academic,
    delete_academic
)


academic_bp = Blueprint("academic", __name__)


# =====================================
# GET ALL ACADEMIC RECORDS
# =====================================

@academic_bp.route("/academics", methods=["GET"])
def academics():

    return get_all_academics()


# =====================================
# GET ACADEMIC RECORD BY STUDENT ID
# =====================================

@academic_bp.route("/academics/<int:student_id>", methods=["GET"])
def get_academic(student_id):

    record = get_academic_by_student_id(student_id)

    if record is None:

        return {
            "error": "Academic record not found"
        }, 404

    return record


# =====================================
# CREATE ACADEMIC RECORD
# =====================================

@academic_bp.route("/academics", methods=["POST"])
def add_academic():

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    required_fields = [
        "student_id",
        "cgpa",
        "backlogs",
        "quiz_average"
    ]

    for field in required_fields:

        if field not in data:

            return {
                "error": f"Missing field: {field}"
            }, 400

    try:

        academic_id = create_academic(
            data["student_id"],
            data["cgpa"],
            data["backlogs"],
            data["quiz_average"]
        )

        return {
            "message": "Academic record created successfully",
            "academic_id": academic_id
        }, 201

    except Exception as error:

        return {
            "error": str(error)
        }, 400


# =====================================
# UPDATE ACADEMIC RECORD
# =====================================

@academic_bp.route("/academics/<int:student_id>", methods=["PUT"])
def edit_academic(student_id):

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    required_fields = [
        "cgpa",
        "backlogs",
        "quiz_average"
    ]

    for field in required_fields:

        if field not in data:

            return {
                "error": f"Missing field: {field}"
            }, 400

    try:

        updated = update_academic(
            student_id,
            data["cgpa"],
            data["backlogs"],
            data["quiz_average"]
        )

        if updated == 0:

            return {
                "error": "Academic record not found"
            }, 404

        return {
            "message": "Academic record updated successfully",
            "student_id": student_id
        }, 200

    except Exception as error:

        return {
            "error": str(error)
        }, 400


# =====================================
# DELETE ACADEMIC RECORD
# =====================================

@academic_bp.route("/academics/<int:student_id>", methods=["DELETE"])
def remove_academic(student_id):

    deleted = delete_academic(student_id)

    if deleted == 0:

        return {
            "error": "Academic record not found"
        }, 404

    return {
        "message": "Academic record deleted successfully",
        "student_id": student_id
    }, 200