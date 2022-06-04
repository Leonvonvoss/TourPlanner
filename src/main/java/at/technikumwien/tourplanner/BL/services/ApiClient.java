package at.technikumwien.tourplanner.BL.services;

import javafx.util.Pair;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.time.temporal.ChronoUnit.SECONDS;

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
    /*private ApiClient() {
        throw new IllegalStateException("Utility class");
    }

    public static Pair<String,Double> getTourAPIData(String from, String to, String name, String mode) {
        String key = getKey();

        // create a client
        var client = HttpClient.newHttpClient();


        String routeType = switch (mode) {
            case "Motor Vehicle":
                yield "fastest";
            case "On Foot":
                yield "pedestrian";
            case "Bicycle":
                yield "bicycle";
            default:
                throw new IllegalArgumentException("Unexpected value: " + mode);
        };

        String routeRequestURL = "https://www.mapquestapi.com/directions/v2/route?key=" + key +
                "&from=" + from.replaceAll("[^a-zA-Z0-9äöüÄÖÜß_,\\- ]", "").replace(" ", "+")
                + "&to=" + to.replaceAll("[^a-zA-Z0-9äöüÄÖÜß_,\\- ]", "").replace(" ", "+") + "&routeType=" + routeType;

        HttpRequest routeRequest = HttpRequest.newBuilder(
                        URI.create(routeRequestURL))
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        // use the client to send the request
        CompletableFuture<HttpResponse<String>> routeResponseFuture = client.sendAsync(routeRequest, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> routeResponse;
        var responseBody = "";

        routeResponse = routeResponseFuture.get();
        responseBody = routeResponse.body();




        var boundingBox = "";
        var sessionID = "";

        var distance = 0.0;

        var mapper = new ObjectMapper();

            var rootNode  = mapper.readTree(responseBody);
            var routeNode = rootNode.path("route");
            sessionID = routeNode.path("sessionId").textValue();
            distance = routeNode.path("distance").doubleValue();
            var boundingBoxNode = routeNode.path("boundingBox");
            boundingBox += boundingBoxNode.path("lr").path("lat").doubleValue()+",";
            boundingBox += boundingBoxNode.path("lr").path("lng").doubleValue()+",";
            boundingBox += boundingBoxNode.path("ul").path("lat").doubleValue()+",";
            boundingBox += boundingBoxNode.path("ul").path("lng").doubleValue();



        String mapRequestURL = "https://www.mapquestapi.com/staticmap/v5/map?key=" + key + "&boundingBox="
                + boundingBox + "&session=" + sessionID + "&format=jpg&size=900,300&locale=en_GB";


        HttpRequest mapRequest = HttpRequest.newBuilder(
                        URI.create(mapRequestURL))
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        var file = Path.of(getImageDir()+fileName+"_"+name.replaceAll("[^a-zA-Z0-9äöüÄÖÜß_\\-]", "")+".jpg");
        file.toFile().getParentFile().mkdirs();
        CompletableFuture<HttpResponse<Path>> mapResponseFuture = client.sendAsync(mapRequest, HttpResponse.BodyHandlers.ofFile(file,  CREATE, WRITE));
        var mapPath = "";
        var mapResponse = mapResponseFuture.get();
        mapPath = mapResponse.body().toString();


        return new Pair<>(mapPath,distance);
    }*/

}
