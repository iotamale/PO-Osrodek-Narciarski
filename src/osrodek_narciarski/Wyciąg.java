package osrodek_narciarski;

public class Wyciąg {

    private final Węzeł początek; // dolna stacja
    private final Węzeł koniec; // górna stacja
    private final int odstępCzasowy;
    private final int maksWielkośćGrupy;
    private final int czasPrzejazdu;

    private final KolejkaSportowców kolejkaOczekujących;
    private int liczbaPrzejazdów;

    public Wyciąg(int id, Węzeł początek, Węzeł koniec, int odstępCzasowy, int maksWielkośćGrupy, int czasPrzejazdu) {
        this.początek = początek;
        this.koniec = koniec;
        this.odstępCzasowy = odstępCzasowy;
        this.maksWielkośćGrupy = maksWielkośćGrupy;
        this.czasPrzejazdu = czasPrzejazdu;

        this.kolejkaOczekujących = new KolejkaSportowców();
        this.liczbaPrzejazdów = 0;
    }

    public void dodajDoKolejki(Sportowiec sportowiec) {
        kolejkaOczekujących.dodaj(sportowiec);
    }

    public void zgłośPrzejazd(int liczbaOsób) {
        liczbaPrzejazdów += liczbaOsób;
    }

}