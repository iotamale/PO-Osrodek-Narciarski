package parser;

import java.io.InputStream;
import java.util.Scanner;

public class Czytnik {

    private final Scanner scanner;

    public Czytnik(InputStream stream) {
        this.scanner = new Scanner(stream);
    }

    public boolean czyMaKolejnyWiersz() {
        return scanner.hasNextLine();
    }

    public String czytajNastępnyWiersz() {
        return scanner.nextLine().trim();
    }

    public String pobierzNiepustyWiersz() {
        while (czyMaKolejnyWiersz()) {
            final String linia = czytajNastępnyWiersz();

            if (!linia.trim().isEmpty()) {
                return linia;
            }
        }

        return "";
    }

    public int czytajNastępnąLiczbę() {
        return scanner.nextInt();
    }

    public void zamknij() {
        scanner.close();
    }

}
