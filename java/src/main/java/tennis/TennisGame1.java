package tennis;

public class TennisGame1 implements TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        return Arbiter.determineResult(player1Score, player2Score)
                .getScore(player1Score, player2Score);
    }

    static class Arbiter {
        public static Result determineResult(int player1Score, int player2Score) {

            if (playersAreDrawn(player1Score, player2Score)) {
                return new DrawResult();
            } else if (eitherPlayerHasAdvantage(player1Score, player2Score) && someoneIsAheadByOne(player1Score, player2Score)) {
                return new AdvantageResult();
            } else if (eitherPlayerHasAdvantage(player1Score, player2Score)) {
                return new WinResult();
            } else {
                return new OngoingResult();
            }
        }

        private static boolean someoneIsAheadByOne(int player1Score, int player2Score) {
            return Math.abs(player1Score - player2Score) == 1;
        }

        private static boolean eitherPlayerHasAdvantage(int player1Score, int player2Score) {
            return playerHasAdvantage(player1Score) || playerHasAdvantage(player2Score);
        }

        private static boolean playersAreDrawn(int player1Score, int player2Score) {
            return player1Score == player2Score;
        }

        private static boolean playerHasAdvantage(int player1Score) {
            return player1Score >= 4;
        }
    }

    abstract static class Result {
        abstract String getScore(int player1Score, int player2Score);
    }

    static class DrawResult extends Result {

        @Override
        String getScore(int player1Score, int player2Score) {
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

    static class AdvantageResult extends Result {

        @Override
        String getScore(int player1Score, int player2Score) {
            if (player1Score > player2Score) {
                return "Advantage player1";
            } else {
                return "Advantage player2";
            }
        }
    }

    static class WinResult extends Result {

        @Override
        String getScore(int player1Score, int player2Score) {
            if (player1Score > player2Score) {
                return "Win for player1";
            } else {
                return "Win for player2";
            }
        }
    }

    static class OngoingResult extends Result {

        @Override
        String getScore(int player1Score, int player2Score) {
            return score(player1Score) + "-" + score(player2Score);
        }

        private String score(int playerScore) {
            switch (playerScore) {
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
}