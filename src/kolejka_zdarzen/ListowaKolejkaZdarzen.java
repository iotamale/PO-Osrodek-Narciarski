package kolejka_zdarzen;

public class ListowaKolejkaZdarzen implements KolejkaZdarzeń {

    private ElementListy głowa;

    public ListowaKolejkaZdarzen() {
        this.głowa = null;
    }

    private boolean czyMniejszy(ElementListy następny, Zdarzenie noweZdarzenie) {
        return następny.pobierzZdarzenie().pobierzCzas() <= noweZdarzenie.pobierzCzas();
    }

    @Override
    public void dodaj(Zdarzenie noweZdarzenie) {
        final ElementListy nowyElement = new ElementListy(noweZdarzenie);

        if (głowa == null || głowa.pobierzZdarzenie().pobierzCzas() > noweZdarzenie.pobierzCzas()) {
            nowyElement.ustawNastępny(głowa);
            głowa = nowyElement;
            return;
        }

        ElementListy aktualny = głowa;
        while (aktualny.pobierzNastępny() != null && czyMniejszy(aktualny.pobierzNastępny(), noweZdarzenie)) {
            aktualny = aktualny.pobierzNastępny();
        }

        nowyElement.ustawNastępny(aktualny.pobierzNastępny());
        aktualny.ustawNastępny(nowyElement);
    }

    @Override
    public Zdarzenie pobierzPierwsze() {
        if (czyPusta()) {
            throw new IllegalStateException("Próba pobrania zdarzenia z pustej kolejki!");
        }

        final Zdarzenie pierwsze = głowa.pobierzZdarzenie();
        głowa = głowa.pobierzNastępny();

        return pierwsze;
    }

    @Override
    public boolean czyPusta() {
        return głowa == null;
    }

}
