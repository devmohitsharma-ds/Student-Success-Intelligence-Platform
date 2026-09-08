from flask import Blueprint

from backend.services.recommendation_service import (
    get_student_recommendations
)


recommendation_bp = Blueprint(
    "recommendation",
    __name__
)


# =====================================
# GET STUDENT RECOMMENDATIONS
# =====================================

@recommendation_bp.route(
    "/recommendations/<int:student_id>",
    methods=["GET"]
)
def student_recommendations(student_id):

    result = get_student_recommendations(student_id)

    if result is None:
        return {
            "error": "Student not found"
        }, 404

    return result, 200
