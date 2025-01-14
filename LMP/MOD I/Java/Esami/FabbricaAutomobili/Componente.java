package Esami.FabbricaAutomobili;

public class Componente {
    String nome;
    String paeseDiProvenienza;
    private double tempoDiOrdinazione;
    private double costo;

    Componente(String nome, String paeseProv, double tempoOrd, double costo) {
        this.nome = nome;
        this.paeseDiProvenienza = paeseProv;
        this.tempoDiOrdinazione = tempoOrd;
        this.costo = costo;
    }

    public double getCostoComp() {
        return costo;
    }

    public double getTempoOrdinazione() {
        return tempoDiOrdinazione;
    }
}
