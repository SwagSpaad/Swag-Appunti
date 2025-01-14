package Package1;
import Package2.*;

/*            class       package    subclass     world
 * public      si            si         si         si
 * protected   si            si         si         no
 * default     si            si         no         no
 * private     si            no         no         no
 */


public class Main {
    public static void main(String[] args) {
        B provaB = new B();

        /* prova.nome; non funziona, perché nel package2 non abbiamo specificato nessun modificatore di accesso
        quindi ha preso il modificatore di default che permette l'accesso solo nella class e nel package di appartenenza 
        Per fare in modo che l'attributo nome della classe B venga visto all'interno del package1, bisogna importare il package2
        ma inoltre bisogna dichiarare l'attributo col modificatore di accesso public
        */
        System.out.println(provaB.nome);

        A provaA = new A();
        System.out.println(provaA.nome); //in questo caso con la parola protected nello stesso package non ho restrizioni, quindi posso accedere
    }
}
