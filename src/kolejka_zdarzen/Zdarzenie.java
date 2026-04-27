package kolejka_zdarzen;

import czas.Czas;

public abstract class Zdarzenie {

    private final Czas czas;

    public Zdarzenie(Czas czas) {
        this.czas = czas;
    }

    public Czas pobierzCzas() {
        return czas;
    }

    public abstract void wykonaj();
}