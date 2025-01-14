package Lezioni;
//file principale del programma

//per utilizzare gli input da terminale bisogna importare java.util.Scanner

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) { //entry point del programma
        System.out.print("Hello world!\n");

        int x; //dichiarazione variabile intera
        x = 45; //assegnazione valore alla variabile

        int y = 50; //inizializzazione (dichiarazione + assegnazione insieme)
        String parola = "albero"; //inizializzazione variabile di tipo stringa
        double z = 3.14; //inizializzazione valore con virgola

        System.out.println("Valore variabile intera X: "+x);
        System.out.println("Valore variabile stringa 'parola': "+parola);
        System.out.println("Valore variabile dobule z: "+z);


        //TIPI DI DATO
        // INTERI
        boolean typeBool = true; 
        byte typeByte = 127;
        short typeShort = -32768;
        int typeInt = 2_000_000_000; 
        long typeLong = 32_534_342_456L; //necessaria L finale 

        //VIRGOLA 
        float typeFloat = 5.3456476F; //necessaria F finale
        double typeDouble = 5.134578392154693; 

        //ALFANUMERICI
        char typeChar = 'a';
        String typeString = "stringa"; 

        /*
         * I dati si distinguono in:
         * - primitive (fornite direttamente dal linguaggio) ed iniziano con la lettera minuscola
         * - reference (vengono create a partire dalle primitive), iniziano con la maiuscola e hanno dei metodi associati
         */

        System.out.println(typeString);
        System.out.println(typeString.toUpperCase());

        //creo l'oggetto scanner
        Scanner scanner = new Scanner(System.in); //Tipo nome = new Object

        /* System.out.print("Inserisci il tuo nome: ");
        String nome = scanner.nextLine(); //nextLine() è il metodo dell'oggetto scanner che mi permette di acquisire il valore scritto in console
        System.out.print("Quanti anni hai? ");
        byte eta = scanner.nextByte(); 
        scanner.nextLine(); //necessario, perché il metodo nextByte skippa le linee al di sotto, quindi potrebbe portare errori
        System.out.print("Dove sei nato? ");
        String citta = scanner.nextLine();
        System.out.println("Ciao " + nome + "!\nHai " + eta + " anni.\nSei nato a " + citta + "."); */

        //Array
        /* int[] numeri = new int[3];
        numeri[0] = 10;
        numeri[1] = 20;
        numeri[2] = 30; */
        
        int[] numeri =  {10, 20, 30};
        
        System.out.println("Array di lunghezza: " + numeri.length);

        System.out.println(numeri[1]);

        numeri[1] = 100;

        System.out.println(numeri[1]);

        //Array 2d
        /* String[][] matrix = new String[3][3]; //matrice di stringhe 3x3

        matrix[0][0] = "A";       
        matrix[0][1] = "B";
        matrix[0][2] = "C";

        matrix[1][0] = "D";       
        matrix[1][1] = "E";
        matrix[1][2] = "F";

        matrix[2][0] = "G";       
        matrix[2][1] = "H";
        matrix[2][2] = "I"; */

        String[][] matrix = {
            {"A", "B", "C"},
            {"D", "E", "F"},
            {"G", "H", "I"}
        };
        
        //iterazione matrice
        for(int riga = 0; riga < matrix.length; riga++){
            for(int colonna = 0; colonna < matrix[riga].length; colonna++){
                System.out.print(matrix[riga][colonna] + " ");
            }
            System.out.println();
        }

        //iterazione con foreach
        for(String[] riga: matrix){
            for(String colonna: riga){
                System.out.print(colonna + " ");
            }
            System.out.println();
        }

        //metodi delle stringhe
        String testMetodi = "FrancescoTotti       ";

        boolean result = testMetodi.equals("Francescototti");
        System.out.println(result);
        result = testMetodi.equalsIgnoreCase("FrAnCeScOToTTi");
        System.out.println(result);
        System.out.println("Output metodo length: " + testMetodi.length());
        System.out.println("Output metodo charAt: " + testMetodi.charAt(testMetodi.length() - 1));
        System.out.println("Output metodo indexOf: " + testMetodi.indexOf("T"));
        System.out.println("Output metodo toUpperCase: " + testMetodi.toUpperCase());
        System.out.println("Output metodo toLowerCase: " + testMetodi.toLowerCase() + "!");
        System.out.println("Output metodo trim: " + testMetodi.trim() + "!"); //rimuove eventuali spazi dopo la fine dei caratteri
        System.out.println("Output metodo replace: " + testMetodi.replace("o", "u"));

        //Array list
        /*
         * le array list non accettano valori primitivi, per questo 
         * si utilizzano le Wrapper Classes per creare liste di 
         * int, float, bool, double e char 
         */
        ArrayList<String> list = new ArrayList<String>();
        list.add("Luca");
        list.add("Marco");
        list.add("Anna");

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        list.set(2, "Paola"); //modifica l'indice 2 della lista con l'elemento specificato

        list.remove(0); //elimina l'elemento 0

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        //ArrayList 2D

        ArrayList<String> list1 = new ArrayList<String>();

        list1.add("Francesco");
        list1.add("Daniele");
        list1.add("Lorenzo");

        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("10");
        list2.add("16");
        list2.add("7");

        ArrayList<ArrayList<String>> matrixList = new ArrayList();
        matrixList.add(list1);
        matrixList.add(list2);

        for(int i = 0; i < matrixList.size(); i++){
            for(int j = 0; j < matrixList.get(i).size(); j++){
                System.out.print(matrixList.get(i).get(j) + " ");
            }
            System.out.println();
        }

        primoMetodo();
        metodoConParametri(3);

        int sum = somma(2, 5);
        System.out.println("La somma è: " + sum);


        //creazione oggetto personale
        /* Persona persona1 = new Persona();

        System.out.println(persona1.nome);
        persona1.saluta();
        persona1.cammina(); */
        Persona persona1 = new Persona("Nicolò", "Spadoni"/* , 22, "Marino" */);
        Persona persona2 = new Persona("Leonardo", "Ascenzi"/* , 22, "Roma"*/);

        Pizza margherita = new Pizza("normale", "pomodoro", "mozzarella");
        Pizza marinaraSG = new Pizza("senza glutine", "pomodoro");
        Pizza wurstelPatatine = new Pizza("normale", "pomodoro", "mozzarella", "wurstel", "patatine");
        Pizza crostinoInt = new Pizza("integrale", "bianca", "mozzarella", "prosciutto cotto");

        System.out.println(margherita.toString());

        //Array di oggetti
        Persona[] persone = new Persona[3]; 

        Persona persona3 = new Persona("Franco", "Salvucci"/* , 23, "Roma" */);

        persone[0] = persona1;
        persone[1] = persona2;
        persone[2] = persona3;

        System.out.println(persone[1]);
        //System.out.println(persone[0].nome); //da errore in quanto l'attributo nome nella definizione della classe persona è private

        persone[0].salutaPersona(persone[1]);

        //final e static
        final int CONST = 10; //la variabile non può cambiare una volta assegnata
        //static si utilizza nelle variabili e nei metodi per condividere la variabile

        System.out.println(Persona.numeroPersone); //accedo all'attributo direttamente dall'oggetto e non da un istanza di questo

        Studente studente1 = new Studente("Davide", "Conti", "1D");
        studente1.salutaPersona(persona3); //l'oggetto studente non ha saluta come metodo, ma lo eredita dalla classe Persona

        Insegnante insegnante1 = new Insegnante("Anna", "Rossi", "Italiano");

        studente1.salutaProf(insegnante1);
        studente1.studia();

        insegnante1.insegna();
        insegnante1.saluta();

        Automobile auto1 = new Automobile();
        auto1.muovi();
        auto1.frena();

        //per accedere al nome di una persona utilizziamo la funzione getters definita nella classe persona
        System.out.println(persona3.getNome());
        System.out.println(persona3.getCognome());

        //modifico il cognome di una persona con la funzione setters definita
        //persona3.cognome = "Sborucci"; //questo da errore in quanto gli attributi sono private
        persona3.setCognome("Sborucci");
        System.out.println(persona3.getNome());
        System.out.println(persona3.getCognome());

        System.out.println("Persona1: " + persona1);
        System.out.println("Persona2: " + persona2);
        System.out.println();
        
        System.out.println("Effettuo la copia di persona2 in persona1...");
        //voglio eseguire una copia di persona2 in persona1
        //persona1 = persona2; istintivamente potrei fare cosi, ma in realtà questo va solamente a puntare alla stessa cella di memoria,
        //                     quindi effettuando una modifica in persona1 risulterebbe modificata anche persona2
        persona1.copy(persona2);
        System.out.println("Persona1: " + persona1);
        System.out.println("Persona2: " + persona2);
        System.out.println("Cambio il nome di persona1...");
        persona1.setNome("Pippo");
        System.out.println("Persona1: " + persona1);
        System.out.println("Persona2: " + persona2);
        
        //col nuovo metodo costruttore aggiunto posso effettuare la copia direttamente dalla dichiarazione
        Persona persona4 = new Persona(persona1);

        System.out.println("Persona4: " + persona4);

        
    }
    //metodi
    static void primoMetodo() {
        System.out.println("Sono il tuo primo metodo");
    }

    static void metodoConParametri(int param) {
        System.out.println("Sono il metodo con parametro " + param);
    }

    //overloaded methods
    //metodi con stesso nome ma che variano o nel numero di parametri o nel tipo
    static int somma(int a, int b) {
        int result = a + b;
        //System.out.println("La somma tra " + a + " e " + b + " è " + result); //manda solamente a schermo l'addizione, non ritorna il valore
        return result;
    }

    static int somma(int a, int b, int c) {
        int result = a + b + c;
        //System.out.println("La somma tra " + a + " e " + b + " è " + result); //manda solamente a schermo l'addizione, non ritorna il valore
        return result;
    } 
}