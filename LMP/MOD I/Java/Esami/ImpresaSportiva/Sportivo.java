package Esami.ImpresaSportiva;

import Esami.ImpresaSportiva.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

public class Sportivo {
    private String nome;
    private String cognome;
    private HashMap<Month, Integer> golPerMese = new HashMap<>();
    private LocalDate dataAssunzione;
    private TipoSportivo tipo;
    static int numProgressivo = 0;
    private String numIscrizione;
    private int livelloStipendiale;

    Sportivo(String nome, String cognome, LocalDate dataAssunzione, TipoSportivo tipo, int livelloStipendiale) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataAssunzione = dataAssunzione;
        this.tipo = tipo;
        numProgressivo++;
        this.numIscrizione = "TEAM_< " + numProgressivo + " >";
        if (livelloStipendiale > 5 || livelloStipendiale < 1) {
            throw new Error("Il livello stipendiale deve essere compreso tra 1 e 5");
        } else {
            this.livelloStipendiale = livelloStipendiale;
        }
    }

    public void setGolPerMese(Month mese, Integer retiSegnate) {
        if (this.getTipoSportivo() == TipoSportivo.GIOCATORE) {
            this.golPerMese.put(mese, retiSegnate);
        } else {
            throw new Error("La tipologia di sportivo non segna gol!");
        }
    }

    public TipoSportivo getTipoSportivo() {
        return tipo;
    }

    public int getLivelloStipendiale() {
        return livelloStipendiale;
    }

    public HashMap<Month, Integer> getGolHashMap() {
        return golPerMese;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String toString() {
        if (this.tipo == TipoSportivo.GIOCATORE) {
            return "" + nome + " " + cognome + " " + dataAssunzione + " " + tipo + " " + livelloStipendiale + " " + golPerMese;
        } else {
            return "" + nome + " " + cognome + " " + dataAssunzione + " " + tipo + " " + livelloStipendiale;
        }
    }

    public void setLivelloStipendiale(int newLivello) {
        this.livelloStipendiale = newLivello;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }
}
