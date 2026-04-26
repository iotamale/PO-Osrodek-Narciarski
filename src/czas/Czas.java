package czas;

public class Czas {

    private static final int MINUTA = 60;
    private static final int GODZINA = 60 * MINUTA;

    private final int sekundy; // sekundy od północy

    public Czas(int sekundy) {
        this.sekundy = sekundy;
    }

    public Czas(String czasStr) {
        final String[] części = czasStr.split(":");
        final int godziny = Integer.parseInt(części[0]);
        final int minuty = Integer.parseInt(części[1]);
        final int sekundy = Integer.parseInt(części[2]);

        this.sekundy = godziny * GODZINA + minuty * MINUTA + sekundy;
    }

    public Czas dodajSekundy(int ile) {
        return new Czas(this.sekundy + ile);
    }

    public boolean czyWcześniej(Czas inny) {
        return this.sekundy < inny.sekundy;
    }

    public boolean czyWcześniejLubRówno(Czas inny) {
        return this.sekundy <= inny.sekundy;
    }

    private String składowaCzasu(int liczba) {
        if (liczba < 10) {
            return "0" + String.valueOf(liczba);
        }

        return String.valueOf(liczba);
    }

    // TODO pewnie można usunąć
    public int pobierzSekundy() {
        return sekundy;
    }

    @Override
    public String toString() {
        final int hh = sekundy / GODZINA;
        final int resztaHH = sekundy % GODZINA;
        final int mm = resztaHH / MINUTA;
        final int ss = resztaHH % MINUTA;

        return składowaCzasu(hh) + ":" + składowaCzasu(mm) + ":" + składowaCzasu(ss);
    }

}
