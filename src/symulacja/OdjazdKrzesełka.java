package symulacja;

import czas.Czas;
import osrodek_narciarski.Sportowiec;
import osrodek_narciarski.Wyciąg;

public class OdjazdKrzesełka extends ZdarzenieWyciągu {

    private static final Czas KONIEC_SYMULACJI = new Czas("15:00:00");

    public OdjazdKrzesełka(Wyciąg wyciąg, Czas czas, SilnikSymulacji symulacja) {
        super(wyciąg, czas, symulacja);
    }

    @Override
    public void wykonaj() {
        final Czas czas = pobierzCzas();
        if (!czas.czyWcześniej(KONIEC_SYMULACJI)) {
            return;
        }

        final SilnikSymulacji symulacja = pobierzSymulację();
        final Wyciąg wyciąg = pobierzWyciąg();
        int zabraniPasażerowie = 0;
        final int pojemność = wyciąg.pobierzPojemność();


        while (zabraniPasażerowie < pojemność && !wyciąg.czyPustaKolejka()) {
            final Sportowiec pasażer = wyciąg.weźZKolejki();
            zabraniPasażerowie++;

            pasażer.loguj(czas, "rozpoczyna wjazd " + wyciąg);

            final Czas czasDojazdu = czas.dodajSekundy(wyciąg.pobierzCzasPrzejazdu());
            symulacja.dodajZdarzenieDoKolejki(new ZakończenieWjazduWyciągiem(pasażer, czasDojazdu, symulacja, wyciąg));
        }

        if (zabraniPasażerowie > 0) {
            wyciąg.zgłośPrzejazd(zabraniPasażerowie);
        }

        final Czas następnyOdjazd = czas.dodajSekundy(wyciąg.pobierzOdstępCzasowy());
        symulacja.dodajZdarzenieDoKolejki(new OdjazdKrzesełka(wyciąg, następnyOdjazd, symulacja));
    }

}
