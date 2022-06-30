package tennis;

class Arbiter {

    private Arbiter() {
    }

    public static Result determineResult(Player player1, Player player2) {
        // TODO: Change Arbiter to non-static and give it player1 and player2 in constructor
        if (player1.isTiedWith(player2)) {
            return new DrawResult(player1, player2);
        } else if (aPlayerHasAScoreGreaterThanThree(player1, player2) && someoneIsAheadByOnePoint(player1, player2)) {
            return new AdvantageResult(player1, player2);
        } else if (aPlayerHasAScoreGreaterThanThree(player1, player2)) {
            return new WinResult(player1, player2);
        } else {
            return new OngoingResult(player1, player2);
        }
    }

    static boolean someoneIsAheadByOnePoint(Player player1, Player player2) {
        return player1.isAheadByOne(player2) || player2.isAheadByOne(player1);
    }

    private static boolean aPlayerHasAScoreGreaterThanThree(Player player1, Player player2) {
        return player1.hasScoreGreaterThanThree() || player2.hasScoreGreaterThanThree();
    }

}
