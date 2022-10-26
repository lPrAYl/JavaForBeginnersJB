package tictactoe.player.move_strategy.ai_move;

import tictactoe.game.Game;
import tictactoe.game.GameField;
import tictactoe.player.Move;
import tictactoe.player.MoveStrategy;
import tictactoe.player.PlayerType;

public class AI_Hard implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Making move level \"hard\"");
        GameField field = Game.getField();
        char currentPlayer = Game.getCurrentPlayer();
        Minimax minimax = new Minimax(currentPlayer);
        Move move = minimax.findBestMove(Game.getField().getCurrentState());
        if (move.x == -1) {
            if(field.isEmpty(2,2)) {
                move.x = 2;
                move.y = 2;
            }else {
                move = Game.getRandomXAndYNotOccupied();
            }
        }
        field.set(move.x, move.y, currentPlayer);
    }

    static class Minimax {

        char player = PlayerType.X.symbol, opponent = PlayerType.O.symbol;

        public Minimax(char player) {
            this.player = player;
            this.opponent = player == PlayerType.X.symbol ? PlayerType.O.symbol : PlayerType.X.symbol;
        }


        // This function returns true if there are moves
        // remaining on the board. It returns false if
        // there are no moves left to play.
        Boolean isMovesLeft(char[][] board) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] == PlayerType.EMPTY.symbol)
                        return true;
            return false;
        }

        int evaluate(char[][] board) {
            // Checking for Rows for player or opponent victory.
            for (int row = 0; row < GameField.getSIZE(); row++) {
                if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                    if (board[row][0] == player)
                        return +10;
                    else if (board[row][0] == opponent)
                        return -10;
                }
            }

            // Checking for Columns for player or opponent victory.
            for (int col = 0; col < GameField.getSIZE(); col++) {
                if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                    if (board[0][col] == player)
                        return +10;
                    else if (board[0][col] == opponent)
                        return -10;
                }
            }

            // Checking for Diagonals for player or opponent victory.
            boolean playerForwardDiagonal = true, playerBackwardDiagonal = true,
                    opponentForwardDiagonal = true, opponentBackwardDiagonal = true;

            for (int r1 = 0; r1 < GameField.getSIZE(); r1++) {
                int c2 = GameField.getSIZE() - r1 - 1;
                playerForwardDiagonal &= board[r1][r1] == player;
                playerBackwardDiagonal &= board[r1][c2] == player;
                opponentForwardDiagonal &= board[r1][r1] == opponent;
                opponentBackwardDiagonal &= board[r1][c2] == opponent;
            }
            if (playerForwardDiagonal || playerBackwardDiagonal)
                return +10;
            if (opponentForwardDiagonal || opponentBackwardDiagonal)
                return -10;


            // Else if none of them have won then return 0
            return 0;
        }


        // This is the minimax function. It considers all
        // the possible ways the game can go and returns
        // the value of the board
        int minimax(char[][] board, int depth, Boolean isMax) {
            int score = evaluate(board);

            // If Maximizer has won the game
            // return his/her evaluated score
            if (score == 10)
                return score;

            // If Minimizer has won the game
            // return his/her evaluated score
            if (score == -10)
                return score;

            // If there are no more moves and
            // no winner then it is a tie
            if (!isMovesLeft(board))
                return 0;

            // If this maximizer's move
            int best;
            if (isMax) {
                best = -1000;

                // Traverse all cells
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // Check if cell is empty
                        if (board[i][j] == PlayerType.EMPTY.symbol) {
                            // Make the move
                            board[i][j] = player;

                            // Call minimax recursively and choose
                            // the maximum value
                            best = Math.max(best, minimax(board, depth + 1, false));

                            // Undo the move
                            board[i][j] = PlayerType.EMPTY.symbol;
                        }
                    }
                }
            }

            // If this minimizer's move
            else {
                best = 1000;

                // Traverse all cells
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // Check if cell is empty
                        if (board[i][j] == PlayerType.EMPTY.symbol) {
                            // Make the move
                            board[i][j] = opponent;

                            // Call minimax recursively and choose
                            // the minimum value
                            best = Math.min(best, minimax(board, depth + 1, true));

                            // Undo the move
                            board[i][j] = PlayerType.EMPTY.symbol;
                        }
                    }
                }
            }
            return best;
        }

        // This will return the best possible
        // move for the player
        public Move findBestMove(char[][] board) {
            int bestVal = -1000;
            Move bestMove = new Move(-1, -1);

            // Traverse all cells, evaluate minimax function
            // for all empty cells. And return the cell
            // with optimal value.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == PlayerType.EMPTY.symbol) {
                        // Make the move
                        board[i][j] = player;

                        // compute evaluation function for this move.
                        int moveVal = minimax(board, 0, false);

                        // Undo the move
                        board[i][j] = PlayerType.EMPTY.symbol;

                        // If the value of the current move is
                        // more than the best value, then update
                        // best/
                        if (moveVal > bestVal) {
                            bestMove.x = i;
                            bestMove.y = j;
                            bestVal = moveVal;
                        }
                    }
                }
            }

//        System.out.printf("The value of the best Move " + "is : %d\n\n", bestVal);

            return bestMove;
        }


    }
}

