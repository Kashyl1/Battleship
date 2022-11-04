package battleship;

import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PoleBitwy {
    final int rzad;    // Instancje
    final int kolumna;
    final char[][] pole;

    MglaWojny mglaWojny = new MglaWojny(10, 10);

    public PoleBitwy(int rzad, int kolumna) {  //Konstruktor
        this.rzad = rzad;
        this.kolumna = kolumna;
        this.pole = new char[rzad][kolumna];
        for (int i = 0; i < rzad; i++) {       // Tworzenie mapy
            for (int j = 0; j < kolumna; j++) {
                pole[i][j] = '~';
            }
        }
    }

    public void pokazMape() { // Pokazanie mapy
        System.out.print("  ");
        for (int i = 1; i <= rzad; i++) {
            System.out.print(i + " ");  // Drukowanie 1 2 3.. 10
        }
        System.out.println();
        char A = 'A';
        for (int i = 0; i < rzad; i++) {
            System.out.print(A + " ");  // Drukowanie A B C .. J
            A++;
            for (int j = 0; j < kolumna; j++) {
                System.out.print(pole[i][j] + " "); // Drukowanie " "
            }
            System.out.println();
        }
    }

    public void Organizacja() {
        Koordynaty koordynaty;
        pokazMape();
        for (Statek statki : Statek.values()) {  // Petla foreach enum Statek
            System.out.println("Wprowadź koordynaty statku: " + statki.nazwa + " o rozmiarze(" + statki.rozmiar + "):");
            // Wypisywanie statków + ich nazwy
            while (true) {
                koordynaty = new Koordynaty(scanner.nextLine());  // Podawanie koordynatów
                if (!koordynaty.blad()) {  // Szukanie złej lokalizacji
                    System.out.println("Error! Zła lokalizacja statku!");
                    continue;
                }
                if (koordynaty.dlugosc() != statki.rozmiar) {  // Porównywaniu długości koordynatów z ich długością
                    System.out.println("Error! Zła długość statku!");
                    continue;
                }
                if (!dokuj(koordynaty, 'O')) {  // Szukanie czy statek nie jest za blisko siebie
                    System.out.println("Error! Położyłeś statek za blisko drugiego ");
                    continue;
                }
                pokazMape();
                break;
            }
        }
    }

    boolean dokuj(Koordynaty koordynaty, char a) {

        for (int x = max(koordynaty.getGora() - 1, 0); // petla for + sprawdzanie czy gora nie wychodzi za indeks tablicy
             x <= min(koordynaty.getDol() + 1, rzad - 1); x++) {
            for (int y = max(koordynaty.getLewo() - 1, 0);
                 y <= min(koordynaty.getPrawo() + 1, kolumna - 1); y++) {
                if (pole[x][y] != '~') {  // Jezeli pole jest różne od ~ to miejsce zajęte
                    return false;
                }
            }
        }
        for (int x = koordynaty.getGora(); x <= koordynaty.getDol(); x++) {  // Pętla for + drukowanie pozycji statku
            for (int y = koordynaty.getLewo(); y <= koordynaty.getPrawo(); y++) {
                pole[x][y] = a;
            }
        }
        return true;
    }
    public int[] lokalizacjaStrzalu(String strzelaj) { //
        int strzalX = strzelaj.charAt(0) - 65; // Zmienia współrzędne np A(65) na 0
        int strzalY = Integer.parseInt(strzelaj.substring(1)) - 1; // Zmienia 1 na indeks 0
        System.out.println();
        return new int[]{strzalX, strzalY};  // Dodaje do tablicy np A1 to [0] = A-65 czyli 0, [1] = 1-1 czyli 0
    }
    public boolean sprawdzStrzal(int[] uwazaj) {  // Sprawdza czy strzał nie jest poza mapą
        if (uwazaj[0] < 0 || uwazaj[0] > 9 || uwazaj[1] < 0 || uwazaj[1] > 9) {
            System.out.println("Strzeliłeś poza możliwą mape!");
            return false;
        }
        if (pole[uwazaj[0]][uwazaj[1]] == 'X'|| pole[uwazaj[0]][uwazaj[1]] == 'M') {
            System.out.println("Tu już strzalałeś! Wybierz inną pozycje");
            return false;
        }
        return true;
    }
    public boolean czyTrafiles(int[] statek) { // Sprawdza czy strzał jest trafiony
        if (pole[statek[0]][statek[1]] == 'O' || pole[statek[0]][statek[1]] == 'X') {
            pole[statek[0]][statek[1]] = 'X';
            mglaWojny.mgla[statek[0]][statek[1]] = 'X';
            return true;
        }
        else {
            pole[statek[0]][statek[1]] = 'M';
            mglaWojny.mgla[statek[0]][statek[1]] = 'M';
            return false;
        }
    }
    public boolean sprawdzMape() { // Sprawdza czy na mapie zostały jeszcze jakieś statki
        for (int i = 0; i < rzad; i++) {
            for (int j = 0; j < kolumna; j++) {
                if (pole[i][j] == 'O') {
                    return false;
                }
            }
        } return true;
    }

   public boolean zatopionyStatek(int[] statek) { // Sprawdza czy po strzale statek został zatopiony
        try {
                if (pole[statek[0] - 1][statek[1]] == 'O' || pole[statek[0] + 1][statek[1]] == 'O'
                        || pole[statek[0]][statek[1] - 1] == 'O' || pole[statek[0]][statek[1] + 1] == 'O') {
                    return false;
                }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        } return true;
   }
    public boolean Wojna() { // Metoda w której oddaje strzał
        int[] lokalizacja;
        try {
            do {
                lokalizacja = lokalizacjaStrzalu(scanner.nextLine());
            } while (!sprawdzStrzal(lokalizacja));
          //  boolean trafTo = czyTrafiles(lokalizacja);
            if (czyTrafiles(lokalizacja)) {
                System.out.println("Trafiłeś w statek!");
                  if (!zatopionyStatek(lokalizacja)) {
                      System.out.println("Zatopiłeś statek!");
                      sprawdzMape();
                  }
            }

            else {
                System.out.println("Nie celnąłeś!");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Zły format!");
        }
        if (sprawdzMape()) {
            System.out.println("Zatopiłeś ostatni statek! Wygrałeś!");
            return false;
        }
        else {
            return true;

        }

    }

    final static Scanner scanner = new Scanner(System.in);
}

