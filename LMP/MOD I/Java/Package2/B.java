package Package2;
import Package1.*;

public class B {
    public String nome = "Luca";

    public static void main(String[] args) {

        A provaA = new A();
        
        //System.out.println(provaA.nome);
        /*
        * in questo caso ho errore, perché B non è sottoclasse di A e con il modificatore
        * protected posso accedere al dato solamente nella classe, nel package o nelle sottoclassi.
        * Quindi per la classe C posso accedervi in quanto estende la classe A
        */    
    }  
}
