package Esami.Biblioteca;
import java.util.ArrayList;

public class Supporto {

    private String titolo;
    private String entePubblicante;
    private int annoDiPubblicazione;
    private ArrayList<Prestito> listaPrestiti;

    public Supporto(String titolo, String entePubblicante, int annoDiPubblicazione) {
        this.titolo = titolo;
        this.entePubblicante = entePubblicante;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.listaPrestiti = new ArrayList<>();
    }

    public void addPrestito(Prestito prestito) {
        this.listaPrestiti.add(prestito);
    }

    public Prestito getUltimoPrestito() {
        Prestito ultimoPrestito = this.listaPrestiti.getLast();
        return ultimoPrestito;
    }

    public void printListaPrestiti() {
        ArrayList<Prestito> listaPrestiti = this.listaPrestiti;
        System.out.println("Lista dei prestiti del prodotto " + this.toString());
        if(listaPrestiti.size() == 0) {
            System.out.println("Non sono stati ancora effettuati prestiti per questo prodotto");
        } else {
            for (Prestito prestito : listaPrestiti) {
                System.out.println(prestito.toString());            
            }
        }
    }

    public ArrayList<Prestito> getListaPrestiti() {
        return this.listaPrestiti;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getEntePubblicante() {
        return entePubblicante;
    }
    
    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setEntePubblicante(String entePubblicante) {
        this.entePubblicante = entePubblicante;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }


}
