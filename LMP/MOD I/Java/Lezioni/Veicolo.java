package Lezioni;
/*
 * Le classi astratte sono classi che mettono a disposizione attributi e metodi 
 * concettuali per delle classi che ereditano i suoi parametri, 
 * ma non possono essere richiamate ed istanziate nel main 
 */


public abstract class Veicolo {
    
    abstract void muovi(); //i metodi astratti non hanno body, quindi viene specificato solamente il nome del metodo, senza specificare il suo funzionamento
    abstract void frena();
}
