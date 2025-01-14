package Esami.DocentiLMP;

import java.time.LocalDate;

public class Esame {
    private ProvaParziale[] prove = new ProvaParziale[5];
    private Studente stud;
    private double votoFinale;
    private boolean esito;

    Esame(ProvaParziale[] prove, Studente s) {
        this.prove = prove;
        this.stud = s;
        double v = this.getVotoFinale(prove); 
        if (v >= 0 && v <= 30) {
            this.votoFinale = v;
            if (v >= 18 && v <= 30) {
                this.esito = true;
            } else {
                this.esito = false;
            }
        } else {
            throw new Error("Il voto non è ammesso!");
        }
    }

    public double getVotoFinale(ProvaParziale[] prove) {
        double votoFinale;
        int scrittoJava = 0;
        int coeffScrittoJava = 0;
        int scrittoProlog = 0;
        int coeffScrittoProlog = 0;
        int scrittoPython = 0;
        int coeffScrittoPython = 0;
        int oraleOOP = 0;
        int coeffOraleOOP = 0;
        int oralePLF = 0;
        int coeffOralePLF = 0;

        //ottengo i voti e i coefficienti delle varie prove
        for (int i = 0; i < prove.length; i++) {
            switch (prove[i].getArgomento()) {
                case S_JAVA:
                    scrittoJava = prove[i].getVoto();
                    coeffScrittoJava = prove[i].getCoefficiente();
                    break;
                case S_PROLOG:
                    scrittoProlog = prove[i].getVoto();
                    coeffScrittoProlog = prove[i].getCoefficiente();
                    break;
                case S_PYTHON:
                    scrittoPython = prove[i].getVoto();
                    coeffScrittoPython = prove[i].getCoefficiente();
                    break;
                case O_OOP:
                    oraleOOP = prove[i].getVoto();
                    coeffOraleOOP = prove[i].getCoefficiente();
                    break;
                case O_PLF:
                    oralePLF = prove[i].getVoto();
                    coeffOralePLF = prove[i].getCoefficiente();
                    break;
                default:
                    break;
            }
        }

        double mediaPesataOOP = ((scrittoJava * coeffScrittoJava) + (oraleOOP * coeffOraleOOP)) / (coeffScrittoJava + coeffOraleOOP);
        double mediaPesataPLF = ((scrittoProlog * coeffScrittoProlog) + (scrittoPython * coeffScrittoPython) + (oralePLF * coeffOralePLF)) / (coeffScrittoProlog + coeffScrittoPython + coeffOralePLF);

        votoFinale = (mediaPesataOOP + mediaPesataPLF) / 2;
        return votoFinale;
    }

    public String toString() {
        for (int i = 0; i < prove.length; i++) {
            System.out.println(prove[i]);
        }
        return "" + stud + " " + votoFinale + " " + esito;
    }

    public void aggiungiBonus(int bonus) {
        if (bonus < 0 || bonus > 2) {
            System.out.println("Il valore del bonus non è valido");
        } else {
            this.votoFinale = votoFinale + bonus;
        }
    }
    
    public Studente getStudente() {
        return stud;
    }

    public double getVotoFinale() {
        return votoFinale;
    }

    public String getEsito() {
        if(esito == true) {
            return "Promosso";
        } else {
            return "Bocciato";
        }
    }
}
