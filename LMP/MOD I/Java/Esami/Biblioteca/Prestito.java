package Esami.Biblioteca;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestito {

    private LocalDate dataInizioPrestito;
    private LocalDate dataRiconsegnaPrevista;
    private LocalDate dataRiconsegnaEffettiva;
    private String nomeCliente;
    private String cognomeCliente;
    private double costoAffitto;

    private static final double PENALE = 5;

    public Prestito(LocalDate dataInizioPrestito, int numGiorniPrestito, String nomeCliente, String cognomeCliente, double costoAffitto) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRiconsegnaPrevista = this.dataInizioPrestito.plusDays(numGiorniPrestito);
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
        this.costoAffitto = costoAffitto;        
    }
    
    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public LocalDate getDataRiconsegnaPrevista() {
        return dataRiconsegnaPrevista;
    }

    public LocalDate getDataRiconsegnaEffettiva() {
        return dataRiconsegnaEffettiva;
    }

    public String getNome() {
        return nomeCliente;
    }

    public String getCognome() {
        return cognomeCliente;
    }

    public double getCostoAffitto() {
        return costoAffitto;
    }

    public void setNome(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setCognome(String cognomeCliente) {
        this.cognomeCliente = cognomeCliente;
    }

    public void setCostoAffitto(double costoAffitto) {
        this.costoAffitto = costoAffitto;
    }

    public void setDataRiconsegnaEffettiva(LocalDate dataRiconsegnaEffettiva) {
        this.dataRiconsegnaEffettiva = dataRiconsegnaEffettiva;
    }

    public boolean checkConsegna() {
        return this.dataRiconsegnaEffettiva != null;
    }

    public void applicaMaggiorazione() {
        this.costoAffitto += PENALE;
    }

    public String toString() {
        return "[DataInizio] = " + this.getDataInizioPrestito() + ", [DataConsegnaPrevista] = " + this.getDataRiconsegnaPrevista() + ", [DataConsegnaEffettiva] = " + this.getDataRiconsegnaEffettiva() + ", [Nome Cognome Cliente] = " + this.getNome() + " " + this.getCognome() + ", [CostoAffitto] = " + getCostoAffitto();
    }
}
