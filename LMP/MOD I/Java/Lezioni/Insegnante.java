package Lezioni;
public class Insegnante extends Persona {

    String materia;
    /* String[] classi = {"1A", "2A", "1D"}; */

    Insegnante(String nome, String cognome, String materia) {
        super(nome, cognome);
        this.materia = materia;
    }

    void insegna() {
        System.out.println(this.getNome() + ":\"Sto insengando...\"");
    }

    @Override
    void saluta() {
        System.out.println(this.getNome() + ":\"Buongiorno ragazzi!\"");
    }
}
