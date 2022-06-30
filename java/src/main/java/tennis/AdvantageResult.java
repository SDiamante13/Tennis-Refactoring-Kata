package tennis;

class AdvantageResult extends Result {

    public AdvantageResult(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    String getScoreAsText() {
        return player1.isAheadOf(player2) ? "Advantage player1" : "Advantage player2";
    }

}
