package fr.lernejo.navy_battle;

import java.util.List;

public class Boat {
    private final List<int[]> Liste_Coordonne;
    private final int size;

    public Boat(List<int[]> liste_coordonne, int size) {
        Liste_Coordonne = liste_coordonne;
        this.size = size;
    }
    public List<int[]> getCoordonee() {
        return this.Liste_Coordonne;
    }


    public enum boat_Type{
        porte_avion(5),
        croiseur(4),
        contre_torpilleur1(3),
        contre_torpilleur2(3),
        torpilleur(2);

        public final int size;
        boat_Type(int size) {
            this.size = size;
        }
    }
}
