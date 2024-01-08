package org.example;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoadTest {
        public static int test(String url) throws IOException, InterruptedException {
            HttpResponse<String> response;
            try (var httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build()) {
                var request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            }
            return response.statusCode();
        }
    public static int test(String url, int numberOfRequests){
            return 200;
    }
    public static int test(String url, int numberOfRequests, int numberOfConcurrentRequests){
        return 200;
    }
    public static int test(int numberOfRequests, int numberOfConcurrentRequests, String fileName) {
        return 200;
    }
}
