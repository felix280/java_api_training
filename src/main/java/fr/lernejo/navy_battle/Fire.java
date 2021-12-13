package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Fire implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("GET")){//On check le verbe de la requête
            InputStreamReader is = new InputStreamReader(exchange.getRequestBody(),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(is);
            int tmp;
            StringBuilder stringBuilder = new StringBuilder();
            while((tmp = bufferedReader.read()) != -1)
                stringBuilder.append((char) tmp);

            String requestBody = stringBuilder.toString();

            String response = "{\n" +
                "    \"$schema\": \"http://json-schema.org/schema#\",\n" +
                "    \"type\": \"object\",\n" +
                "    \"properties\": {\n" +
                "        \"consequence\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"enum\": [\"miss\", \"hit\", \"sunk\"]\n" +
                "        },\n" +
                "        \"shipLeft\": {\n" +
                "            \"type\": \"boolean\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"required\": [\n" +
                "        \"consequence\",\n" +
                "        \"shipLeft\"\n" +
                "    ]\n" +
                "}";


            System.out.print(requestBody);
            byte [] body = "Lourd ça marche".getBytes();
            exchange.sendResponseHeaders(202, body.length);
            try(OutputStream os = exchange.getResponseBody()){
                os.write(body);
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
}
