package Esami.Ferrovie;

import java.util.ArrayList;

public class Ferrovia {
    double fattoreAltaVel;
    ArrayList<Tratta> tratteDisp = new ArrayList<>();

    Ferrovia(double fattoreAltaVel) {
        this.fattoreAltaVel = fattoreAltaVel;
    }

    public void addTratta(Tratta tratta) {
        tratteDisp.add(tratta);
    }

}
