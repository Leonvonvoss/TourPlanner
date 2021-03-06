package at.technikumwien.tourplanner.BL.services;

import at.technikumwien.tourplanner.config.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.concurrent.ExecutionException;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.time.temporal.ChronoUnit.SECONDS;

import org.json.*;

public class ApiClient {

    private ApiClient() {
        throw new IllegalStateException("Utility class");
    }

    public static Pair<String,Pair<String, String >> getTourAPIData(String from, String to, String name, String mode) throws ExecutionException, InterruptedException, JsonProcessingException {
        Configuration configuration =Configuration.Instance();
        String key = configuration.getProperty("apikey");
        String imgdir = configuration.getProperty("imgdir");

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
        System.out.println(routeRequestURL);
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

        String distance = "0.0";
        String duration = "0.0";

        var mapper = new ObjectMapper();

            var rootNode  = mapper.readTree(responseBody);
            var routeNode = rootNode.path("route");
            sessionID = routeNode.path("sessionId").textValue();
            distance = routeNode.path("distance").toString();
            duration = routeNode.path("formattedTime").toString();
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
        var file = Path.of(imgdir+fileName+"_"+name.replaceAll("[^a-zA-Z0-9äöüÄÖÜß_\\-]", "")+".jpg");
        System.out.println(file);
        file.toFile().getParentFile().mkdirs();
        CompletableFuture<HttpResponse<Path>> mapResponseFuture = client.sendAsync(mapRequest, HttpResponse.BodyHandlers.ofFile(file,  CREATE, WRITE));
        var mapPath = "";
        var mapResponse = mapResponseFuture.get();
        mapPath = mapResponse.body().toString();

        System.out.println("Mappath; " + mapPath);
        System.out.println("Distance final; " + distance);
        System.out.println("formattedTime final; " + duration);
        Pair secondPair = new Pair<>(distance, duration);
        return new Pair<>(mapPath,secondPair);
    }

}
