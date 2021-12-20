package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    public void Send_POST_Request(String sender, String reciever) throws URISyntaxException {
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
        /*Game my_game = new Game();
        try {
            my_game.current_Game("http://localhost:9876", "http://localhost:8795");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
        //Send_GET_Request(sender, reciever, "A8");
    }
    public void Send_GET_Request(String sender, String reciever, String case_Visee) throws URISyntaxException {

        HttpRequest requeteGet = HttpRequest.newBuilder()
            .uri(URI.create(reciever + "/api/game/fire"))
            .setHeader("Content-Type", "text/plain;charset=UTF-8")
            .setHeader("cell", case_Visee)
            .setHeader("sender", sender)
            .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.sendAsync(requeteGet, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
    }


}
