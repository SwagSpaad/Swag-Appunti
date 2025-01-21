package Esami.LudotecaINC;

import java.time.Year;

public class Libro extends Prodotto {
    private int numeroPagine;

    public Libro(String t, String a, String cE, Year anno, int quantità, int nP) {
        super(t, a, cE, anno, quantità);
        this.numeroPagine = nP;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int nP) {
        this.numeroPagine = nP;
    }
}
