package tictactoe.player.move_strategy.ai_move;

import tictactoe.game.Game;
import tictactoe.game.GameField;
import tictactoe.player.Move;
import tictactoe.player.MoveStrategy;
import tictactoe.player.PlayerType;

public class AI_Medium implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Making move level \"medium\"");
        GameField field = Game.getField();
        char currentPlayer = Game.getCurrentPlayer();
        Move move = Game.getEmptyCell(currentPlayer);
        if (move.x == -1) {
            if (currentPlayer == PlayerType.O.symbol)
                move = Game.getEmptyCell(PlayerType.X.symbol);
            else
                move = Game.getEmptyCell(PlayerType.O.symbol);

            if (move.x == -1) {
                move = Game.getRandomXAndYNotOccupied();
            }
        }
        field.set(move.x, move.y, currentPlayer);
    }
}
