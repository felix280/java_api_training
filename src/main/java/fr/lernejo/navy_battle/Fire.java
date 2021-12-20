package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;

public class Fire implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equals("GET")){//On check le verbe de la requête
            System.out.println(exchange.getRequestHeaders().get("sender"));
            System.out.println(exchange.getRequestHeaders().get("cell"));
            InputStreamReader is = new InputStreamReader(exchange.getRequestBody(),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(is);
            int tmp;
            StringBuilder stringBuilder = new StringBuilder();
            while((tmp = bufferedReader.read()) != -1)
                stringBuilder.append((char) tmp);

            String requestBody = stringBuilder.toString();
            System.out.println(requestBody);

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
            try(OutputStream os = exchange.getResponseBody()) {
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
    public void fire(String host1, String host2) throws URISyntaxException {
        System.out.print("Entrez la case ou vous voulez tirer :");
        Scanner scanner = new Scanner(System.in);
        Request request = new Request();
        String Coordonee = scanner.nextLine();

        request.Send_GET_Request(host1, host2, Coordonee);
    }
    public String [][] Game(String [][] Plateau_Coordonnee) throws URISyntaxException {
        for (String[] row: Plateau_Coordonnee)
            Arrays.fill(row, "mer");
        Plateau_Coordonnee = create_boat(Plateau_Coordonnee);//Création des bateaux
        //Afficher_Plateau(Plateau_Coordonnee);
        return Plateau_Coordonnee;
    }

    public void Afficher_Plateau(String [][] Plateau){
        System.out.println(" \tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ");
        for(int j=0; j<Plateau.length;j++){
            System.out.print((j+1) + "\t");
            for(int k=0; k<Plateau.length;k++){
                System.out.print(Plateau[j][k] + "\t");
            }
            System.out.print("\n");
        }
    }

    public String [][] create_boat(String [][] Plateau) {
        Scanner scanner = new Scanner(System.in);
        String Coordonee;
        char lettre;
        int colonne=-1, nombre = -1;
        boolean check_Coordonnee = false;
        for(Boat.boat_Type boat : Boat.boat_Type.values()) {
            System.out.print("Creation du bateau : " + boat + "\n");
            for (int i = 0; i < boat.size; i++) {
                while(!check_Coordonnee){
                    System.out.print("Veuillez entrer une coordonee entre de 1 à 10 et de A à J: ");
                    Coordonee = scanner.nextLine();
                    if(Coordonee.length() == 2){
                        lettre = Coordonee.charAt(0);
                        colonne = lettre;
                        nombre = Coordonee.charAt(1);
                        nombre-=48;
                    }
                    else if(Coordonee.length() == 3){
                        lettre = Coordonee.charAt(0);
                        colonne = lettre;
                        String numberOnly= Coordonee.replaceAll("[^0-9]", "");
                        nombre = Integer.parseInt(numberOnly);
                        System.out.println(nombre);
                    }
                    else
                        System.out.println("Vueillez entrez une coordonnee dans le bon format");
                    if((colonne<=74 && colonne>=65) && (nombre<=10 && nombre>0))
                        check_Coordonnee=true;
                    else
                        System.out.println("Vous n'avez pas respecter les règles de coordonnees");
                }
                colonne -= 65;
                for(int j=0; j<Plateau.length;j++){
                    for(int k=0; k<Plateau.length;k++){
                        if(j == (nombre-1) && k == colonne){
                            Plateau[nombre-1][colonne] = boat.name();
                            System.out.println("Vous avez ajouter " + boat.name() + " en " + (nombre-1) + colonne);
                        }
                    }
                }
                check_Coordonnee = false;
            }
        }
        return Plateau;
    }
}
