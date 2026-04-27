package symulacja;

import czas.Czas;
import kolejka_zdarzen.KolejkaZdarzeń;
import kolejka_zdarzen.ListowaKolejkaZdarzen;
import osrodek_narciarski.Krawędź;
import osrodek_narciarski.Sportowiec;

public class SilnikSymulacji {

    private static final Czas KONIEC_SYMULACJI = new Czas("15:00:00");

    private final GeneratorLosowy generator;
    private final KolejkaZdarzeń kolejka;

    public SilnikSymulacji(GeneratorLosowy generator) {
        this.generator = generator;
        this.kolejka = new ListowaKolejkaZdarzen();
    }

    public void obsłóżDecyzjęSportowca(Sportowiec sportowiec, Czas czas) {
        if (!czas.czyWcześniej(KONIEC_SYMULACJI)) {
            return;
        }

        final Krawędź wybór = sportowiec.podejmijDecyzję(generator);
        wybór.obsłużDecyzję(sportowiec, czas, this);
    }

    public void dodajZdarzenieDoKolejki(Zdarzenie zdarzenie) {
        kolejka.dodaj(zdarzenie);
    }

}
