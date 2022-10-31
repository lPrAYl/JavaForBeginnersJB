package battleship;

public enum Ships {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private String typeOfShip;
    private int length;

    Ships(String ship, int length) {
        this.typeOfShip = ship;
        this.length = length;
    }

    public String getTypeOfShip() {
        return typeOfShip;
    }

    public int getLength() {
        return length;
    }
}
