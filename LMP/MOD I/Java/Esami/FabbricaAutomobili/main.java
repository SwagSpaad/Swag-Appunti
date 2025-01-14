package Esami.FabbricaAutomobili;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        Fabbrica fab1 = new Fabbrica(20, 20);

        Componente comp1 = new Componente("A", "Italia", 3, 5.5);
        Componente comp2 = new Componente("B", "Francia", 1, 8);
        Componente comp3 = new Componente("C", "Spagna", 5, 10);

        ArrayList<Componente> listaCompProdotto1 = new ArrayList<>();
        listaCompProdotto1.add(comp1);
        listaCompProdotto1.add(comp2);
        listaCompProdotto1.add(comp3);

        fab1.addProdotto("AB12", "abcdef", listaCompProdotto1, 3);

        System.out.println(fab1.getCatalogoProd());
        System.out.println(fab1.getRankingProdotto("AB12"));
    }
}
