package Esami.ImpresaSportiva;

import java.time.LocalDate;
import java.time.Month;

public class main {

    public static void main(String[] args) {
        Sportivo sportivo1 = new Sportivo("Nicolò", "Spadoni", LocalDate.of(2025, 1, 1), TipoSportivo.GIOCATORE, 5);
        Sportivo sportivo2 = new Sportivo("Leonardo", "Ascenzi", LocalDate.of(2025, 2, 1), TipoSportivo.GIOCATORE, 2);
        Sportivo sportivo3 = new Sportivo("Franco", "Salvucci", LocalDate.of(2025, 1, 1), TipoSportivo.ALLENATORE, 4);
        Sportivo sportivo4 = new Sportivo("Davide", "Conti", LocalDate.of(2025, 2, 1), TipoSportivo.PREPARATORE_ATLETICO, 3);
        Sportivo sportivo5 = new Sportivo("Andrea", "Fortini", LocalDate.of(2025, 2, 1), TipoSportivo.PREPARATORE_ATLETICO, 4);
        Sportivo sportivo6 = new Sportivo("Damiano", "Folco", LocalDate.of(2024, 2, 1), TipoSportivo.PREPARATORE_ATLETICO, 5);
        Sportivo sportivo7 = new Sportivo("Damiano", "Necci", LocalDate.of(2025, 1, 1), TipoSportivo.GIOCATORE, 3);

        sportivo1.setGolPerMese(Month.JANUARY, 3);
        sportivo1.setGolPerMese(Month.FEBRUARY, 5);
        sportivo1.setGolPerMese(Month.MARCH, 1);

        sportivo2.setGolPerMese(Month.JANUARY, 16);
        sportivo2.setGolPerMese(Month.FEBRUARY, 1);
        sportivo2.setGolPerMese(Month.MARCH, 10);

        sportivo7.setGolPerMese(Month.JANUARY, 4);
        sportivo7.setGolPerMese(Month.FEBRUARY, 6);
        sportivo7.setGolPerMese(Month.MARCH, 8);

        ImpresaSportiva is = new ImpresaSportiva(3);

        is.addSportivo(sportivo1);
        is.addSportivo(sportivo2);
        is.addSportivo(sportivo3);
        is.addSportivo(sportivo4);
        is.addSportivo(sportivo5);
        is.addSportivo(sportivo6);
        is.addSportivo(sportivo7);

        is.printOrganicoImpresa();

        is.assegnaBonus();
        is.assuntiPerMese();

    }
}
