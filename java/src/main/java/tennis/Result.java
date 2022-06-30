package tennis;

abstract class Result {
    Player player1;
    Player player2;

    Result(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    abstract String getScoreAsText();
}
