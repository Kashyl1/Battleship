package battleship;

public class MglaWojny {
    final int rzad;
    final int kolumna;
    final char[][] mgla;
    public MglaWojny(int rzad, int kolumna) {
        this.rzad = rzad;
        this.kolumna = kolumna;
        this.mgla = new char[rzad][kolumna];
        for (int i = 0; i < rzad; i++) {       // Tworzenie mapy
            for (int j = 0; j < kolumna; j++) {
                mgla[i][j] = '~';
            }
        }
    }
    public void pokazMgleWojny() {
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
                System.out.print(mgla[i][j] + " "); // Drukowanie " "
            }
            System.out.println();
        }
        System.out.println();
    }
}
