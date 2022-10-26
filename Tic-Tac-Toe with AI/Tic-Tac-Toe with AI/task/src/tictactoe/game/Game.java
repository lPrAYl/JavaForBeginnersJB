package tictactoe.game;

import tictactoe.player.Move;
import tictactoe.player.PlayerType;

import java.util.Random;

public class Game {
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';

    public static GameField getField() {
        return field;
    }

    public static final GameField field = new GameField();


    public void newGame() {
        field.init();
    }


    public static Move getEmptyCell(char player) {

        for (int i = 0; i < GameField.getSIZE(); i++) {
            if (field.get(i, 0) == player && field.get(i, 1) == player && field.isEmpty(i, 2))
                return new Move(i, 2);
            if (field.get(i, 0) == player && field.get(i, 2) == player && field.isEmpty(i, 1))
                return new Move(i, 1);
            if (field.get(i, 1) == player && field.get(i, 2) == player && field.isEmpty(i, 0))
                return new Move(i, 0);
            if (field.get(0, i) == player && field.get(1, i) == player && field.isEmpty(2, i))
                return new Move(2, i);
            if (field.get(0, i) == player && field.get(2, i) == player && field.isEmpty(1, i))
                return new Move(1, i);
            if (field.get(1, i) == player && field.get(2, i) == player && field.isEmpty(0, i))
                return new Move(0, i);
            
            if (field.get(0, 0) == player && field.get(1, 1) == player && field.isEmpty(2, 2))
                return new Move(2, 2);
            if (field.get(0, 0) == player && field.get(2, 2) == player && field.isEmpty(1, 1))
                return new Move(1, 1);
            if (field.get(1, 1) == player && field.get(2, 2) == player && field.isEmpty(0, 0))
                return new Move(0, 0);
            if (field.get(0, 2) == player && field.get(1, 1) == player && field.isEmpty(2, 0))
                return new Move(2, 0);
            if (field.get(0, 2) == player && field.get(2, 0) == player && field.isEmpty(1, 1))
                return new Move(1, 1);
            if (field.get(1, 1) == player && field.get(2, 0) == player && field.isEmpty(0, 2))
                return new Move(0, 2);

        }
        return new Move(-1, -1);
    }

    public static Move getRandomXAndYNotOccupied() {
        Random rand = new Random();
        int x = rand.nextInt(3), y = rand.nextInt(3);

        while (!field.isEmpty(x, y)) {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        }
        return new Move(x, y);
    }


    public static char getCurrentPlayer() {
        int countX = field.count(PLAYER_X);
        int countO = field.count(PLAYER_O);
        if (countX == countO) {
            return PLAYER_X;
        }
        if (countX > countO) {
            return PLAYER_O;
        }
        return ' ';
    }

    public GameState currentState() {
        boolean xWins = checkWinner(PLAYER_X);
        boolean oWins = checkWinner(PLAYER_O);
        if (xWins) {
            return GameState.X_WINS;
        }
        if (oWins) {
            return GameState.O_WINS;
        }
        int countEmpty = field.count(PlayerType.EMPTY);
        if (countEmpty == 0) {
            return GameState.DRAW;
        }
        return GameState.GAME_NOT_FINISHED;
    }

    private boolean checkWinner(char player) {
        return checkRows(player) ||
                checkColumns(player) ||
                checkDiagonals(player);
    }

    private boolean checkRows(char player) {
        boolean check = false;
        for (int x = 0; x < GameField.getSIZE(); x++) {
            boolean checkRow = true;
            for (int y = 0; y < GameField.getSIZE(); y++) {
                checkRow &= field.get(x, y) == player;
            }
            check |= checkRow;
        }
        return check;
    }

    private boolean checkColumns(char player) {
        boolean check = false;
        for (int y = 0; y < GameField.getSIZE(); y++) {
            boolean checkCol = true;
            for (int x = 0; x < GameField.getSIZE(); x++) {
                checkCol &= field.get(x, y) == player;
            }
            check |= checkCol;
        }
        return check;
    }

    private boolean checkDiagonals(char player) {
        boolean check1 = true;
        boolean check2 = true;
        for (int r1 = 0; r1 < GameField.getSIZE(); r1++) {
            int c2 = GameField.getSIZE() - r1 - 1;
            check1 &= field.get(r1, r1) == player;
            check2 &= field.get(r1, c2) == player;
        }
        return check1 || check2;
    }

}
