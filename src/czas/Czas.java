package czas;

public class Czas {

    private int godzina;
    private int minuta;
    private int sekunda;

    public Czas(int godzina, int minuta, int sekunda) {
        this.godzina = godzina;
        this.minuta = minuta;
        this.sekunda = sekunda;
    }

    public Czas(int godzina, int minuta) {
        this(godzina, minuta, 0);
    }

    public Czas(int godzina) {
        this(godzina, 0, 0);
    }

}
