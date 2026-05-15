package osrodek_narciarski;

public class PustaKolejkaSportowców extends RuntimeException {
    public PustaKolejkaSportowców() {
        super("Próba pobrania z pustej kolejki sportowców!");
    }
}
