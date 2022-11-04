package battleship;

import java.util.Scanner;

import static battleship.Gracz.gracz1;
import static battleship.Gracz.gracz2;

public class graj {
    private static final int RZAD = 10;
    private static final int KOLUMNA = 10;

    public void Graj() {
        boolean gra = true;
        System.out.println(gracz1.getImie() + " " + gracz1.getNumer() + ", połóż statki na polu bitwy");
        gracz1.polenr1 = new PoleBitwy(RZAD, KOLUMNA);  // Tworzenie pola biwy gracza 1
        gracz1.polenr1.Organizacja();   // Stawianie statków na polu bitwy gracza 1
        gracz1.polenr1.mglaWojny = new MglaWojny(RZAD, KOLUMNA); // tworzenie mgły wojny
        Ruch(); // Wciśnij enter by podać grę dalej
        System.out.println(gracz2.getImie() + " " + gracz2.getNumer() + ", połóż statki na polu bitwy");
        gracz2.polenr2 = new PoleBitwy(RZAD, KOLUMNA); //Tworzenie pola biwy gracza 2
        gracz2.polenr2.Organizacja(); // Stawianie statków na polu bitwy gracza 2
        gracz2.polenr2.mglaWojny = new MglaWojny(RZAD, KOLUMNA); // tworzenie mgły wojny

        Ruch();
        while (gra) {
            gracz2.polenr2.mglaWojny.pokazMgleWojny(); // Pokazanie mgly wojny gracza 2
            System.out.println("---------------------"); // Oddzielenie map
            gracz1.polenr1.pokazMape();  // Pokaz mape gracza 1
            System.out.println(gracz1.getImie() + " " + gracz1.getNumer() + ", twoja tura");
            gra = gracz2.polenr2.Wojna();  // metoda graj która jest odpowiedzialna za strzał i sprawdzanie czy koniec gry
            if (!gra) {
                break;
            }
            Ruch();
            gracz1.polenr1.mglaWojny.pokazMgleWojny();
            System.out.println("---------------------");
            gracz2.polenr2.pokazMape();
            System.out.println(gracz2.getImie() + " " + gracz2.getNumer() + ", twoja tura");
            gra = gracz1.polenr1.Wojna();
            if (!gra) {
                break;
            }
            Ruch();
        }
    }
    public void Ruch() { // Przekazanie tury
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wciśnij enter i podaj ture do następnego gracza");
        scanner.nextLine();
    }
}
