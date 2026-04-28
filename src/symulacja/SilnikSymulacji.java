package symulacja;

import czas.Czas;
import kolejka_zdarzen.KolejkaZdarzeń;
import kolejka_zdarzen.ListowaKolejkaZdarzen;
import osrodek_narciarski.DaneSymulacji;
import osrodek_narciarski.Krawędź;
import osrodek_narciarski.Sportowiec;
import osrodek_narciarski.Wyciąg;

public class SilnikSymulacji {

    private static final Czas POCZĄTEK_SYMULACJI = new Czas("9:00:00");
    private static final Czas KONIEC_SYMULACJI = new Czas("15:00:00");

    private final GeneratorLosowy generator;
    private final KolejkaZdarzeń kolejka;
    private final DaneSymulacji daneSymulacji;

    public SilnikSymulacji(GeneratorLosowy generator, DaneSymulacji daneSymulacji) {
        this.generator = generator;
        this.daneSymulacji = daneSymulacji;
        this.kolejka = new ListowaKolejkaZdarzen();
    }

    private void inicjujPracęWyciągów() {
        final Wyciąg[] wyciągi = daneSymulacji.pobierzWyciągi();

        for (final Wyciąg wyciąg : wyciągi) {
            final OdjazdKrzesełka zdarzenie = new OdjazdKrzesełka(wyciąg, POCZĄTEK_SYMULACJI, this);
            dodajZdarzenieDoKolejki(zdarzenie);
        }
    }

    private void inicjujSportowców() {
        final Sportowiec[] sportowcy = daneSymulacji.pobierzSportowcy();

        for (final Sportowiec sportowiec : sportowcy) {
            final Czas czasPrzybycia = sportowiec.pobierzCzasPrzyjazdu();
            final PojawienieSięNaStoku wydarzenie = new PojawienieSięNaStoku(sportowiec, czasPrzybycia, this);
            dodajZdarzenieDoKolejki(wydarzenie);
        }
    }

    public void rozpcznij() {
        inicjujSportowców();
        inicjujPracęWyciągów();

        while (!kolejka.czyPusta()) {
            final Zdarzenie zdarzenie = kolejka.pobierzPierwsze();
            zdarzenie.wykonaj();
        }
    }

    protected void obsłóżDecyzjęSportowca(Sportowiec sportowiec, Czas czas) {
        if (!czas.czyWcześniej(KONIEC_SYMULACJI)) {
            return;
        }

        final Krawędź wybór = sportowiec.podejmijDecyzję(generator);
        wybór.obsłużDecyzję(sportowiec, czas, this);
    }

    public void dodajZdarzenieDoKolejki(Zdarzenie zdarzenie) {
        kolejka.dodaj(zdarzenie);
    }

    public void wypiszStatystyki() {
        System.out.println("------STATYSTYKI KOŃCOWE (po 15:00:00) ------");

        System.out.println();
        System.out.println("Wyciągi:");
        for (final Wyciąg wyciąg : daneSymulacji.pobierzWyciągi()) {
            System.out.println(wyciąg + " przewiózł łącznie " + wyciąg.pobierzLiczbęPrzejazdów() + " osób.");
        }

        // TODO dokończyć
        // TODO wyjątki wszędzie
    }

}
