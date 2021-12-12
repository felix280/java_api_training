package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    public void Send_Request(String sender, String reciever){
        System.out.print("envoie");
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(reciever + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + sender + "\", \"message\":\"hello\"}"))
            .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();
    }
}
