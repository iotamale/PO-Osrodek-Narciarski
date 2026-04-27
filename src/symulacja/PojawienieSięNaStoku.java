package symulacja;

import czas.Czas;
import osrodek_narciarski.Sportowiec;

public class PojawienieSięNaStoku extends ZdarzenieSportowca {

    public PojawienieSięNaStoku(Sportowiec sportowiec, Czas czas, SilnikSymulacji symulacja) {
        super(sportowiec, czas, symulacja);
    }

    @Override
    public void wykonaj() {
        final Sportowiec sportowiec = pobierzSportowca();
        loguj("pojawił się na stoku w " + sportowiec.pobierzObecnyWęzeł());

        final SilnikSymulacji symulacja = pobierzSymulację();
        final Czas czas = pobierzCzas();
        symulacja.obsłóżDecyzjęSportowca(sportowiec, czas);
    }

}
