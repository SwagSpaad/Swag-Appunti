package Esami.Ferrovie;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        ArrayList<TipoTratta> tipo1 = new ArrayList<>();
        tipo1.add(TipoTratta.NAZ_BLU);
        tipo1.add(TipoTratta.NAZ_GRIGIO);
        Tratta t1 = new Tratta("Roma", "Firenze", 200, tipo1, TipoTreno.ENTRAMBI);

        ArrayList<TipoTratta> tipo2 = new ArrayList<>();
        tipo2.add(TipoTratta.REG_BLU);
        tipo2.add(TipoTratta.NAZ_BLU);
        Tratta t2 = new Tratta("Firenze", "Bologna", 60, tipo2, TipoTreno.NORMALE);

        
    }
}
