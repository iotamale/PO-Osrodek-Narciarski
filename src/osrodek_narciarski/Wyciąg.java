package osrodek_narciarski;

import czas.Czas;
import kolejka_zdarzen.KolejkaZdarzeń;
import symulacja.SilnikSymulacji;

public class Wyciąg extends Krawędź {

    private final int odstępCzasowy;
    private final int maksWielkośćGrupy;
    private final KolejkaSportowców kolejkaOczekujących;

    public Wyciąg(int id, Węzeł początek, Węzeł koniec, int odstępCzasowy, int maksWielkośćGrupy, int czasPrzejazdu) {
        super(id, początek, koniec, czasPrzejazdu);
        this.odstępCzasowy = odstępCzasowy;
        this.maksWielkośćGrupy = maksWielkośćGrupy;

        this.kolejkaOczekujących = new KolejkaSportowców();
    }

    private void dodajDoKolejki(Sportowiec sportowiec) {
        kolejkaOczekujących.dodaj(sportowiec);
    }

    public int pobierzPojemność() {
        return maksWielkośćGrupy;
    }

    public int pobierzOdstępCzasowy() {
        return odstępCzasowy;
    }

    public boolean czyPustaKolejka() {
        return kolejkaOczekujących.czyPusta();
    }

    public Sportowiec weźZKolejki() {
        return kolejkaOczekujących.pobierz();
    }

    @Override
    public String toString() {
        return "Wyciąg nr " + String.valueOf(pobierzId());
    }

    @Override
    public void obsłużDecyzję(Sportowiec sportowiec, Czas czas, SilnikSymulacji symulacja) {
        sportowiec.loguj(czas, "ustawia się w kolejce do " + this);

        dodajDoKolejki(sportowiec); // dodajemy do kolejki WYCIĄGU
    }
}