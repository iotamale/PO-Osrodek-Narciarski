package osrodek_narciarski;

import java.util.Arrays;

public class Węzeł {

    private static final int POCZĄTKOWY_ROZMIAR_TAB = 4;
    private static final int MNOŻNIK_TAB = 2;

    private final int id;
    private final int x;
    private final int y;
    private final int wysokość;
    private final boolean czySkomunikowany;

    private Trasa[] trasyWychodzące;
    private int liczbaTras;

    private Wyciąg[] wyciągiWychodzące;
    private int liczbaWyciągów;

    public Węzeł(int id, int wysokość, int x, int y, boolean czySkomunikowany) {
        this.id = id;
        this.wysokość = wysokość;
        this.x = x;
        this.y = y;
        this.czySkomunikowany = czySkomunikowany;

        this.trasyWychodzące = new Trasa[POCZĄTKOWY_ROZMIAR_TAB];
        this.liczbaTras = 0;

        this.wyciągiWychodzące = new Wyciąg[POCZĄTKOWY_ROZMIAR_TAB];
        this.liczbaWyciągów = 0;
    }

    private void powiększTrasy() {
        trasyWychodzące = Arrays.copyOf(trasyWychodzące, liczbaTras * MNOŻNIK_TAB);
    }

    private void powiększWyciągi() {
        wyciągiWychodzące = Arrays.copyOf(wyciągiWychodzące, liczbaWyciągów * MNOŻNIK_TAB);
    }

    public void dodajTrase(Trasa trasa) {
        if (trasyWychodzące.length == liczbaTras) {
            powiększTrasy();
        }

        trasyWychodzące[liczbaTras++] = trasa;
    }

    public void dodajWyciąg(Wyciąg wyciąg) {
        if (wyciągiWychodzące.length == liczbaWyciągów) {
            powiększWyciągi();
        }

        wyciągiWychodzące[liczbaWyciągów++] = wyciąg;
    }

    public int pobierzLiczbęTras() {
        return liczbaTras;
    }

    public int pobierzLiczbęWyciągów() {
        return liczbaWyciągów;
    }

    public Trasa[] pobierzTrasyWychodzące() {
        return Arrays.copyOf(trasyWychodzące, liczbaTras);
    }

    public Wyciąg[] pobierzWyciągiWychodzące() {
        return Arrays.copyOf(wyciągiWychodzące, liczbaWyciągów);
    }
}
