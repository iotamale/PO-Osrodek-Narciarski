package kolejka_zdarzen;

import czas.Czas;

public interface Zdarzenie {

    Czas pobierzCzas(); // w sekundach

    void wykonaj();

}
