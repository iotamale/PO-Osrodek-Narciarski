package osrodek_narciarski;

import czas.Czas;
import kolejka_zdarzen.KolejkaZdarzeń;
import symulacja.SilnikSymulacji;

public abstract class Krawędź {

    private final int id;
    private final Węzeł początek; // dolna stacja wyciągu / początek trasy (na górze)
    private final Węzeł koniec;
    private final int czasPrzejazdu; // w sekundach
    private int liczbaPrzejazdów;

    public Krawędź(int id, Węzeł początek, Węzeł koniec, int czasPrzejazdu) {
        this.id = id;
        this.początek = początek;
        this.koniec = koniec;
        this.czasPrzejazdu = czasPrzejazdu;
        this.liczbaPrzejazdów = 0;
    }

    public abstract void obsłużDecyzję(Sportowiec sportowiec, Czas czas, SilnikSymulacji symulacja);

    public void zgłośPrzejazd(int liczbaOsób) {
        liczbaPrzejazdów += liczbaOsób;
    }

    public int pobierzLiczbęPrzejazdów() {
        return liczbaPrzejazdów;
    }

    public Węzeł pobierzKoniec() {
        return koniec;
    }

    public int pobierzCzasPrzejazdu() {
        return czasPrzejazdu;
    }

    public int pobierzId() {
        return id;
    }

}
