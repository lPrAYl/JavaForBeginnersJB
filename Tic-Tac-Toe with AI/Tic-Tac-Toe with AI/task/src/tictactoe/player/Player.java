package tictactoe.player;

public class Player {
    MoveStrategy moveStrategy;
    PlayerType playerSymbol;

    public Player(MoveStrategy moveStrategy, PlayerType playerSymbol) {
        this.moveStrategy = moveStrategy;
        this.playerSymbol = playerSymbol;
    }

    public void move() {
        this.moveStrategy.move();
    }

    public void changeMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void changePlayerSymbol() {
        this.playerSymbol = playerSymbol == PlayerType.O ? PlayerType.X : PlayerType.O;
    }
}
