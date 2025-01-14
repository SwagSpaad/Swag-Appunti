package Esami.Ferrovie;

import java.util.ArrayList;

public class Biglietto {
    String partenza;
    String arrivo;
    ArrayList<Tratta> sequenzaTratta;
    TipoTreno tipoTreno;
    TipoTratta tipoTratta;
    double prezzoBiglietto;

    Biglietto(String p, String a, ArrayList<Tratta> seq, TipoTreno tipoTreno, TipoTratta tipoTratta, double pr) {
        this.partenza = p;
        this.arrivo = a;
        this.sequenzaTratta = seq;
        this.tipoTreno = tipoTreno;
        this.tipoTratta = tipoTratta;
        this.prezzoBiglietto = pr;
    }

    public String toString() {
        return "" + partenza + " " + arrivo + " " + sequenzaTratta.toString() + " " + tipoTreno + " " + tipoTratta + " " + prezzoBiglietto; 
    }
}
