package Lezioni;
public class Pizza {
    String impasto;
    String salsa;
    String formaggio;
    String extra1;
    String extra2;

    //Overloaded constructors
    Pizza(String impasto) {
        this.impasto = impasto;
    }

    Pizza(String impasto, String salsa) {
        this.impasto = impasto;
        this.salsa = salsa;
    }

    Pizza(String impasto, String salsa, String formaggio) {
        this.impasto = impasto;
        this.salsa = salsa;
        this.formaggio = formaggio;
    }    
    
    Pizza(String impasto, String salsa, String formaggio, String extra1) {
        this.impasto = impasto;
        this.salsa = salsa;
        this.formaggio = formaggio;
        this.extra1 = extra1;
    }

    Pizza(String impasto, String salsa, String formaggio, String extra1, String extra2) {
        this.impasto = impasto;
        this.salsa = salsa;
        this.formaggio = formaggio;
        this.extra1 = extra1;
        this.extra2 = extra2;
    }

    public String toString(){
        String stringa = this.impasto + "\n" + this.salsa + "\n" + this.formaggio + "\n" + this.extra1 + "\n" + this.extra2;
        return stringa;
    }
}
