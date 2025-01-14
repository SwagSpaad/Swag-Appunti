package Polimorfismo;

public class Main {
    public static void main(String[] args) {

        Studente studente1 = new Studente("Nicolò", "Spadoni");
        Studente studente2 = new Studente("Leonardo", "Ascenzi");
        Insegnante insegnante1 = new Insegnante("Anna", "Rossi");

        /*
         * supponiamo di voler creare un'array che identifichi la classe, quindi abbia all'interno 
         * tutti gli studenti e tutti gli insegnanti relativi a quella classe. Come facciamo?
         * 
         * classe = {studente1, insegnante1, ...};
         * 
         * ma di che tipo deve essere? 
         * 
         * Studente[] classe = {studente1, insegnante1, ...}; non va bene perché insegnante non è di tipo Studente
         * Insegnante[] classe = {studente1, insegnante1, ...}; non va bene perché studente non è oggetto Insegnante
         * 
         * Entra in gioco il polimorfismo, in quanto sia studente che insegnante estendono la classe Persona, allora 
         * sono entrambi oggetti persona
         */

         Persona[] classe = {studente1, studente2, insegnante1};

         for(Persona persona : classe) {
            System.out.print(persona.getNome() + ": ");
            persona.saluta();
         }
    }
}
