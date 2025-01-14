package Polimorfismo;

public class Insegnante extends Persona {

    Insegnante(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public void saluta() {
        System.out.println("Buongiorno ragazzi!");
    }
}
