package tictactoe.game;

import tictactoe.player.PlayerType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameField {

    private static int SIZE = 3;
    private final char[][] board;

    public GameField() {
        board = new char[SIZE][SIZE];
        init();
    }

    public static int getSIZE() {
        return SIZE;
    }
    public static void setSIZE(int SIZE) {
        GameField.SIZE = SIZE;
    }
    public void init() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = PlayerType.EMPTY.symbol;
            }
        }
    }

    public char get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, char val) {
        board[x][y] = val;
    }

    public void unSet(int x, int y) {
        board[x][y] = PlayerType.EMPTY.symbol;
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y] == PlayerType.EMPTY.symbol;
    }

    public int count(char cellValue) {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == cellValue) {
                    count++;
                }
            }
        }
        return count;
    }

    public int count(PlayerType PLAYERTYPE) {
        return count(PLAYERTYPE.symbol);
    }

    @Override
    public String toString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream);
        out.println("-".repeat(9));
        for (int row = 0; row < SIZE; row++) {
            out.print("| ");
            for (int col = 0; col < SIZE; col++) {
                out.printf("%c ", board[row][col]);
            }
            out.println("|");
        }
        out.println("-".repeat(9));
        return byteArrayOutputStream.toString();
    }

    public char[][] getCurrentState() {
        return board;
    }
}
