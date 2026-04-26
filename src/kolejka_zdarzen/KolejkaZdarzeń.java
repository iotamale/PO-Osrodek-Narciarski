package kolejka_zdarzen;

public interface KolejkaZdarzeń {

    void dodaj(Zdarzenie zdarzenie);

    Zdarzenie pobierzPierwsze();

    boolean czyPusta();

}
