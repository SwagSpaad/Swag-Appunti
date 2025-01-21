package Esami.LudotecaINC;

import java.time.LocalTime;

public class Prestito {
    private Gioco gioco;
    private Libro libro;
    private LocalTime inizio;
    private LocalTime stimaFine;
    private String nome;
    private String cognome;
    private LocalTime fine = null;

    public Prestito(Gioco g, LocalTime inizio, String n, String c) {
        this.gioco = g;
        g.setQuantità(g.getQuantità() - 1);
        this.inizio = inizio;
        this.stimaFine = inizio.plusMinutes(g.getDurataMediaPartita());
        this.nome = n;
        this.cognome = c;
    }

    public Prestito(Libro l, LocalTime inizio, String n, String c) {
        this.libro = l;
        l.setQuantità(l.getQuantità() - 1);
        this.inizio = inizio;
        this.nome = n;
        this.cognome = c;
    }

    public Gioco getGioco() {
        return gioco;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalTime getStimaFine() {
        return stimaFine;
    }

    public LocalTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalTime inizio) {
        this.inizio = inizio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalTime getFine() {
        return fine;
    }

    public void setFine(LocalTime fine) {
        this.fine = fine;
    }
}
