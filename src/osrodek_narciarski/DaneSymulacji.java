package osrodek_narciarski;

import java.util.Arrays;

public class DaneSymulacji {

    private final Węzeł[] węzły;
    private final Wyciąg[] wyciągi;
    private final Trasa[] trasy;
    private final Sportowiec[] sportowcy;

    public DaneSymulacji(Węzeł[] węzły, Wyciąg[] wyciągi, Trasa[] trasy, Sportowiec[] sportowcy) {
        this.węzły = węzły;
        this.wyciągi = wyciągi;
        this.trasy = trasy;
        this.sportowcy = sportowcy;
    }

    public Wyciąg[] pobierzWyciągi() {
        return Arrays.copyOf(wyciągi, wyciągi.length);
    }

    public Sportowiec[] pobierzSportowcy() {
        return Arrays.copyOf(sportowcy, sportowcy.length);
    }

    public Trasa[] pobierzTrasy() {
        return Arrays.copyOf(trasy, trasy.length);
    }
}
