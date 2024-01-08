package org.example;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class LoadTest {
    private static record RequestResponseInfo(int statusCode, long responseTime) {}
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
    public static List<RequestResponseInfo> test(String url, int numberOfRequests){
        var results = new ArrayList<RequestResponseInfo>(numberOfRequests);
        for (int i = 0; i < numberOfRequests; i++) {
            try {
                var startTime = System.currentTimeMillis();
                var response = test(url);
                results.add(new RequestResponseInfo(response, System.currentTimeMillis() - startTime));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
    public static int test(String url, int numberOfRequests, int numberOfConcurrentRequests){
        return 200;
    }
    public static int test(int numberOfRequests, int numberOfConcurrentRequests, String fileName) {
        return 200;
    }
}
