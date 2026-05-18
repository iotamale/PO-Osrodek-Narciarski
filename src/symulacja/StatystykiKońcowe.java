package symulacja;

import osrodek_narciarski.DaneSymulacji;
import osrodek_narciarski.Trasa;
import osrodek_narciarski.Wyciąg;

public class StatystykiKońcowe {

    private final String[] statTrasy;
    private final String[] statWyciąg;

    public StatystykiKońcowe(DaneSymulacji daneSymulacji) {
        final Trasa[] trasy = daneSymulacji.pobierzTrasy();
        final Wyciąg[] wyciągi = daneSymulacji.pobierzWyciągi();

        statTrasy = new String[trasy.length];
        statWyciąg = new String[wyciągi.length];

        int i = 0;
        for (final Trasa trasa : trasy) {
            statTrasy[i++] = trasa + " została przejechana " + trasa.pobierzLiczbęPrzejazdów() + " razy.";
        }

        i = 0;
        for (final Wyciąg wyciąg : wyciągi) {
            statWyciąg[i++] = wyciąg + " przewiózł łącznie " + wyciąg.pobierzLiczbęPrzejazdów() + " pasażerów.";
        }
    }

    public void wypisz() {
        System.out.println("-------- STATYSTYKI KOŃCOWE (po 15:00:00) --------");

        for (final String x : statTrasy) {
            System.out.println(x);
        }

        System.out.println();
        for (final String x : statWyciąg) {
            System.out.println(x);
        }

        System.out.println("--------------- KONIEC STATYSTYK ---------------");
    }

}
