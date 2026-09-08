from flask import Flask
from flask_cors import CORS

from backend.routes.student_routes import student_bp
from backend.routes.attendance_routes import attendance_bp
from backend.routes.academic_routes import academic_bp
from backend.routes.lifestyle_routes import lifestyle_bp
from backend.routes.analytics_routes import analytics_bp
from backend.routes.recommendation_routes import recommendation_bp


app = Flask(__name__)

# =====================================
# ENABLE CORS
# =====================================

CORS(app)


# =====================================
# REGISTER API ROUTES
# =====================================

app.register_blueprint(student_bp)
app.register_blueprint(attendance_bp)
app.register_blueprint(academic_bp)
app.register_blueprint(lifestyle_bp)
app.register_blueprint(analytics_bp)
app.register_blueprint(recommendation_bp)


# =====================================
# HOME
# =====================================

@app.route("/")
def home():

    return {
        "project": "Student Success Intelligence Platform",
        "backend": "Running",
        "version": "1.0.0"
    }


# =====================================
# START SERVER
# =====================================

if __name__ == "__main__":

    app.run(debug=True)