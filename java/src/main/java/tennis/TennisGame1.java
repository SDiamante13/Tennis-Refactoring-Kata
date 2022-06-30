package tennis;

// Low-Hanging Fruit (rename field, rename method, extract method, remove temp variables)
// Unroll For Loop
// Replace Conditional with Polymorphism
// Split AdvantageOrWinResult up
// Eliminate Primitive Obsession by introducing Player object
// Eliminate Feature Envy

public class TennisGame1 implements TennisGame {


    private Player player1;
    private Player player2;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
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

class AdvantageResult extends Result {

    public AdvantageResult(int player1Score, int player2Score) {
        super(player1Score, player2Score);
    }

    @Override
    String getScoreAsText() {
        return player1Score > player2Score ? "Advantage player1" : "Advantage player2";
    }
}

class WinResult extends Result {

    public WinResult(int player1Score, int player2Score) {
        super(player1Score, player2Score);
    }

    @Override
    String getScoreAsText() {
        return player1Score > player2Score ? "Win for player1" : "Win for player2";
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
    public static Result determineResult(Player player1, Player player2) {
        if (player1.isTiedWith(player2)) {
            return new DrawResult(player1.getScore(), player2.getScore());
        } else {
            if (eitherPlayerHasAdvantage(player1.getScore(), player2.getScore()) && someoneIsAheadByOnePoint(player1, player2)) {
                return new AdvantageResult(player1.getScore(), player2.getScore());
            } else if (eitherPlayerHasAdvantage(player1.getScore(), player2.getScore())) {
                return new WinResult(player1.getScore(), player2.getScore());
            } else {
                return new OngoingResult(player1.getScore(), player2.getScore());
            }
        }
    }

    private static boolean someoneIsAheadByOnePoint(Player player1, Player player2) {
        return Math.abs(player1.getScore() - player2.getScore()) == 1;
    }

    private static boolean eitherPlayerHasAdvantage(int player1Score, int player2Score) {
        return player1Score >= 4 || player2Score >= 4;
    }
}