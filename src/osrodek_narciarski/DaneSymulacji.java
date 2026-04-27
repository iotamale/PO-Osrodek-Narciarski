package osrodek_narciarski;

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
        return wyciągi;
    }

    public Sportowiec[] pobierzSportowcy() {
        return sportowcy;
    }
}
