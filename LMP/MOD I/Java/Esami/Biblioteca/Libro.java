package Esami.Biblioteca;

public class Libro extends Supporto {

    private int numPagine;

    public Libro(String titolo, String entePubblicante, int annoDiPubblicazione, int numPagine) {
        super(titolo, entePubblicante, annoDiPubblicazione);
        this.numPagine = numPagine;
    }

    public String toString() {
        return "Libro: [Titolo] = " + this.getTitolo() + ", [Ente Pubblicante] = " + this.getEntePubblicante() + ", [Anno] = " + this.getAnnoDiPubblicazione() + ", [Numero Pagine] = " + this.numPagine + "";
    }

    public int getNumPagine() {
        return numPagine;
    }

    public void setNumPagine(int numPagine) {
        this.numPagine = numPagine;
    }

}
