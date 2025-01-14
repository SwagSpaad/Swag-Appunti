package Polimorfismo;

public class Persona {
    
    private String nome;
    private String cognome;

    Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    Persona(Persona persona) {
        this.copy(persona);
    }

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

    public void saluta() {
        System.out.println("Ciao!");
    }
}
