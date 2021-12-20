package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        int port = Integer.parseInt(args[0]);

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/ping", new CallHandler());
        server.createContext("/api/game/start",new StartGame());
        server.createContext("/api/game/fire", new Fire());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.start();
        Request request = new Request();
        if(args.length == 2){
            request.Send_POST_Request(args[0], args[1]);
        }
    }
}
