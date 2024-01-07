import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Replace with the actual URL of your API Gateway endpoint
        String apiUrl = "https://your-api-gateway-url/logs";

        // Number of concurrent requests
        int numRequests = 10;

        // Create a JSON payload for the POST request
        String jsonPayload = "{\"group\":\"right\",\"message\":\"(562, 78.19999694824219)\"}";

        // Run concurrent requests
        for (int i = 0; i < numRequests; i++) {
            Thread thread = new Thread(() -> sendPostRequest(apiUrl, jsonPayload));
            thread.start();
        }
    }

    private static void sendPostRequest(String url, String jsonPayload) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            // Set the JSON payload
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // Execute the request
            HttpResponse response = httpClient.execute(httpPost);

            // Read and print the response
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println("Response: " + responseEntity.getContent().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}