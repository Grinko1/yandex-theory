package ru.yandex.lecture.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        URI uri = URI.create("http://ipwhois.app/json/46.226.227.20?lang=ru");

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        HttpRequest request = requestBuilder

                .uri(uri)
                .GET()
//                .version(java.net.http.HttpClient.Version.HTTP_1_1)
//                .header("Accept", " text/html")
//                .header("Accept", " application/xml")
//                .header("Accept", " application/json")
                .build();


        HttpClient client = HttpClient.newHttpClient();


        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

        try {
            HttpResponse<String> response = client.send(request, handler);
            int status = response.statusCode();
            System.out.println("Code response " + status);
        if (status == 200){
            JsonElement jsonElement = JsonParser.parseString(response.body());
            if (!jsonElement.isJsonObject()){
                System.out.println("wrong response");
                return;
            }
        }
            if (status >= 200 && status <= 299){
                System.out.println("success");
                System.out.println("Body of response " + response.body());
            }


        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong");
        }

    }
}

