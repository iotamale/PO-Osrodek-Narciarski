package symulacja;

import osrodek_narciarski.DaneSymulacji;
import parser.Czytnik;
import parser.Parser;

public class Main {

    public static void main(String[] args) {
        final Czytnik czytnik = new Czytnik(System.in);
        final GeneratorLosowy generator = new GeneratorLosowy();
        final Parser parser = new Parser(czytnik);
        final DaneSymulacji daneSymulacji = parser.wczytaj();
    }

}
