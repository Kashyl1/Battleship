package battleship;

public class Koordynaty  {
     int gora = -1;      // Tworzenie instancji
     int prawo = -1;
     int dol = -1;
     int lewo = -1;

    Koordynaty(String s) {  // Konstruktor
        String[] krawedz = s.split(" ");
        int gora = krawedz[0].charAt(0) - 65;
        int prawo = Integer.parseInt(krawedz[0].substring(1)) - 1;
        // Zamiana char np A3: A=GORA=65(ASCII CONVERTER) GORA-65 = 0, 3=PRAWO PRAWO-1=2 Czyli 1 koordynaty to [0][2]
        int dol = krawedz[1].charAt(0) - 65; // C3
        int lewo = Integer.parseInt(krawedz[1].substring(1)) - 1; // ANALOGICZNIE
        if (gora > dol) {  // Jezeli są współrzędne C6 i A6 to C>A więc zamień miejscami C z A
            int a = gora;
            gora = dol;
            dol = a;
        }
        if (lewo > prawo) { // Jezeli są współrzędne A6 i A2 to 6>2 więc zamień miejscami 6 z 2
            int a = lewo;
            lewo = prawo;
            prawo = a;
        }
        if (0 <= gora && gora < 10 && 0 <= prawo && prawo < 10 && dol < 10 && 0 <= lewo && gora == dol ^ lewo == prawo) {
            // Indeks[ 0 <= x < 10 ]
            this.gora = gora;
            this.dol = dol;
            this.prawo = prawo;
            this.lewo = lewo;
        }
    }

    boolean blad() {
        return gora != -1 && prawo != -1 && dol != -1 && lewo != -1;
    }
    int dlugosc() { // Sprawdza dlugosc statku np A5 A2 jezeli gora==dol (A=A) to 5-2+1 = 4
        if (gora == dol) {
            return prawo - lewo + 1;
        }
        else {
            return dol - gora + 1;
        }
    }
    public int getGora() { //GETTERY
        return gora;
    }
    public int getDol() {
        return dol;
    }
    public int getPrawo() {
        return prawo;
    }
    public int getLewo() {
        return lewo;
    }
}
