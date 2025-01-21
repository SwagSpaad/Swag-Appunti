package Esami.LudotecaINC;

import java.time.Year;

public class Gioco extends Prodotto {
    private int durataMediaPartita; //in minuti

    public Gioco(String t, String a, String cE, Year anno, int quantità, int dur) {
        super(t, a, cE, anno, quantità);
        this.durataMediaPartita = dur;
    }

    public int getDurataMediaPartita() {
        return durataMediaPartita;
    }

    public void setDurataMediaPartita(int dur) {
        this.durataMediaPartita = dur;
    }
}
