package frontend;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiClient {

    // =====================================
    // BACKEND CONFIGURATION
    // =====================================

    private static final String BASE_URL =
            "http://127.0.0.1:5000";

    private static final HttpClient CLIENT =
            HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();


    // =====================================
    // GET REQUEST
    // =====================================

    public static String get(String endpoint)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .timeout(Duration.ofSeconds(15))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        return handleResponse(response);
    }


    // =====================================
    // POST REQUEST
    // =====================================

    public static String post(
            String endpoint,
            String json
    ) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .timeout(Duration.ofSeconds(15))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(
                        HttpRequest.BodyPublishers.ofString(json)
                )
                .build();

        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        return handleResponse(response);
    }


    // =====================================
    // PUT REQUEST
    // =====================================

    public static String put(
            String endpoint,
            String json
    ) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .timeout(Duration.ofSeconds(15))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .PUT(
                        HttpRequest.BodyPublishers.ofString(json)
                )
                .build();

        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        return handleResponse(response);
    }


    // =====================================
    // DELETE REQUEST
    // =====================================

    public static String delete(String endpoint)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .timeout(Duration.ofSeconds(15))
                .header("Accept", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        return handleResponse(response);
    }


    // =====================================
    // RESPONSE HANDLER
    // =====================================

    private static String handleResponse(
            HttpResponse<String> response
    ) throws IOException {

        int statusCode = response.statusCode();

        if (statusCode >= 200 && statusCode < 300) {
            return response.body();
        }

        throw new IOException(
                "API request failed. HTTP "
                        + statusCode
                        + ": "
                        + response.body()
        );
    }


    // =====================================
    // STUDENT API
    // =====================================

    public static String getStudents()
            throws IOException, InterruptedException {

        return get("/students");
    }


    public static String getStudent(int studentId)
            throws IOException, InterruptedException {

        return get("/students/" + studentId);
    }


    public static String createStudent(String json)
            throws IOException, InterruptedException {

        return post("/students", json);
    }


    public static String updateStudent(
            int studentId,
            String json
    ) throws IOException, InterruptedException {

        return put("/students/" + studentId, json);
    }


    public static String deleteStudent(int studentId)
            throws IOException, InterruptedException {

        return delete("/students/" + studentId);
    }


    // =====================================
    // ACADEMIC API
    // =====================================

    public static String getAcademics()
            throws IOException, InterruptedException {

        return get("/academics");
    }


    public static String getAcademic(int studentId)
            throws IOException, InterruptedException {

        return get("/academics/" + studentId);
    }


    public static String createAcademic(String json)
            throws IOException, InterruptedException {

        return post("/academics", json);
    }


    public static String updateAcademic(
            int studentId,
            String json
    ) throws IOException, InterruptedException {

        return put("/academics/" + studentId, json);
    }


    public static String deleteAcademic(int studentId)
            throws IOException, InterruptedException {

        return delete("/academics/" + studentId);
    }


    // =====================================
    // ATTENDANCE API
    // =====================================

    public static String getAttendance()
            throws IOException, InterruptedException {

        return get("/attendance");
    }


    public static String getAttendance(int studentId)
            throws IOException, InterruptedException {

        return get("/attendance/" + studentId);
    }


    public static String createAttendance(String json)
            throws IOException, InterruptedException {

        return post("/attendance", json);
    }


    public static String updateAttendance(
            int studentId,
            String json
    ) throws IOException, InterruptedException {

        return put("/attendance/" + studentId, json);
    }


    public static String deleteAttendance(int studentId)
            throws IOException, InterruptedException {

        return delete("/attendance/" + studentId);
    }


    // =====================================
    // LIFESTYLE API
    // =====================================

    public static String getLifestyle()
            throws IOException, InterruptedException {

        return get("/lifestyle");
    }


    public static String getLifestyle(int studentId)
            throws IOException, InterruptedException {

        return get("/lifestyle/" + studentId);
    }


    public static String createLifestyle(String json)
            throws IOException, InterruptedException {

        return post("/lifestyle", json);
    }


    public static String updateLifestyle(
            int studentId,
            String json
    ) throws IOException, InterruptedException {

        return put("/lifestyle/" + studentId, json);
    }


    public static String deleteLifestyle(int studentId)
            throws IOException, InterruptedException {

        return delete("/lifestyle/" + studentId);
    }


    // =====================================
    // ANALYTICS API
    // =====================================

    public static String getAnalytics(int studentId)
            throws IOException, InterruptedException {

        return get("/analytics/" + studentId);
    }


    // =====================================
    // RECOMMENDATION API
    // =====================================

    public static String getRecommendations(int studentId)
            throws IOException, InterruptedException {

        return get("/recommendations/" + studentId);
    }


    // =====================================
    // BACKEND HEALTH CHECK
    // =====================================

    public static String healthCheck()
            throws IOException, InterruptedException {

        return get("/");
    }
}
