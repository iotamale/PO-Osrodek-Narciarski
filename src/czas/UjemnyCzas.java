package czas;

public class UjemnyCzas extends RuntimeException {
    public UjemnyCzas() {
        super("Składowa/składowe czasu nie może być ujemna!");
    }

}
