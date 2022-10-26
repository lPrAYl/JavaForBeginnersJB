package tictactoe;

import tictactoe.player.move_strategy.ai_move.AI_Easy;
import tictactoe.player.move_strategy.ai_move.AI_Hard;
import tictactoe.player.move_strategy.ai_move.AI_Medium;
import tictactoe.player.PlayerType;
import tictactoe.game.GameState;
import tictactoe.game.Game;
import tictactoe.player.move_strategy.HumanMove;
import tictactoe.player.MoveStrategy;
import tictactoe.player.Player;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final Scanner reader = new Scanner(System.in);
    private static final Game game = new Game();
    private static final HashMap<String, MoveStrategy> strategyMap = new HashMap<>() {{
        put("user", new HumanMove());
        put("easy", new AI_Easy());
        put("medium", new AI_Medium());
        put("hard", new AI_Hard());
    }};

    public static void main(String[] args) {

        while (true) {
            String[] input = readCommands();
            if (input[0].equals("exit"))
                break;
            game.newGame();
            System.out.print(Game.field);
            Player playerX = new Player(strategyMap.get(input[1]), PlayerType.X);
            Player playerO = new Player(strategyMap.get(input[2]), PlayerType.O);
            int count = 0, i = 0;
            while (count < 9) {
                i = count % 2;
                if (i == 0)
                    playerX.move();
                else
                    playerO.move();
                System.out.print(Game.field);
                if (game.currentState() != GameState.GAME_NOT_FINISHED) {
                    System.out.println(game.currentState());
                    break;
                }
                count++;
            }
        }

    }


    private static String[] readCommands() {

        while (true) {
            System.out.print("Input command: ");
            String[] args = reader.nextLine().split("\\s+");
            if (args.length == 1 && args[0].equals("exit"))
                return new String[]{"exit"};

            if (args.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }
            boolean valid = true;
            if (!args[0].equals("start")) valid = false;


            if (!args[1].equals("easy") && !args[1].equals("medium") && !args[1].equals("hard")
                    && !args[1].equals("user")) valid = false;


            if (!args[2].equals("easy") && !args[2].equals("medium") && !args[2].equals("hard")
                    && !args[2].equals("user")) valid = false;

            if (!valid) {
                System.out.println("Bad parameters!");
                continue;
            }

            return args;
        }
    }
}
