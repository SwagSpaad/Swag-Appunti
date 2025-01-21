package Esami.LudotecaINC;

import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;

public class Ludoteca {
    ArrayList<Gioco> inventarioGiochi = new ArrayList<>();
    ArrayList<Libro> inventarioLibri = new ArrayList<>();

    ArrayList<Prestito> prestitiAttivi = new ArrayList<>();
    

    public void addLibro(String titolo, String autore, String casaEd, Year anno, int quantità, int numPag) {
        inventarioLibri.add(new Libro(titolo, autore, casaEd, anno, quantità, numPag));
    }

    public void addGioco(String titolo, String autore, String casaEd, Year anno, int quantità, int durata) {
        inventarioGiochi.add(new Gioco(titolo, autore, casaEd, anno, quantità, durata));
    }

    public void startPrestito(Gioco gioco, LocalTime ora, String nome, String cognome) {
        if (inventarioGiochi.contains(gioco)) {
            if (gioco.getQuantità() > 0) {
                //gioco disponibile
                prestitiAttivi.add(new Prestito(gioco, ora, nome, cognome));
            } else {
                //gioco non disponibile
                int min = 0;
                for (Prestito p : prestitiAttivi) {
                    if (p.getGioco() == gioco) {
                        min = p.getStimaFine().toSecondOfDay() - p.getInizio().toSecondOfDay();
                        break;
                    }
                }
                for (Prestito p : prestitiAttivi) {
                    if (p.getGioco() == gioco) {
                        min = Math.min(min, p.getStimaFine().toSecondOfDay() - p.getInizio().toSecondOfDay());
                    }
                }
                min = min / 60;
                System.out.println("Non è dispoibile una copia del gioco. Tempo stimato: " + min);
            }
        }
    }



}
