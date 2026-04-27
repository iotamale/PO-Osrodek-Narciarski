package kolejka_zdarzen;

import czas.Czas;
import osrodek_narciarski.Wyciąg;

public abstract class ZdarzenieWyciągu extends Zdarzenie {

    private final Wyciąg wyciąg;

    public ZdarzenieWyciągu(Wyciąg wyciąg, Czas czas) {
        super(czas);
        this.wyciąg = wyciąg;
    }

    public abstract void wykonaj();

}
