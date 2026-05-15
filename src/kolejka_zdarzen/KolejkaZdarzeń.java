package kolejka_zdarzen;

import symulacja.Zdarzenie;

public interface KolejkaZdarzeń {

    void dodaj(Zdarzenie zdarzenie);

    Zdarzenie pobierzPierwsze() throws PustaKolejkaZdarzeń;

    boolean czyPusta();

}
