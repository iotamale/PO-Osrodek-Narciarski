package kolejka_zdarzen;

public class PustaKolejkaZdarzeń extends RuntimeException {
    public PustaKolejkaZdarzeń() {
        super("Próba pobrania zdarzenia z pustej kolejki zdarzeń!");
    }
}
