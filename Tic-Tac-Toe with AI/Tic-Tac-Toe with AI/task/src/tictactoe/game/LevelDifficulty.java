package tictactoe.game;

public enum LevelDifficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String difficulty;

    LevelDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLevelDifficulty() {
        return difficulty;
    }
}