package tictactoe.player.move_strategy;

import tictactoe.game.Game;
import tictactoe.game.GameField;
import tictactoe.player.Move;
import tictactoe.player.MoveStrategy;

import java.util.Scanner;

public class HumanMove implements MoveStrategy {

    @Override
    public void move() {
        GameField field = Game.getField();
        Move move = readCoordinates(field);
        char currentPlayer = Game.getCurrentPlayer();
        field.set(move.x, move.y, currentPlayer);
    }

    private static Move readCoordinates(GameField field) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            Scanner reader = new Scanner(System.in);
            String[] coordinates = reader.nextLine().split("\\s+");
            if (coordinates.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }
            boolean valid = true;
            for (String coordinate : coordinates) {
                if (!coordinate.matches("\\d")) {
                    System.out.println("You should enter numbers!");
                    valid = false;
                    break;
                }
                if (!coordinate.matches("[1-3]")) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                continue;
            }
            int x = Integer.parseInt(coordinates[0]) - 1;
            int y = Integer.parseInt(coordinates[1]) - 1;
            if (!field.isEmpty(x, y)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            return new Move(x, y);
        }
    }
}
