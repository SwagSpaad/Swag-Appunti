package Esami.DocentiLMP;

public class Studente {
    String nome;
    String cognome;
    String matricola;

    Studente(String n, String c, String m) {
        this.nome = n;
        this.cognome = c;
        this.matricola = m;
    }

    public String toString() {
        return "" + matricola + " " + nome + " " + cognome;
    }
}
