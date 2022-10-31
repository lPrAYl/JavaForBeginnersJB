package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private Scanner scanner;
    private Ships ships;
    Game() {
        this.playerOne = new Player("Player 1");
        this.playerTwo = new Player("Player 2");
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean gameNotEnd() {
        return !playerOne.isWinner(playerTwo) && !playerTwo.isWinner(playerOne);
    }
    public void run() {
        playerOne.arangementOfShips();
        Game.promptEnterKey();
        playerTwo.arangementOfShips();
        Game.promptEnterKey();

        while (gameNotEnd()) {
            playerOne.move(playerTwo);
            Game.promptEnterKey();
            playerTwo.move(playerOne);
            Game.promptEnterKey();
        }

    }
}
