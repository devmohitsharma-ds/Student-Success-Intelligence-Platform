from flask import Blueprint

from backend.services.analytics_service import get_student_analytics


analytics_bp = Blueprint("analytics", __name__)


# =====================================
# GET STUDENT ANALYTICS
# =====================================

@analytics_bp.route("/analytics/<int:student_id>", methods=["GET"])
def student_analytics(student_id):

    result = get_student_analytics(student_id)

    if result is None:
        return {
            "error": "Student not found"
        }, 404

    return result, 200
