package parser;

import czas.Czas;
import osrodek_narciarski.*;
import symulacja.GeneratorLosowy;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Parser {

    private static final int POCZĄTKOWY_ROZMIAR_TAB = 16;
    private static final int MNOŻNIK_TAB = 2;

    private final Czytnik czytnik;
    private Sportowiec[] sportowcy;
    private int iluSportowców;

    public Parser(Czytnik czytnik) {
        this.czytnik = czytnik;
        przygotujTablicęSportowców();
    }

    public DaneSymulacji wczytaj() {
        final int liczbaWęzłów = czytnik.czytajNastępnąLiczbę();
        final Węzeł[] węzły = wczytajWęzły(liczbaWęzłów);

        final int liczbaWyciągów = czytnik.czytajNastępnąLiczbę();
        final Wyciąg[] wyciągi = wczytajWyciągi(liczbaWyciągów, węzły);

        final int liczbaTras = czytnik.czytajNastępnąLiczbę();
        final Trasa[] trasy = wczytajTrasy(liczbaTras, węzły);

        // Teoretycznie można użyć tego samego parsera do wczytania potem innych danych.
        if (iluSportowców > 0) {
            przygotujTablicęSportowców();
        }
        final int liczbaGrupSportowców = czytnik.czytajNastępnąLiczbę();
        wczytajSportowców(liczbaGrupSportowców, węzły);

        // obetnijSportowców() zwraca kopie this.sportowiec obciętą do poprawnego rozmiaru.
        return new DaneSymulacji(węzły, wyciągi, trasy, obetnijSportowców());
    }

    private Węzeł[] wczytajWęzły(int ile) {
        final Węzeł[] węzły = new Węzeł[ile];

        for (int i = 0; i < ile; i++) {
            final Scanner poWierszu = skanerWiersza(czytnik.pobierzNiepustyWiersz());

            final int wysokość = poWierszu.nextInt();
            final int x = poWierszu.nextInt();
            final int y = poWierszu.nextInt();
            final boolean czySkomunikowany = poWierszu.hasNext("s");

            węzły[i] = new Węzeł(i, wysokość, x, y, czySkomunikowany);
        }

        return węzły;
    }

    private Wyciąg[] wczytajWyciągi(int ile, Węzeł[] węzły) {
        final Wyciąg[] wyciągi = new Wyciąg[ile];

        for (int i = 0; i < ile; i++) {
            final Scanner poWierszu = skanerWiersza(czytnik.pobierzNiepustyWiersz());

            final int nrPoczątek = poWierszu.nextInt();
            final int nrKoniec = poWierszu.nextInt();
            final int odstępCzasowy = poWierszu.nextInt();
            final int maksWielkośćGrupy = poWierszu.nextInt();
            final int czasPrzejazdu = poWierszu.nextInt();

            wyciągi[i] = new Wyciąg(i, węzły[nrPoczątek], węzły[nrKoniec], odstępCzasowy, maksWielkośćGrupy, czasPrzejazdu);
            węzły[nrPoczątek].dodajWyciąg(wyciągi[i]);
        }

        return wyciągi;
    }

    private Trasa[] wczytajTrasy(int ile, Węzeł[] węzły) {
        final Trasa[] trasy = new Trasa[ile];

        for (int i = 0; i < ile; i++) {
            final Scanner poWierszu = skanerWiersza(czytnik.pobierzNiepustyWiersz());

            final int nrPoczątek = poWierszu.nextInt();
            final int nrKoniec = poWierszu.nextInt();
            final int trudność = poWierszu.nextInt();
            final int czasPrzejazdu = poWierszu.nextInt();
            final double atrakcyjność = poWierszu.nextDouble();
            final double odporność = poWierszu.nextDouble();

            trasy[i] = new Trasa(i, węzły[nrPoczątek], węzły[nrKoniec], trudność, czasPrzejazdu, atrakcyjność, odporność);
            węzły[nrPoczątek].dodajTrase(trasy[i]);
        }

        return trasy;
    }

    private void przygotujTablicęSportowców() {
        sportowcy = new Sportowiec[POCZĄTKOWY_ROZMIAR_TAB];
        iluSportowców = 0;
    }

    private void powiększSportowców(int ilePotrzeba) {
        int p = sportowcy.length;

        while (p < ilePotrzeba) {
            p *= MNOŻNIK_TAB;
        }

        sportowcy = Arrays.copyOf(sportowcy, p);
    }

    private Sportowiec[] obetnijSportowców() {
        return Arrays.copyOf(sportowcy, iluSportowców);
    }

    private void wczytajSportowców(int ileGrup, Węzeł[] węzły) {
        for (int i = 0; i < ileGrup; i++) {
            Scanner poWierszu = skanerWiersza(czytnik.pobierzNiepustyWiersz());
            final int rozmiarGrupy = poWierszu.nextInt();
            final int zaawansowanie = poWierszu.nextInt();
            final double spontaniczność = poWierszu.nextDouble();
            final boolean czyŚledzić = poWierszu.hasNext("s");

            poWierszu = skanerWiersza(czytnik.pobierzNiepustyWiersz());
            final double wagaD = poWierszu.nextDouble();
            final double wagaW = poWierszu.nextDouble();

            poWierszu = skanerWiersza(czytnik.pobierzNiepustyWiersz());
            final int nrStartowegoWęzła = poWierszu.nextInt();
            final Węzeł start = węzły[nrStartowegoWęzła];
            final String godzinaStr = poWierszu.next();
            Czas godzina = new Czas(godzinaStr);
            final int odstęp = (rozmiarGrupy > 1) ? poWierszu.nextInt() : 0;

            powiększSportowców(iluSportowców + rozmiarGrupy);

            for (int j = 0; j < rozmiarGrupy; j++) {
                final Czas przyjazd = godzina.dodajSekundy(j * odstęp);
                sportowcy[iluSportowców] = new Sportowiec(iluSportowców, zaawansowanie, start, przyjazd, wagaD, wagaW, spontaniczność, czyŚledzić);
                iluSportowców++;
            }
        }
    }

    private Scanner skanerWiersza(String wiersz) {
        final Scanner scanner = new Scanner(wiersz);
        scanner.useLocale(Locale.ENGLISH);
        return scanner;
    }

}