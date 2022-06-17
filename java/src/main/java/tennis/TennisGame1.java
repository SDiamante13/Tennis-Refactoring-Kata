package tennis;

public class TennisGame1 implements TennisGame {

    private String player1Name;
    private String player2Name;
    private Player player1;
    private Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            player1.addPoint();
        } else {
            player2.addPoint();
        }
    }

    public String getScore() {
        return Arbiter.determineResult(player1, player2).getScoreAsText();
    }

}

class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    boolean isTiedWith(int otherScore) {
        return score == otherScore;
    }

    public int score() {
        return score;
    }

    public void addPoint() {
        score++;
    }
}

class Arbiter {
    public static Result determineResult(Player player1, Player player2) {
        if (player1.isTiedWith(player2.score())) {
            return new DrawResult(player1.score(), player2.score());
        } else if (player1.score() >= 4 || player2.score() >= 4) {
            return new AdvantageOrWinResult(player1.score(), player2.score());
        } else {
            return new OngoingResult(player1.score(), player2.score());
        }
    }
}

abstract class Result {
    int player1Score;
    int player2Score;

    Result(int player1Score, int player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    abstract String getScoreAsText();
}

class DrawResult extends Result {

    public DrawResult(int player1Score, int player2Score) {
        super(player1Score, player2Score);
    }

    @Override
    String getScoreAsText() {
        switch (player1Score) {
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
}

class AdvantageOrWinResult extends Result {

    AdvantageOrWinResult(int player1Score, int player2Score) {
        super(player1Score, player2Score);
    }

    @Override
    String getScoreAsText() {
        int minusResult = player1Score - player2Score;
        if (minusResult == 1) {
            return "Advantage player1";
        } else if (minusResult == -1) {
            return "Advantage player2";
        } else if (minusResult >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }
}

class OngoingResult extends Result {

    OngoingResult(int player1Score, int player2Score) {
        super(player1Score, player2Score);
    }

    @Override
    String getScoreAsText() {
        return getScoreText(player1Score) + "-" + getScoreText(player2Score);
    }

    private String getScoreText(int tempScore) {
        switch (tempScore) {
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
}

