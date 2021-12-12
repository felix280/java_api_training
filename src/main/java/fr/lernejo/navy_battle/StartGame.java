package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class StartGame  implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("POST")){//On check le verbe de la requête
            JSONObject jsonObject =  recieve_Json(exchange); //On récupère le JSON reçu
            if(jsonObject.has("id") && jsonObject.has("url") &&jsonObject.has("message")){ //On analyse si il est de la bonne forme
                String response = "{\n" +
                    "    \"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\n" +
                    "    \"url\": \"http://localhost:9876\",\n" +
                    "    \"message\": \"May the best code win\"\n" +
                    "}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(202, response.length());
                try(OutputStream os = exchange.getResponseBody()){
                    os.write(response.getBytes());
                }
            }
            else{
                byte [] body = "JSON malformed".getBytes();
                exchange.sendResponseHeaders(400, body.length );
                try(OutputStream os = exchange.getResponseBody()){
                    os.write(body);
                }
            }

        }
        else{
            byte [] body = "Bad Request".getBytes();
            exchange.sendResponseHeaders(404, body.length );
            try(OutputStream os = exchange.getResponseBody()){
                os.write(body);
            }
        }
    }

    public JSONObject recieve_Json (HttpExchange exchange) throws IOException {
        InputStreamReader is = new InputStreamReader(exchange.getRequestBody(),"utf-8");
        BufferedReader bufferedReader = new BufferedReader(is);
        int tmp;
        StringBuilder stringBuilder = new StringBuilder();
        while((tmp = bufferedReader.read()) != -1)
            stringBuilder.append((char) tmp);

        String requestBody = stringBuilder.toString();
        JSONObject jsonObject = new JSONObject(requestBody);
        bufferedReader.close();
        is.close();
        return jsonObject;
    }
}
