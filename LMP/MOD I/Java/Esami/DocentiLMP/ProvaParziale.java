package Esami.DocentiLMP;

public class ProvaParziale {
    private Argomento arg;
    private int voto;
    private Studente studente;
    private boolean promosso;
    private int coeffMedia = 1;

    ProvaParziale(Argomento a, Studente s, int v) {
        this.arg = a;
        this.studente = s;
        if (v >= 0 && v <= 30) {
            this.voto = v;
            if (v >= 18 && v <= 30) {
                this.promosso = true;
            } else {
                this.promosso = false;
            }
        } else {
            throw new Error("Il voto non è ammesso!");
        }
    }

    public void setCoefficiente(int coeff) {
        this.coeffMedia = coeff;
    }

    public String toString() {
        return "" + arg + " " + voto + " " + coeffMedia + " " + promosso;
    }

    public Argomento getArgomento() {
        return arg;
    }

    public int getVoto() {
        return voto;
    }

    public Studente getStudente() {
        return studente;
    }

    public boolean getPromosso() {
        return promosso;
    }

    public int getCoefficiente() {
        return coeffMedia;
    }
}
