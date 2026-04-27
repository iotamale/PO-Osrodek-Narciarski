package osrodek_narciarski;

import czas.Czas;
import symulacja.GeneratorLosowy;

public class Sportowiec {

    private final int poziomZaawansowania;
    private final Węzeł startowyWęzeł;
    private final Czas godzinaPrzyjazdu;

    private final double wagaD;
    private final double wagaW;
    private final double spontaniczność;
    private final boolean czyŚledzić;

    public Sportowiec(int poziomZaawansowania, Węzeł startowyWęzeł, Czas godzinaPrzyjazdu, double wagaD, double wagaW, double spontaniczność, boolean czyŚledzić) {
        this.poziomZaawansowania = poziomZaawansowania;
        this.startowyWęzeł = startowyWęzeł;
        this.godzinaPrzyjazdu = godzinaPrzyjazdu;
        this.wagaD = wagaD;
        this.wagaW = wagaW;
        this.spontaniczność = spontaniczność;
        this.czyŚledzić = czyŚledzić;
    }

    private double łącznaAtrakcyjnośćTrasy(Trasa trasa) {
        final double d = trasa.atrakcyjnośćDTrasy(poziomZaawansowania);
        final double w = trasa.atrakcyjnośćWTrasy();

        return wagaD * d + wagaW * w;
    }

    public Krawędź podejmijDecyzję(Węzeł obecnyWęzeł, GeneratorLosowy generator) {
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
}
