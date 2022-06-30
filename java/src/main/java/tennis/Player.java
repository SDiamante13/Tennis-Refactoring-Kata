package tennis;

class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    boolean isTiedWith(Player otherPlayer) {
        return score == otherPlayer.score;
    }

    public void addPoint() {
        score++;
    }

    public int getScore() {
        return score;
    }
}
