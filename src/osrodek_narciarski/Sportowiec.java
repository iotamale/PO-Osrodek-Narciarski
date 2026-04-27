package osrodek_narciarski;

import czas.Czas;
import symulacja.GeneratorLosowy;

public class Sportowiec {

    private final int id;
    private final int poziomZaawansowania;
    private final Węzeł węzełStartowy;
    private final Czas godzinaPrzyjazdu;

    private final double wagaD;
    private final double wagaW;
    private final double spontaniczność;
    private final boolean czyŚledzić;
    private Węzeł obecnyWęzeł;

    public Sportowiec(
            int id, int poziomZaawansowania, Węzeł węzełStartowy, Czas godzinaPrzyjazdu, double wagaD,
            double wagaW, double spontaniczność, boolean czyŚledzić) {
        this.id = id;
        this.poziomZaawansowania = poziomZaawansowania;
        this.węzełStartowy = węzełStartowy;
        this.godzinaPrzyjazdu = godzinaPrzyjazdu;
        this.wagaD = wagaD;
        this.wagaW = wagaW;
        this.spontaniczność = spontaniczność;
        this.czyŚledzić = czyŚledzić;
        this.obecnyWęzeł = węzełStartowy;
    }

    private double łącznaAtrakcyjnośćTrasy(Trasa trasa) {
        final double d = trasa.atrakcyjnośćDTrasy(poziomZaawansowania);
        final double w = trasa.atrakcyjnośćWTrasy();

        return wagaD * d + wagaW * w;
    }

    public Krawędź podejmijDecyzję(GeneratorLosowy generator) {
        final Trasa[] trasyWychodzące = obecnyWęzeł.pobierzTrasyWychodzące();
        final Wyciąg[] wyciągiWychodzące = obecnyWęzeł.pobierzWyciągiWychodzące();
        final int liczbaTras = obecnyWęzeł.pobierzLiczbęTras();
        final int liczbaWyciągów = obecnyWęzeł.pobierzLiczbęWyciągów();

        final int sumaOpcji = liczbaTras + liczbaWyciągów;
        final boolean czySpontaniczna = generator.czyZajdzieZdarzenie(spontaniczność);

        // Wybór spontaniczny
        if (czySpontaniczna) {
            final int wybór = generator.losujInt(0, sumaOpcji);
            if (wybór < liczbaTras) {
                return trasyWychodzące[wybór];
            } else {
                return wyciągiWychodzące[wybór - liczbaTras];
            }
        }

        // Normalny wybór
        double najlepszaAtrakcyjność = -1.0;
        Krawędź najlepszaDecyzja = null;

        for (final Trasa trasa : trasyWychodzące) {
            final double atrakcyjność = łącznaAtrakcyjnośćTrasy(trasa);
            if (atrakcyjność > najlepszaAtrakcyjność) {
                najlepszaDecyzja = trasa;
                najlepszaAtrakcyjność = atrakcyjność;
            }
        }

        for (final Wyciąg wyciąg : wyciągiWychodzące) {
            final Węzeł stacjaGórna = wyciąg.pobierzKoniec();
            final Trasa[] trasyStacji = stacjaGórna.pobierzTrasyWychodzące();

            for (final Trasa trasa : trasyStacji) {
                final double atrakcyjność = łącznaAtrakcyjnośćTrasy(trasa);
                if (atrakcyjność > najlepszaAtrakcyjność) {
                    najlepszaDecyzja = wyciąg;
                    najlepszaAtrakcyjność = atrakcyjność;
                }
            }
        }

        return najlepszaDecyzja;
    }

    @Override
    public String toString() {
        return "Sportowiec nr " + String.valueOf(id);
    }

    public boolean czyŚledzićSportowca() {
        return czyŚledzić;
    }

    public void zgłośZjazd(Czas czas, Trasa trasa) {
        if (czyŚledzić) {
            System.out.println(czas + ": " + this + " rozpoczął zjazd przez " + trasa + ".");
        }
    }

    public void zgłosWjazd(Czas czas, Wyciąg wyciąg) {
        if (czyŚledzić) {
            System.out.println(czas + ": " + this + " ustawia się w kolejce do " + wyciąg + ".");
        }
    }

    public void ustawObecnyWęzeł(Węzeł nowyWęzeł) {
        obecnyWęzeł = nowyWęzeł;
    }

    public Węzeł pobierzObecnyWęzeł() {
        return obecnyWęzeł;
    }
}
