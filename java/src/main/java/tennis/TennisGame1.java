package tennis;

public class TennisGame1 implements TennisGame {

    // TODO: introduce Score object
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    private String translateTiedScore(int playerScore) {
        switch (playerScore) {
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


    public String getScore() {
        StringBuilder score = new StringBuilder();
        if (scoresAreEqual(player1Score, player2Score)) {
            String tiedScore = translateTiedScore(player1Score);
            return score.append(tiedScore)
                    .toString();
        } else if (isPlayerCrushingIt()) {
            int minusResult = player1Score - player2Score;
            translateWinningScore(score, minusResult);
        } else {
            //  player1Score.translate() + "-" + player2Score.translate();
            //  Use a HashMap (0 -> Love, 1-> Fifteen, 2-> Thirty, 3-> Forty)
            for (int i = 1; i < 3; i++) {
                int tempScore;

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
        return score.toString();
    }

    private void translateWinningScore(StringBuilder score, int minusResult) {
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

    private boolean isPlayerCrushingIt() {
        return player1Score >= 4 || player2Score >= 4;
    }


    private boolean scoresAreEqual(int player1Score, int player2Score) {
        return player1Score == player2Score;
    }
}