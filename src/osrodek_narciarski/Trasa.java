package osrodek_narciarski;

import czas.Czas;
import kolejka_zdarzen.KolejkaZdarzeń;
import symulacja.SilnikSymulacji;
import symulacja.ZakończenieZjazduTrasą;

public class Trasa extends Krawędź {

    private final int poziomTrudności; // 0-10
    private final double bazowaAtrakcyjność; // [0, 1]
    private final double odpornośćNierówności; // (0, 1]

    public Trasa(int id, Węzeł początek, Węzeł koniec, int poziomTrudności,
                 int czasPrzejazdu, double bazowaAtrakcyjność, double odpornośćNierówności) {
        super(id, początek, koniec, czasPrzejazdu);
        this.poziomTrudności = poziomTrudności;
        this.odpornośćNierówności = odpornośćNierówności;
        this.bazowaAtrakcyjność = bazowaAtrakcyjność;
    }

    public double atrakcyjnośćDTrasy(int pn) { // pn - poziomZaawansowaniaSportowca
        final int pt = poziomTrudności;

        if (pt >= pn + 5) {
            return 0;
        } else if (pn + 5 > pt && pt >= pn) {
            return 1 - (double)(pt - pn) / 5;
        } else {
            return Math.max(0.2, 1 - (double)(pn - pt) / 7);
        }
    }

    public double atrakcyjnośćWTrasy() {
        final int liczbaPrzejazdów = pobierzLiczbęPrzejazdów();
        return bazowaAtrakcyjność + (1 - bazowaAtrakcyjność) * Math.pow(odpornośćNierówności, liczbaPrzejazdów);
    }

    @Override
    public String toString() {
        return "Trasa nr " + String.valueOf(pobierzId());
    }

    @Override
    public void obsłużDecyzję(Sportowiec sportowiec, Czas czas, KolejkaZdarzeń kolejka, SilnikSymulacji symulacja) {
        sportowiec.loguj(czas, "rozpoczął zjazd " + this);
        zgłośPrzejazd(1);

        final Czas czasKońca = czas.dodajSekundy(pobierzCzasPrzejazdu());
        kolejka.dodaj(new ZakończenieZjazduTrasą(sportowiec, czasKońca, this, symulacja));
    }
}
