package Package2;

/*
 * Per estendere la classe C con la classe A, bisogna importare il pacchetto1 in cui è contenuta la classe A
 * altrimenti darebbe errore. Con la notazione * intendiamo di importare tutti i file del package1
 */
import Package1.*; 

public class C extends A {

    public static void main(String[] args) {
        C provaC = new C();
        System.out.println(provaC.nome); //riesco ad accedere all'attributo nome ereditato dalla classe A perché è dichiarato con protected
    }

}
