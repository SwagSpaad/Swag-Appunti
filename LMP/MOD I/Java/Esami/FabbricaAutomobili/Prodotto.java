package Esami.FabbricaAutomobili;

import java.util.ArrayList;

public class Prodotto {
    String identificativo;
    String etichetta;
    ArrayList<Componente> listaComponenti;
    double costoDiProduzione;
    double tempoStimatoOrdine;
    private double tempoRealizzazione;
    double prezzoAcquirenti;

    Prodotto(String id, String et, ArrayList listaComp, double costoProd, double tempoOrdine, double tempoReal, double prAcq) {
        this.identificativo = id;
        this.etichetta = et;
        this.listaComponenti = listaComp;
        this.costoDiProduzione = costoProd;
        this.tempoStimatoOrdine = tempoOrdine;
        this.tempoRealizzazione = tempoReal;
        this.prezzoAcquirenti = prAcq;
    }

    public double getTempoRealizzazione() {
        return tempoRealizzazione;
    }

    public String toString() {
        return " " + this.etichetta + " " + this.tempoStimatoOrdine + " " + this.tempoRealizzazione + " " + this.costoDiProduzione + " " + this.prezzoAcquirenti; 
    }
}
