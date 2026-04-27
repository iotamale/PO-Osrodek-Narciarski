package symulacja;

import czas.Czas;
import osrodek_narciarski.Wyciąg;

public abstract class ZdarzenieWyciągu extends Zdarzenie {

    private final Wyciąg wyciąg;
    private final SilnikSymulacji symulacja;

    protected ZdarzenieWyciągu(Wyciąg wyciąg, Czas czas, SilnikSymulacji symulacja) {
        super(czas);
        this.wyciąg = wyciąg;
        this.symulacja = symulacja;
    }

    protected Wyciąg pobierzWyciąg() {
        return wyciąg;
    }

    protected SilnikSymulacji pobierzSymulację() {
        return symulacja;
    }

    public abstract void wykonaj();

}
