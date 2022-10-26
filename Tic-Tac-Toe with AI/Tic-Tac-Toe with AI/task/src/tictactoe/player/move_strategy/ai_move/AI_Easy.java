package tictactoe.player.move_strategy.ai_move;

import tictactoe.game.Game;
import tictactoe.game.GameField;
import tictactoe.player.Move;
import tictactoe.player.MoveStrategy;

public class AI_Easy implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Making move level \"easy\"");
        GameField field = Game.getField();
        char currentPlayer = Game.getCurrentPlayer();
        Move move = Game.getRandomXAndYNotOccupied();
        field.set(move.x, move.y, currentPlayer);
    }
}
