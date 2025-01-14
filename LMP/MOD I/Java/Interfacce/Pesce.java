package Interfacce;

public class Pesce implements Preda, Predatore {
    @Override
    public void caccia() {
        System.out.println("Il pesce grande inizia la caccia.");
        
    }

    @Override
    public void scappa() {
        System.out.println("Il pesce piccolo scappa.");
    }

}
