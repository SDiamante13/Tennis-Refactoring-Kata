package tennis;

class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void addPoint() {
        score++;
    }

    boolean hasScoreGreaterThanThree() {
        return score > 3;
    }

    String getDrawScoreAsText() {
        // TODO: looks like a map could work here
        switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            default:
                return "Deuce";
        }
    }

    String getOngoingScoreAsText() {
        // TODO: looks like a map could work here
        switch (score) {
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Love";
        }
    }

    boolean isTiedWith(Player otherPlayer) {
        return score == otherPlayer.score;
    }

    boolean isAheadOf(Player otherPlayer) {
        return score > otherPlayer.score;
    }

    public boolean isAheadByOne(Player otherPlayer) {
        return score - otherPlayer.score == 1;
    }


}
