package Esami.Biblioteca;

public class DVD extends Supporto {
    
    private int durata; //in minuti

    public DVD(String titolo, String entePubblicante, int annoDiPubblicazione, int durata) {
        super(titolo, entePubblicante, annoDiPubblicazione);
        this.durata = durata;
    }

    public String toString() {
        return "DVD: [Titolo] = " + this.getTitolo() + ", [Ente Pubblicante] = " + this.getEntePubblicante() + ", [Anno] = " + this.getAnnoDiPubblicazione() + ", [Durata] = " + this.durata + "";
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}
