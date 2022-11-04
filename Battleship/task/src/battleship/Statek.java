package battleship;

enum Statek {
    CARRIER("Aircraft Carrier", 5), // Wczytywanie statków + ich rozmiary
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    final String nazwa;
    final int rozmiar;


     Statek(String nazwa, int rozmiar) {  // Konstruktor
        this.nazwa = nazwa;
        this.rozmiar = rozmiar;
    }

}