package tictactoe.player;

public enum PlayerType {
    EMPTY(' '),
    X('X'),
    O('O');

    public final char symbol;

    PlayerType(char cellSymbol) {
        this.symbol = cellSymbol;
    }
}