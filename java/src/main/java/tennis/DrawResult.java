package tennis;

class DrawResult extends Result {

    public DrawResult(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    String getScoreAsText() {
        return player1.getDrawScoreAsText();
    }

}
