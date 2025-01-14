package Esami.Biblioteca;

import java.time.LocalDate;

public class main {

    public static void main(String[] args) {

        Biblioteca b = new Biblioteca();
        Libro libro1 = new Libro("Il signore degli anelli", "Mondadori", 2002, 347);
        DVD dvd1 = new DVD("Toy Story", "Pixar", 2002, 120);

        b.addSupporto(libro1);
        b.addSupporto(dvd1);

        b.getCatalogo();

        b.iniziaPrestito(dvd1, LocalDate.of(2025, 01, 1), 21, "Nicolò", "Spadoni", 5.5);
        b.terminaPrestito(dvd1, LocalDate.of(2025, 01, 20));
        b.iniziaPrestito(dvd1, LocalDate.of(2025, 01, 26), 21, "Franco", "Salvucci", 4.5);
        b.terminaPrestito(dvd1, LocalDate.of(2025, 02, 28));
        b.iniziaPrestito(dvd1, LocalDate.of(2025, 03, 1), 25, "Leonardo", "Ascenzi", 5.5);
        b.terminaPrestito(dvd1, LocalDate.of(2025, 04, 5));
        b.iniziaPrestito(dvd1, LocalDate.of(2025, 01, 7), 21, "Nicolò", "Spadoni", 5.5);
        b.terminaPrestito(dvd1, LocalDate.of(2025, 04, 5));

        dvd1.printListaPrestiti();
        System.out.println(b.maxGiorniPrestito(dvd1));
        b.checkInconsistenze(dvd1);
    }
}
