package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9876), 0);
        server.createContext("/ping", new CallHandler());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.start();
    }

    static class CallHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            byte [] body = "OK".getBytes();
            exchange.sendResponseHeaders(200, body.length);
            try(OutputStream os = exchange.getResponseBody()){
                os.write(body);
            }

        }
    }
}
