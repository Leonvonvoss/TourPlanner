package at.technikumwien.tourplanner.BL.services;

import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.json.*;

public class ApiClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        String connectionURL = "https://open.mapquestapi.com/directions/v2/route?key=PRlLqBQT4IAjPDI64URtUOt6G6LmFPeP&from=Vienna&to=Salzburg";
        ApiClient apiClient = new ApiClient(connectionURL);
        apiClient.sendRequestAsync(connectionURL);
    }

    public ApiClient(String connectionURL) throws IOException, InterruptedException {

    }

    private void sendRequest(String connectionURL) throws IOException, InterruptedException {
        URI resourceURL = URI.create(connectionURL);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceURL).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        JSONObject route = json.getJSONObject("route");
        System.out.println(route.get("distance"));
    }

    private void sendRequestAsync(String connectionURL) throws IOException, InterruptedException {
        URI resourceURL = URI.create(connectionURL);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceURL).GET().build();
        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        httpResponseCompletableFuture.thenApply(HttpResponse::body).thenAccept(System.out::println);
        httpResponseCompletableFuture.join();

    }

    private static void handleResponse(String response) {
        JSONObject json = new JSONObject(response);
        String pretty = json.toString(4);
        System.out.println(pretty);
    }

}
