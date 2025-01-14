package Lezioni;
public class Studente extends Persona{ //Studente eredita il costruttore per nome e cognome da Persona

    //rispetto a Persona, Studente ha gli attributi classe e voti in più
    String classe;
    /* int[] voti = {8, 10, 7}; */
    
    Studente(String nome, String cognome, String classe){
        super(nome, cognome);
        this.classe = classe;
    }

    //metodo specifico dell'oggetto Studente
    void studia() {
        System.out.println(this.getNome() + ":\"Sto studiando...\"");
    }

    @Override
    void saluta() {
        System.out.println(this.getNome() + ":\"Buongiorno!\"");
    }

    //questo metodo sovrascrive il metodo saluta ereditato da persona
    void salutaProf(Insegnante insegnanteDaSalutare) {
        System.out.println(this.getNome() + ":\"Salve prof. " + insegnanteDaSalutare.getCognome() + "!\"");
    }
}
