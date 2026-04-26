package kolejka_zdarzen;

class ElementListy {

    private final Zdarzenie zdarzenie;
    private ElementListy następny;

    protected ElementListy(Zdarzenie zdarzenie) {
        this.zdarzenie = zdarzenie;
        this.następny = null;
    }

    protected Zdarzenie pobierzZdarzenie() {
        return zdarzenie;
    }

    protected ElementListy pobierzNastępny() {
        return następny;
    }

    protected void ustawNastępny(ElementListy element) {
        następny = element;
    }




}
