package tennis;

// Low-Hanging Fruit (rename field, rename method, extract method, remove temp variables)
// Unroll For Loop
// Replace Conditional with Polymorphism

public class TennisGame1 implements TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        return Arbiter.determineResult(player1Score, player2Score).getScoreAsText();
    }
}

abstract class Result {
    int player1Score;
    int player2Score;

    public Result(int player1Score, int player2Score) {
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

    public AdvantageOrWinResult(int player1Score, int player2Score) {
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
    public OngoingResult(int player1Score, int player2Score) {
        super(player1Score, player2Score);
    }

    @Override
    String getScoreAsText() {
        return getScoreAsString(player1Score) + "-" + getScoreAsString(player2Score);
    }

    private String getScoreAsString(int score) {
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
}

class Arbiter {
    public static Result determineResult(int player1Score, int player2Score) {
        if (player1Score == player2Score) {
            return new DrawResult(player1Score, player2Score);
        } else if (player1Score >= 4 || player2Score >= 4) {
            return new AdvantageOrWinResult(player1Score, player2Score);
        } else {
            return new OngoingResult(player1Score, player2Score);
        }
    }
}