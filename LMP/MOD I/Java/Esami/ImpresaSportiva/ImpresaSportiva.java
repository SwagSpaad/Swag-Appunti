package Esami.ImpresaSportiva;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import Esami.ImpresaSportiva.*;

public class ImpresaSportiva {
    private ArrayList<Sportivo> organicoImpresa;
    private int numRetiBonus; 

    ImpresaSportiva(int numRetiBonus) {
        this.organicoImpresa = new ArrayList<>();
        this.numRetiBonus = numRetiBonus; 
    }

    public void setNumRetiBonus(int numReti) {
        this.numRetiBonus = numReti;
    }

    public int getNumRetiBonus() {
        return numRetiBonus;
    }

    public void addSportivo(Sportivo sportivo) {
        organicoImpresa.add(sportivo);
    }

    public void addSportivo(String nome, String cognome, LocalDate dataAss, TipoSportivo tipo, int livelloStipendiale) {
        organicoImpresa.add(new Sportivo(nome, cognome, dataAss, tipo, livelloStipendiale));
    }

    public void printOrganicoImpresa() {
        for (Sportivo s : organicoImpresa) {
            System.out.println(s.toString());
            
        }
    }

    public ArrayList<Sportivo> getOrganico() {
        return organicoImpresa;
    }

    public void assegnaBonus() {
        HashMap<Sportivo, Integer> giocatoriPremiati = new HashMap<>();
        for (Sportivo s : organicoImpresa) {
            if (s.getTipoSportivo() == TipoSportivo.GIOCATORE && s.getLivelloStipendiale() >= 3) {
                Integer count = 0;
                for (Integer gol : s.getGolHashMap().values()) {
                    if(gol >= numRetiBonus) {
                        count++;
                    }
                }
                if (count > 0) {
                    giocatoriPremiati.put(s, count);
                }
            } else {
                continue;
            }
        }
        System.out.println("Giocatori premiati: ");
        for (Sportivo sportivo : giocatoriPremiati.keySet()) {
            System.out.println("" + sportivo.getNome() + " " + sportivo.getCognome() + " ha segnato almeno " + numRetiBonus + " reti in " + giocatoriPremiati.get(sportivo) + " mesi dell'anno");            
        }
    }

    public void assuntiPerMese() {
        //appoggio è una copia di organicoImpresa
        ArrayList<Sportivo> appoggio = new ArrayList<>();
        //stessoMese contiene gli oggetti Sportivo che sono stati assunti nello stesso mese
        ArrayList<Sportivo> stessoMese = new ArrayList<>();
        //daRimuovere contiene gli oggetti Sportivo da rimuovere da appoggio per evitare ridondanze
        ArrayList<Sportivo> daRimuovere = new ArrayList<>();
        
        for (Sportivo sportivo : organicoImpresa) {
            appoggio.add(sportivo);
        }

        for (Sportivo s : organicoImpresa) {
            Month meseS = s.getDataAssunzione().getMonth();
            int annoS = s.getDataAssunzione().getYear();
            stessoMese.add(s);
            appoggio.remove(s);
            for (Sportivo s1 : appoggio) {
                Month meseS1 = s1.getDataAssunzione().getMonth();
                int annoS1 = s1.getDataAssunzione().getYear();
                if (s.getTipoSportivo() == s1.getTipoSportivo() && meseS == meseS1 && annoS == annoS1) {
                    stessoMese.add(s1);
                    daRimuovere.add(s1);
                }
            }
            if (daRimuovere.size() > 0) {
                for (Sportivo rim : daRimuovere) {
                    appoggio.remove(rim);
                }
            }
            if (stessoMese.size() > 1) {
                System.out.println("" + stessoMese.size() + " sportivi di tipo " + s.getTipoSportivo() + " assunti a " + s.getDataAssunzione().getMonth() + " " + s.getDataAssunzione().getYear());
            }
            //pulisci stessoMese per trovare nuovi sportivi assunti nello stesso mese
            stessoMese.clear();
        }
    }




}
