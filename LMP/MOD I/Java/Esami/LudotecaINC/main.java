package Esami.LudotecaINC;

import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

public class main {

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();

        ludoteca.addGioco("Monopoly", "A", "B", Year.of(2002), 3, 120);
        ludoteca.addLibro("Il signore degli anelli", "Tolkien", "Mondadori", Year.of(1950), 2, 679);
        ludoteca.addGioco("Risiko", "C", "D", Year.of(1999), 2, 180);

    }

}
