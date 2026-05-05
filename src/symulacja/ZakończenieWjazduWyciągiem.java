package symulacja;

import czas.Czas;
import osrodek_narciarski.Sportowiec;
import osrodek_narciarski.Wyciąg;
import osrodek_narciarski.Węzeł;

public class ZakończenieWjazduWyciągiem extends ZdarzenieSportowca {

    private final Wyciąg wyciąg;

    public ZakończenieWjazduWyciągiem(Sportowiec sportowiec, Czas czas, SilnikSymulacji symulacja, Wyciąg wyciąg) {
        super(sportowiec, czas, symulacja);
        this.wyciąg = wyciąg;
    }

    @Override
    public void wykonaj() {
        loguj("schodzi z " + wyciąg);

        final Sportowiec sportowiec = pobierzSportowca();
        final Węzeł stacja = wyciąg.pobierzKoniec();

        final SilnikSymulacji symulacja = pobierzSymulację();
        final Czas czas = pobierzCzas();
        symulacja.obsłóżDecyzjęSportowca(sportowiec, czas, stacja);
    }
}
