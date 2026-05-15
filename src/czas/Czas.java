package czas;

public class Czas {

    private static final int MINUTA = 60;
    private static final int GODZINA = 60 * MINUTA;
    private static final int DOBA = 24 * GODZINA;

    private final int sekundy; // sekundy od północy

    public Czas(int sekundy) throws UjemnyCzas {
        if (sekundy < 0) {
            throw new UjemnyCzas();
        }
        this.sekundy = sekundy % DOBA;
    }

    public Czas(String czasStr) throws UjemnyCzas {
        final String[] części = czasStr.split(":");
        final int godziny = Integer.parseInt(części[0]);
        final int minuty = Integer.parseInt(części[1]);
        final int sekundy = Integer.parseInt(części[2]);

        if (godziny < 0 || minuty < 0 || sekundy < 0) {
            throw new UjemnyCzas();
        }

        this.sekundy = (godziny * GODZINA + minuty * MINUTA + sekundy) % DOBA;
    }

    public Czas dodajSekundy(int ile) throws UjemnyCzas {
        if (this.sekundy + ile < 0) {
            throw new UjemnyCzas();
        }

        final int nowyCzas = (this.sekundy + ile) % DOBA;
        return new Czas(nowyCzas);
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

    @Override
    public String toString() {
        final int hh = sekundy / GODZINA;
        final int resztaHH = sekundy % GODZINA;
        final int mm = resztaHH / MINUTA;
        final int ss = resztaHH % MINUTA;

        return składowaCzasu(hh) + ":" + składowaCzasu(mm) + ":" + składowaCzasu(ss);
    }

}
