package Esami.LudotecaINC;

import java.time.Year;

public class Prodotto {
    private String titolo;
    private String autore;
    private String casaEditrice;
    private Year annoPubblicazione;
    private int quantità;

    public Prodotto(String t, String a, String cE, Year anno, int q) {
        this.titolo = t;
        this.autore = a;
        this.casaEditrice = cE;
        this.annoPubblicazione = anno;
        this. quantità = q;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getCasaEditrice() {
        return casaEditrice;
    }

    public void setCasaEditrice(String casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public Year getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Year annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }
}
