package Esami.DocentiLMP;

import java.time.LocalDate;
import java.util.ArrayList;

public class Appello {
    ArrayList<Esame> esami = new ArrayList<>();
    LocalDate data;
    int numeroPart;
    double percentualePromossi;

    Appello(ArrayList<Esame> esami, LocalDate data) {
        this.esami = esami;
        this.data = data;
        this.numeroPart = esami.size();
        double perProm = calcolaPercentualePromossi();
        this.percentualePromossi = perProm;
    }

    public double calcolaPercentualePromossi() { 
        double percentuale;
        double numPromossi = 0;
        for (Esame esame : esami) {
            if (esame.getEsito() == "Promosso") {
                numPromossi += 1;
            }
        }
        percentuale = (numPromossi / numeroPart) * 100;
        return percentuale;
    }

    public double getPercentualePromossi() {
        return percentualePromossi;
    }

    public void getEsame(String matricola) {
        int esameTrovato = 0;
        for (Esame esame : esami) {
            if (esame.getStudente().matricola == matricola) {
                System.out.println(esame);
                esameTrovato++;
                break;
            }
        }
        if (esameTrovato == 0) {
            System.out.println("Lo studente non ha svolto l'esame in questo appello.");
        }
    }
}
