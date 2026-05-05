package symulacja;

import czas.Czas;
import osrodek_narciarski.Sportowiec;
import osrodek_narciarski.Trasa;
import osrodek_narciarski.Węzeł;

public class ZakończenieZjazduTrasą extends ZdarzenieSportowca {

    private final Trasa trasa;

    public ZakończenieZjazduTrasą(Sportowiec sportowiec, Czas czas, Trasa trasa, SilnikSymulacji symulacja) {
        super(sportowiec, czas, symulacja);
        this.trasa = trasa;
    }

    @Override
    public void wykonaj() {
        loguj("zakończył zjazd " + trasa);

        final Sportowiec sportowiec = pobierzSportowca();
        final Węzeł nowyObecny = trasa.pobierzKoniec();
        final SilnikSymulacji symulacja = pobierzSymulację();

        symulacja.obsłóżDecyzjęSportowca(sportowiec, pobierzCzas(), nowyObecny);
    }

}
