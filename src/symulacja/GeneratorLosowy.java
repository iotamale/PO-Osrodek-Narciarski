package symulacja;

import java.util.Random;

public class GeneratorLosowy {

    private final Random generator;

    public GeneratorLosowy() {
        this.generator = new Random();
    }

    public int losujInt(int a, int b) {
        return generator.nextInt(a, b);
    }

    public double losujDouble(double a, double b) {
        return generator.nextDouble(a, b);
    }

    // prawdopodobieństwo z zakresu [0, 1]
    public boolean czyZajdzieZdarzenie(double prawdopodobieństwo) {
        return generator.nextDouble() < prawdopodobieństwo;
    }

}
