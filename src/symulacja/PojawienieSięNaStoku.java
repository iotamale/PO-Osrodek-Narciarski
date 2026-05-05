package symulacja;

import czas.Czas;
import osrodek_narciarski.Sportowiec;
import osrodek_narciarski.Węzeł;

public class PojawienieSięNaStoku extends ZdarzenieSportowca {

    public PojawienieSięNaStoku(Sportowiec sportowiec, Czas czas, SilnikSymulacji symulacja) {
        super(sportowiec, czas, symulacja);
    }

    @Override
    public void wykonaj() {
        final Sportowiec sportowiec = pobierzSportowca();
        final Węzeł węzełStartowy = sportowiec.pobierzWęzełStartowy();
        loguj("pojawił się na stoku w " + węzełStartowy);

        final SilnikSymulacji symulacja = pobierzSymulację();
        final Czas czas = pobierzCzas();
        symulacja.obsłóżDecyzjęSportowca(sportowiec, czas, węzełStartowy);
    }

}
