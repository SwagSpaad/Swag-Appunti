package Esami.Biblioteca;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Esami.Biblioteca.*;

public class Biblioteca {
    private ArrayList<Supporto> catalogo = new ArrayList<>();
    
    public void addSupporto(Libro libro){
        catalogo.add(libro);
    }

    public void addSupporto(DVD dvd){
        catalogo.add(dvd);
    }

    public void getCatalogo() {
        for(Supporto p : catalogo) {
            System.out.println(p.toString());
        }
    }

    public void iniziaPrestito(Supporto prodotto, LocalDate dataInizioPrestito, int numGiorniPrestito, String nomeCliente, String cognomeCliente, double costoAffitto) {
        ArrayList<Prestito> listaPrestiti = prodotto.getListaPrestiti();
        if(listaPrestiti.size() == 0 || listaPrestiti.getLast().getDataRiconsegnaEffettiva() != null) {
            Prestito p1 = new Prestito(dataInizioPrestito, numGiorniPrestito, nomeCliente, cognomeCliente, costoAffitto);
            prodotto.addPrestito(p1);
        } else {
            System.out.println("Il prodotto non è stato ancora riconsegnato!");
        }
    }

    public void terminaPrestito(Supporto prodotto, LocalDate dataRiconsegnaEffettiva) {
        Prestito prestito = prodotto.getUltimoPrestito();
        if(prestito.getDataRiconsegnaEffettiva() != null) {
            System.out.println("Il prodotto è già stato riconsegnato!");
        } else {
            prestito.setDataRiconsegnaEffettiva(dataRiconsegnaEffettiva);
            if(prestito.checkConsegna()) {
                prestito.applicaMaggiorazione();
                System.out.println("Il prodotto è stato consegnato in ritardo.\nIl nuovo costo dell'affitto è: " + prestito.getCostoAffitto());
            }
        }
    }

    public long maxGiorniPrestito(Supporto prodotto) {
        long max = 0; 
        ArrayList<Prestito> listaPrestiti = prodotto.getListaPrestiti();
        for (Prestito prestito : listaPrestiti) {
            if(prestito.checkConsegna()) {
                long giorni = prestito.getDataRiconsegnaEffettiva().toEpochDay() - prestito.getDataInizioPrestito().toEpochDay();
                if (giorni > max) {
                    max = giorni;
                } 
            }
        }
        return max;
    }

    public void checkInconsistenze(Supporto prodotto) {
        ArrayList<Prestito> listaPrestiti = prodotto.getListaPrestiti();
        ArrayList<Prestito> listaInconsistenze = new ArrayList<>();
        for (Prestito prestito : listaPrestiti) {
            for (Prestito prestito2 : listaPrestiti) {
                if (prestito == prestito2) {
                    continue;
                } else {
                    LocalDate inizioPrestito1 = prestito.getDataInizioPrestito();
                    if (inizioPrestito1.isAfter(prestito2.getDataInizioPrestito()) && inizioPrestito1.isBefore(prestito2.getDataRiconsegnaEffettiva())) {
                        listaInconsistenze.add(prestito);
                    }
                }
            }
        }

        
        if (listaInconsistenze.size() == 0) {
            System.out.println("Non ci sono inconsistenze per il seguente prodotto.");
        } else {
            System.out.println("Sono state rilevate le seguenti inconsistenze:");
            for (Prestito prestito : listaInconsistenze) {
                System.out.println(prestito);   
            }
        }
    }
}
