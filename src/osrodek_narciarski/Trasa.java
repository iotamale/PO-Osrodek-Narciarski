package osrodek_narciarski;

public class Trasa {

    private final int id;
    private final Węzeł początek;
    private final Węzeł koniec;
    private final int poziomTrudności; // 0-10
    private final int czasPrzejazdu; // w sekundach

    private final double odpornośćNierówności; // (0, 1]
    private final double bazowaAtrakcyjność; // [0, 1]
    private int liczbaPrzejazdów;

    public Trasa(int id, Węzeł początek, Węzeł koniec, int poziomTrudności,
                 int czasPrzejazdu, double odpornośćNierówności, double bazowaAtrakcyjność) {
        this.id = id;
        this.początek = początek;
        this.koniec = koniec;
        this.poziomTrudności = poziomTrudności;
        this.czasPrzejazdu = czasPrzejazdu;
        this.odpornośćNierówności = odpornośćNierówności;
        this.bazowaAtrakcyjność = bazowaAtrakcyjność;
        this.liczbaPrzejazdów = 0;
    }

    public void zgłośPrzejazd() {
        liczbaPrzejazdów++;
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
        return bazowaAtrakcyjność + (1 - bazowaAtrakcyjność) * Math.pow(odpornośćNierówności, liczbaPrzejazdów);
    }

}
