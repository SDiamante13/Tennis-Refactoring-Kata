package tennis;

class WinResult extends Result {

    public WinResult(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    String getScoreAsText() {
        return player1.isAheadOf(player2) ? "Win for player1" : "Win for player2";
    }
}
