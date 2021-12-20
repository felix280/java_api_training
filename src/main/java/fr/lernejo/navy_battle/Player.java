package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final List<Boat> Liste_Bateau;

    public Player(List<Boat> liste_bateau) {
        Liste_Bateau = liste_bateau;
    }

    public int[] tirer(){
        System.out.print("C'est ton tour");
        char lettre;
        int colonne=-1, nombre = -1;
        int[] coordonnee = new int[2];
        boolean check_Coordonnee = false;
        Scanner scanner = new Scanner(System.in);

        while(!check_Coordonnee){
            System.out.print("Veuillez entrer une Lettre de coordonnee entre A et J: ");
            lettre = scanner.next().charAt(0);
            colonne = lettre;
            nombre = scanner.next().charAt(1);

            if((colonne<=74 && colonne>=65) && (nombre<=10 && nombre>=0))
                check_Coordonnee=true;
        }
        coordonnee[0] = nombre;
        coordonnee[1] = colonne;
        System.out.print("Vous tirez sur la case : " + coordonnee[1] + coordonnee[0]);
        return coordonnee;

    }
}
