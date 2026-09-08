from flask import Blueprint, request

from backend.services.lifestyle_service import (
    get_all_lifestyle,
    get_lifestyle_by_student_id,
    create_lifestyle,
    update_lifestyle,
    delete_lifestyle
)


lifestyle_bp = Blueprint("lifestyle", __name__)


# =====================================
# GET ALL LIFESTYLE RECORDS
# =====================================

@lifestyle_bp.route("/lifestyle", methods=["GET"])
def lifestyle():

    return get_all_lifestyle()


# =====================================
# GET LIFESTYLE BY STUDENT ID
# =====================================

@lifestyle_bp.route("/lifestyle/<int:student_id>", methods=["GET"])
def get_lifestyle(student_id):

    record = get_lifestyle_by_student_id(student_id)

    if record is None:

        return {
            "error": "Lifestyle record not found"
        }, 404

    return record


# =====================================
# CREATE LIFESTYLE RECORD
# =====================================

@lifestyle_bp.route("/lifestyle", methods=["POST"])
def add_lifestyle():

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    required_fields = [
        "student_id",
        "sleep_hours",
        "water_intake",
        "exercise_minutes",
        "screen_time"
    ]

    for field in required_fields:

        if field not in data:

            return {
                "error": f"Missing field: {field}"
            }, 400

    try:

        lifestyle_id = create_lifestyle(
            data["student_id"],
            data["sleep_hours"],
            data["water_intake"],
            data["exercise_minutes"],
            data["screen_time"]
        )

        return {
            "message": "Lifestyle record created successfully",
            "lifestyle_id": lifestyle_id
        }, 201

    except Exception as error:

        return {
            "error": str(error)
        }, 400


# =====================================
# UPDATE LIFESTYLE RECORD
# =====================================

@lifestyle_bp.route("/lifestyle/<int:student_id>", methods=["PUT"])
def edit_lifestyle(student_id):

    data = request.get_json()

    if not data:

        return {
            "error": "Request body is required"
        }, 400

    required_fields = [
        "sleep_hours",
        "water_intake",
        "exercise_minutes",
        "screen_time"
    ]

    for field in required_fields:

        if field not in data:

            return {
                "error": f"Missing field: {field}"
            }, 400

    try:

        updated = update_lifestyle(
            student_id,
            data["sleep_hours"],
            data["water_intake"],
            data["exercise_minutes"],
            data["screen_time"]
        )

        if updated == 0:

            return {
                "error": "Lifestyle record not found"
            }, 404

        return {
            "message": "Lifestyle record updated successfully",
            "student_id": student_id
        }, 200

    except Exception as error:

        return {
            "error": str(error)
        }, 400


# =====================================
# DELETE LIFESTYLE RECORD
# =====================================

@lifestyle_bp.route("/lifestyle/<int:student_id>", methods=["DELETE"])
def remove_lifestyle(student_id):

    deleted = delete_lifestyle(student_id)

    if deleted == 0:

        return {
            "error": "Lifestyle record not found"
        }, 404

    return {
        "message": "Lifestyle record deleted successfully",
        "student_id": student_id
    }, 200