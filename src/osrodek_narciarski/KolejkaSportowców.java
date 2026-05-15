package osrodek_narciarski;

import kolejka_zdarzen.PustaKolejkaZdarzeń;

public class KolejkaSportowców {

    private static final int POCZĄTKOWY_ROZMIAR_BUFORA = 16;
    private static final int WSPÓŁCZYNNIK_POWIĘKSZENIA = 2;

    private Sportowiec[] bufor;
    private int początek;
    private int rozmiar;

    public KolejkaSportowców() {
        this.bufor = new Sportowiec[POCZĄTKOWY_ROZMIAR_BUFORA];
        this.początek = 0;
        this.rozmiar = 0;
    }

    private void powiększBufor() {
        final Sportowiec[] nowa = new Sportowiec[bufor.length * WSPÓŁCZYNNIK_POWIĘKSZENIA];

        for (int i = 0; i < rozmiar; i++) {
            final int indeks = (początek + i) % bufor.length;
            nowa[i] = bufor[indeks];
        }

        bufor = nowa;
        początek = 0;
    }

    public void dodaj(Sportowiec sportowiec) {
        if (rozmiar == bufor.length) {
            powiększBufor();
        }

        final int koniec = (początek + rozmiar) % bufor.length;
        bufor[koniec] = sportowiec;
        rozmiar++;
    }

    public Sportowiec pobierz() throws PustaKolejkaSportowców {
        if (czyPusta()) {
            throw new PustaKolejkaSportowców();
        }

        final Sportowiec pobrany = bufor[początek];
        bufor[początek] = null;

        początek = (początek + 1) % bufor.length;
        rozmiar--;

        return pobrany;
    }

    public boolean czyPusta() {
        return rozmiar == 0;
    }

    public int pobierzRozmiar() {
        return rozmiar;
    }

}
