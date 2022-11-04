package battleship;

public class Gracz {
    private String imie;
    private int numer;
    public Gracz (String imie, int numer) { // Konstruktor
        this.imie = imie;
        this.numer = numer;
    }

    public int getNumer() { //gettery
        return numer;
    }

    public String getImie() {
        return imie;
    }
    PoleBitwy polenr1 = new PoleBitwy(10, 10);// Pole bitwy nr 1
    PoleBitwy polenr2 = new PoleBitwy(10, 10); // Pole bitwy nr 2
    static Gracz gracz1 = new Gracz("Gracz", 1); // Gracz 1
    static Gracz gracz2 = new Gracz("Gracz", 2); // Gracz 2
}
