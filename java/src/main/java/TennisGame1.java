
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
        if (playerName.equals("player1")) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        StringBuilder score = new StringBuilder();

        if (player1Score == player2Score) {
            appendScoreForDrawingScenario(score);
        } else if (player1Score >= 4 || player2Score >= 4) {
            appendScoreForAdvantageScenario(score);
        } else {
            appendScore(score);
        }

        return score.toString();
    }

    private void appendScore(StringBuilder score) {
        int tempScore;
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = player1Score;
            } else {
                score.append("-");
                tempScore = player2Score;
            }

            switch (tempScore) {
                case 0:
                    score.append("Love");
                    break;
                case 1:
                    score.append("Fifteen");
                    break;
                case 2:
                    score.append("Thirty");
                    break;
                case 3:
                    score.append("Forty");
                    break;
            }
        }
    }

    private void appendScoreForAdvantageScenario(StringBuilder score) {
        int minusResult = player1Score - player2Score;
        if (minusResult == 1) {
            score.append("Advantage player1");
        } else if (minusResult == -1) {
            score.append("Advantage player2");
        } else if (minusResult >= 2) {
            score.append("Win for player1");
        } else {
            score.append("Win for player2");
        }
    }

    private void appendScoreForDrawingScenario(StringBuilder score) {
        switch (player1Score) {
            case 0:
                score.append("Love-All");
                break;
            case 1:
                score.append("Fifteen-All");
                break;
            case 2:
                score.append("Thirty-All");
                break;
            default:
                score.append("Deuce");
                break;
        }
    }
}
