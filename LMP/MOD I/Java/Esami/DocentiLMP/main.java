package Esami.DocentiLMP;

import java.time.LocalDate;
import java.util.ArrayList;

import Esami.DocentiLMP.*;;

public class main {
    public static void main(String[] args) {
        Studente s1 = new Studente("Mario", "Rossi", "123456");
        ProvaParziale scrittoJava = new ProvaParziale(Argomento.S_JAVA, s1, 26);
        scrittoJava.setCoefficiente(3);
        ProvaParziale scrittoProlog = new ProvaParziale(Argomento.S_PROLOG, s1, 19);
        ProvaParziale scrittoPython = new ProvaParziale(Argomento.S_PYTHON, s1, 29);
        ProvaParziale oraleOOP = new ProvaParziale(Argomento.O_OOP, s1, 19);
        ProvaParziale oralePLF = new ProvaParziale(Argomento.O_PLF, s1, 21);

        ProvaParziale[] esameS1 = new ProvaParziale[5];

        esameS1[0] = scrittoJava;
        esameS1[1] = scrittoProlog;
        esameS1[2] = scrittoPython;
        esameS1[3] = oraleOOP;
        esameS1[4] = oralePLF;

        Studente s2 = new Studente("Luigi", "Bianchi", "759683");
        ProvaParziale scrittoJavaS2 = new ProvaParziale(Argomento.S_JAVA, s1, 20);
        ProvaParziale scrittoPrologS2 = new ProvaParziale(Argomento.S_PROLOG, s1, 1);
        ProvaParziale scrittoPythonS2 = new ProvaParziale(Argomento.S_PYTHON, s1, 8);
        ProvaParziale oraleOOPS2 = new ProvaParziale(Argomento.O_OOP, s1, 30);
        ProvaParziale oralePLFS2 = new ProvaParziale(Argomento.O_PLF, s1, 10);

        ProvaParziale[] esameS2 = new ProvaParziale[5];

        esameS2[0] = scrittoJavaS2;
        esameS2[1] = scrittoPrologS2;
        esameS2[2] = scrittoPythonS2;
        esameS2[3] = oraleOOPS2;
        esameS2[4] = oralePLFS2;

        Esame esameFinS1 = new Esame(esameS1, s1);
        esameFinS1.aggiungiBonus(2);

        System.out.println(esameFinS1);
        System.out.println();

        Esame esameFinS2 = new Esame(esameS2, s2);
        
        System.out.println(esameFinS2);

        ArrayList<Esame> esamiApp1 = new ArrayList<>();
        esamiApp1.add(esameFinS1);
        esamiApp1.add(esameFinS2);

        Appello appello1 = new Appello(esamiApp1, LocalDate.now());
        System.out.println("Percentuale promossi: " + appello1.getPercentualePromossi() + "%");
        System.out.println();

        appello1.getEsame("123456");       
    }
}
