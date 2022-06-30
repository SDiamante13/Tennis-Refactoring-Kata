package tennis;

class OngoingResult extends Result {
    public OngoingResult(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    String getScoreAsText() {
        return player1.getOngoingScoreAsText() + "-" + player2.getOngoingScoreAsText();
    }

}
