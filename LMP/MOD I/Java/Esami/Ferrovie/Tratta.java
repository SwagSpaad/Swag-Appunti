package Esami.Ferrovie;

import java.util.ArrayList;

public class Tratta {
    String partenza;
    String arrivo;
    int distanza;
    ArrayList<TipoTratta> tipoTratta; 
    TipoTreno tipoTreno;

    Tratta(String p, String a, int d, ArrayList<TipoTratta> tipoTratta, TipoTreno tipoTreno) {
        this.partenza = p;
        this.arrivo = a;
        this.distanza = d;
        this.tipoTratta = tipoTratta;
        this.tipoTreno = tipoTreno;
    }
    
    public String toString() {
        return partenza + "-" + arrivo;
    } 
}
