package fr.lernejo.navy_battle;;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public void current_Game() { 
        boolean etat_Partie = true;
        List<Boat> Liste_Bateau;
        List<int[]> taken_Coordonees = new ArrayList<>();
        Liste_Bateau = init_List_Boat(taken_Coordonees);//Création des bateaux
        Player player = new Player(Liste_Bateau);
        boolean fin_De_Partie = false;
    }

    public List<Boat> init_List_Boat( List<int[]> taken_Coordonees){
        List<Boat> list = new ArrayList<>();
        List<int[]> Liste_Coordonne = new ArrayList<>();
        for(Boat.boat_Type type : Boat.boat_Type.values()){
            Liste_Coordonne = create_boat(type,taken_Coordonees);
            Boat boat = new Boat(Liste_Coordonne, type.size);
            list.add(boat);
            //System.out.println(Arrays.toString(list.get(0).getCoordonee().get(0)));
        }
        return list;
    }

    public List<int[]> create_boat(Boat.boat_Type boat, List<int[]> taken_Coordonees) {
        System.out.print("Creation du bateau : " + boat + "\n");
        Scanner scanner = new Scanner(System.in);
        char lettre;
        int colonne=-1, nombre = -1;
        List<int[]> Liste_Coordonne = new ArrayList<>();
        int[] coordonnee = new int[2];
        boolean check_Coordonnee = false;
        for (int i = 0; i < boat.size; i++) {
            while(!check_Coordonnee){
                System.out.print("Veuillez entrer une Lettre de coordonnee entre A et J: ");
                lettre = scanner.next().charAt(0);
                colonne = lettre;
                System.out.print("Veuillez entrer un chiffre de coordonnee entre 0 et 10: ");
                nombre = scanner.nextInt();
                if((colonne<=74 && colonne>=65) && (nombre<=10 && nombre>=0))
                    check_Coordonnee=true;
            }
            coordonnee[0] = nombre;
            coordonnee[1] = colonne;
            System.out.println("Yeah +une coordonne");
            Liste_Coordonne.add(coordonnee);
            check_Coordonnee = false;
        }
        return Liste_Coordonne;
    }
}
