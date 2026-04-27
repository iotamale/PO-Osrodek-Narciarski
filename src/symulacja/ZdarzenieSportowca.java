package symulacja;

import czas.Czas;
import osrodek_narciarski.Sportowiec;

public abstract class ZdarzenieSportowca extends Zdarzenie {

    private final Sportowiec sportowiec;
    private final SilnikSymulacji symulacja;

    public ZdarzenieSportowca(Sportowiec sportowiec, Czas czas, SilnikSymulacji symulacja) {
        super(czas);
        this.sportowiec = sportowiec;
        this.symulacja = symulacja;
    }

    public abstract void wykonaj();

    public Sportowiec pobierzSportowca() {
        return sportowiec;
    }

    public SilnikSymulacji pobierzSymulację() {
        return symulacja;
    }

    protected void loguj(String komunikat) {
        final Czas czas = pobierzCzas();
        sportowiec.loguj(czas, komunikat);
    }

}