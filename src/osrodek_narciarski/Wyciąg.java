package osrodek_narciarski;

import czas.Czas;
import kolejka.Kolejka;

public class Wyciąg {

    private static Czas POCZĄTEK_PRACY = new Czas(9);
    private static Czas KONIEC_PRACY = new Czas(16);

    private final Węzeł stacjaPoczątkowa;
    private final Węzeł stacjaKońcowa;
    private final int odstępCzasowy;
    private final int maksWielkośćGrupy;

    private Kolejka<Sportowiec> kolejkaDoWjazdu;

    public Wyciąg(Węzeł stacjaPoczątkowa, Węzeł stacjaKońcowa, int odstępCzasowy, int maksWielkośćGrupy) {
        this.stacjaPoczątkowa = stacjaPoczątkowa;
        this.stacjaKońcowa = stacjaKońcowa;
        this.odstępCzasowy = odstępCzasowy;
        this.maksWielkośćGrupy = maksWielkośćGrupy;
        kolejkaDoWjazdu = new Kolejka<Sportowiec>();
    }

}