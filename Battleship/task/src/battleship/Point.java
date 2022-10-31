package battleship;

public class Point {
    public int x;
    public int y;

    Point(char x, String y) {
        this.x = x - 64;
        this.y = Integer.parseInt(y);
    }
}
