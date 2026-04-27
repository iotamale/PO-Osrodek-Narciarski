package osrodek_narciarski;

public class Wyciąg extends Krawędź {

    private final int odstępCzasowy;
    private final int maksWielkośćGrupy;
    private final KolejkaSportowców kolejkaOczekujących;

    public Wyciąg(int id, Węzeł początek, Węzeł koniec, int odstępCzasowy, int maksWielkośćGrupy, int czasPrzejazdu) {
        super(id, początek, koniec, czasPrzejazdu);
        this.odstępCzasowy = odstępCzasowy;
        this.maksWielkośćGrupy = maksWielkośćGrupy;

        this.kolejkaOczekujących = new KolejkaSportowców();
    }

    public void dodajDoKolejki(Sportowiec sportowiec) {
        kolejkaOczekujących.dodaj(sportowiec);
    }

}