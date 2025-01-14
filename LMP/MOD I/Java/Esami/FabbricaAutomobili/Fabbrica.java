package Esami.FabbricaAutomobili;

import java.util.ArrayList;
import java.util.HashMap;
import Esami.FabbricaAutomobili.*;
import java.math.*;

public class Fabbrica {
    double costoGiornalieroFabbricazione;
    double fattoreGuadagno;
    HashMap<String, Prodotto> catalogoProdotti = new HashMap<>();

    Fabbrica(double costoGiorn, double fattoreGuad) {
        this.costoGiornalieroFabbricazione = costoGiorn;
        this.fattoreGuadagno = fattoreGuad;
    }
    
    public void addProdotto(String id, String et, ArrayList<Componente> listaComp, double tempoRealizz) {
        double costoProd = getCostoProduzione(listaComp, tempoRealizz);
        double tempoOrdine = getMaxTempoOrdine(listaComp);
        double prAcq = getPrezzoAcquisto(costoProd, this.fattoreGuadagno);

        catalogoProdotti.put(id, new Prodotto(id, et, listaComp, costoProd, tempoOrdine, tempoRealizz, prAcq));
    }

    public double getCostoProduzione(ArrayList<Componente> listaComp, double tempoReal) {
        double costoProd = 0;
        for (Componente comp : listaComp) {
            costoProd += comp.getCostoComp();
        }
        costoProd += tempoReal * this.costoGiornalieroFabbricazione;
        return costoProd;
    }

    public double getMaxTempoOrdine(ArrayList<Componente> listaComp) {
        double max = 0;
        for (Componente comp : listaComp) {
            max = Math.max(max, comp.getTempoOrdinazione());
        }
        return max;
    }

    public double getPrezzoAcquisto(double costoProd, double fattoreGuad) {
        double prAcq;
        prAcq = costoProd + fattoreGuad;
        return prAcq;
    }

    public double getRankingProdotto(String id) {
        Prodotto prod = catalogoProdotti.get(id);
        ArrayList<Componente> listaComp = prod.listaComponenti;
        double tempoOrdineMax = getMaxTempoOrdine(listaComp);
        double ranking = this.costoGiornalieroFabbricazione / (prod.getTempoRealizzazione() + tempoOrdineMax);
        return ranking;
    }

    public String getCatalogoProd() {
        return "Catalogo Prodotti:\n" + this.catalogoProdotti;
    }

    public void setCostoGiornaliero(double newCosto) {
        this.costoGiornalieroFabbricazione = newCosto;
    }
    
    public void setFattoreGuad(double newFattore) {
        this.fattoreGuadagno = newFattore;
    }

}
