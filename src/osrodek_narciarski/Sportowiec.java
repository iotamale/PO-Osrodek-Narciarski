package osrodek_narciarski;

import czas.Czas;

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

    public double łącznaAtrakcyjnośćTrasy(Trasa trasa) {
        final double d = trasa.atrakcyjnośćDTrasy(poziomZaawansowania);
        final double w = trasa.atrakcyjnośćWTrasy();

        return wagaD * d + wagaW * w;
    }
}
