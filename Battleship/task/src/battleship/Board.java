package battleship;

public class Board {
    private char field[][];

    Board() {
        boardInit();
    }

    private void boardInit() {
        this.field = new char[11][11];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 && j == 0) {
                    this.field[i][j] = ' ';
                } else if (i == 0 && j == 10) {
                    this.field[i][j] = '1';
                } else if (i == 0) {
                    this.field[i][j] = (char) (j + '0');
                } else if (j == 0) {
                    this.field[i][j] = (char) (i - 1 + 'A');
                } else {
                    this.field[i][j] = '~';
                }
            }
        }
        this.printBoard();
    }

    public char getCeil(int i, int j) {
        return this.field[i][j];
    }

    public void setCeil(int i, int j, char ch) {
        this.field[i][j] = ch;
    }

    public void printBoard() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 && j == 10) {
                    System.out.println(this.field[i][j] + "0");
                } else if (j == 10) {
                    System.out.println(this.field[i][j]);
                } else {
                    System.out.print(this.field[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    public void printBoardWithFogOfWar() {
        char[][] copyField = new char[11][11];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                copyField[i][j] = field[i][j];
            }
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (copyField[i][j] == 'O') {
                    copyField[i][j] = '~';
                }
                if (i == 0 && j == 10) {
                    System.out.println(copyField[i][j] + "0");
                } else if (j == 10) {
                    System.out.println(copyField[i][j]);
                } else {
                    System.out.print(copyField[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

}
