package ru.yandex.lecture.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        URI uri = URI.create("https://httpstatuses.com/418");

        HttpRequest.Builder requestBuilder= HttpRequest.newBuilder();
        HttpRequest request = requestBuilder
                .GET()
                .uri(uri)
                .version(java.net.http.HttpClient.Version.HTTP_1_1)
                .header("Accept", " text/html")
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);
        System.out.println("Code response "+ response.statusCode());
        System.out.println("Body of response "+ response.body());
    }
}

