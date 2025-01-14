package Lezioni;
public class Automobile extends Veicolo {

    @Override
    void muovi() {
        System.out.println("L'automobile si muove...");
    }

    @Override
    void frena() {
        System.out.println("L'automobile frena...");
    }

    
}
