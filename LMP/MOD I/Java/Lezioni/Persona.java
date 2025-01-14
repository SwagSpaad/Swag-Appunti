package Lezioni;
public class Persona {

    /*
     * In questo modo, ogni volta che creiamo un oggetto Persona
     * avrà i dati nome, cognome, età e cittaDiNascita uguale per ogni oggetto
     */
    /* String nome = "Nicolò";
    String cognome = "Spadoni";
    int eta = 22;
    String cittaDiNascita = "Roma"; */

    //invece di specificare il valore dei dati, specifico solamente 
    //il tipo di dati che caratterizzano l'oggetto

    private String nome;
    private String cognome;
    int eta;
    String cittaDiNascita;
    static int numeroPersone; //conta quante persone vengono create e la variabile viene condivisa in tutte le persone che vengono create

    //Costruttore
    Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        /* this.eta = eta;
        this.cittaDiNascita = cittaDiNascita;
        numeroPersone++; //alla creazione di una nuova persona, il valore incrementa di 1 rispetto all'ultimo valore avuto */
    }

    //Costruttore overloaded per permettere la copia direttamente dalla dichiarazione
    Persona(Persona persona) {
        this.copy(persona);
    }

    public String toString(){
        String stringa = this.nome + "\n" + this.cognome; //+ "\n" + this.eta + "\n" + this.cittaDiNascita;
        return stringa;
    }

    //metodi

    void saluta() {
        System.out.println(this.nome + ": \"Ciao!\"");
    }

    void salutaPersona(Persona personaDaSalutare) {
        System.out.println(this.nome + ": \"Ciao " + personaDaSalutare.nome + "!\"");
    }

    void cammina() {
        System.out.println("Sto camminando...");
    }


    // getters per ottenere il nome della Persona in quanto attributi di tipo private
    public String getNome() {
        return nome;   
    }

    public String getCognome() {
        return cognome;   
    }

    //setters per la modifica del nome/cognome della persona 
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void copy(Persona persona) {
        //metodo per effettuare la copia di un oggetto persona all'interno di un altro oggetto persona
        this.setNome(persona.getNome());
        this.setCognome(persona.getCognome());
    }

}
