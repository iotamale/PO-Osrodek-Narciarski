package kolejka_zdarzen;

import czas.Czas;

public class ListowaKolejkaZdarzen implements KolejkaZdarzeń {

    private ElementListy głowa;

    public ListowaKolejkaZdarzen() {
        this.głowa = null;
    }

    // TODO czas.czyWcześniej
    private boolean czyMniejszy(ElementListy następny, Zdarzenie noweZdarzenie) {
        final Czas czas = następny.pobierzZdarzenie().pobierzCzas();
        return czas.czyWcześniej(noweZdarzenie.pobierzCzas());
    }

    @Override
    public void dodaj(Zdarzenie noweZdarzenie) {
        final ElementListy nowyElement = new ElementListy(noweZdarzenie);

        // TODO czas.czyWcześniejLubRówno
        if (głowa == null || głowa.pobierzZdarzenie().pobierzCzas().czyWcześniejLubRówno(noweZdarzenie.pobierzCzas())) {
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
