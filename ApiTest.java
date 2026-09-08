import frontend.ApiClient;

public class ApiTest {
    public static void main(String[] args) {
        try {
            System.out.println("BACKEND:");
            System.out.println(ApiClient.healthCheck());

            System.out.println("\nSTUDENTS:");
            System.out.println(ApiClient.getStudents());

            System.out.println("\nANALYTICS:");
            System.out.println(ApiClient.getAnalytics(12));

            System.out.println("\nRECOMMENDATIONS:");
            System.out.println(ApiClient.getRecommendations(12));

            System.out.println("\nCONNECTION SUCCESS");
        } catch (Exception e) {
            System.out.println("\nCONNECTION FAILED");
            e.printStackTrace();
        }
    }
}
