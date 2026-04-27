package kolejka_zdarzen;

import czas.Czas;
import osrodek_narciarski.Sportowiec;

public abstract class ZdarzenieSportowca extends Zdarzenie {

    private final Sportowiec sportowiec;

    public ZdarzenieSportowca(Sportowiec sportowiec, Czas czas) {
        super(czas);
        this.sportowiec = sportowiec;
    }

    public abstract void wykonaj();

}