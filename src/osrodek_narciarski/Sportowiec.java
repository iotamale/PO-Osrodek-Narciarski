package osrodek_narciarski;

import czas.Czas;

public abstract class Sportowiec {

    private final int poziomZaawansowania;
    private final Węzeł startowyWęzeł;
    private final Czas godzinaPrzyjazdu;

    private final double wagaD;
    private final double wagaW;
    private final double epsilon;

    public Sportowiec(int poziomZaawansowania, Węzeł startowyWęzeł, Czas godzinaPrzyjazdu, double wagaD, double wagaW, double epsilon) {
        this.poziomZaawansowania = poziomZaawansowania;
        this.startowyWęzeł = startowyWęzeł;
        this.godzinaPrzyjazdu = godzinaPrzyjazdu;
        this.wagaD = wagaD;
        this.wagaW = wagaW;
        this.epsilon = epsilon;
    }

    public double łącznaAtrakcyjnośćTrasy(Trasa trasa) {
        final double d = trasa.atrakcyjnośćDTrasy(poziomZaawansowania);
        final double w = trasa.atrakcyjnośćWTrasy();

        return wagaD * d + wagaW * w;
    }
}
