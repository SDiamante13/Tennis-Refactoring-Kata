package tennis;

public class TennisGame1 implements TennisGame {
    private static final int ZERO_POINTS = 0;
    private static final int ONE_POINT = 1;
    private static final int TWO_POINTS = 2;
    private static final int THREE_POINTS = 3;

    // It passes all the tests
    // It minimizes duplication in all its forms
    // It expresses its intent clearly to the reader
    // It removes unnecessary elements

    // Step 6: Eliminate Primitive Obsession by introduction a Value Object called Player to hold the score of the player.
    // Step 7: Inline methods to further reduce duplication and complexity

    private Player player1;
    private Player player2;

    public TennisGame1(String player1Name, String player2Name) { // can migrate constructor to take in two players instead of names.
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) { // this can be replaced by just add points to players. This would require an interface change.
        if ("player1".equals(playerName)) {
            player1.addPoint();
        } else {
            player2.addPoint();
        }
    }

    public String getScore() {
        if (player1.isTiedWith(player2.score())) {
            return Points.ofTied(player1.score()).displayName();
        }

        if (player1.hasAdvantage(player2.score()) && player1.isAheadByOnePoint(player2.score())) {
                return "Advantage player1"; // can add ofAdvantage creation method to Points enum
        }

        if (player1.hasAdvantage(player2.score()) && player1.hasWon(player2.score())) {
            return "Win for player1";
        }

        if (player2.hasAdvantage(player1.score()) && player2.isAheadByOnePoint(player1.score())) {
                return "Advantage player2";
        }

        if (player2.hasAdvantage(player1.score()) && player2.hasWon(player1.score())) {
            return "Win for player2";
        }

        return String.format("%s-%s", translate(player1.score()), translate(player2.score()));
    }

    private String translate(int score) {
        return Points.of(score).displayName();
    }

    enum Points {
        ZERO("Love"), ONE("Fifteen"), TWO("Thirty"), THREE("Forty"), ZERO_ALL("Love-All"), ONE_ALL("Fifteen-All"), TWO_ALL("Thirty-All"), DEUCE("Deuce");

        private final String displayName;

        Points(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() {
            return displayName;
        }

        public static Points of(int score) {
            switch (score) {
                case ONE_POINT:
                    return Points.ONE;
                case TWO_POINTS:
                    return Points.TWO;
                case THREE_POINTS:
                    return Points.THREE;
                default:
                    return Points.ZERO;

            }
        }

        public static Points ofTied(int score) {
            switch (score) {
                case ZERO_POINTS:
                    return Points.ZERO_ALL;
                case ONE_POINT:
                    return Points.ONE_ALL;
                case TWO_POINTS:
                    return Points.TWO_ALL;
                default:
                    return Points.DEUCE;
            }
        }
    }

    static class Player {
        private static final int ADVANTAGE_THRESHOLD = 4;

        private final String name;
        private int score;

        Player(String name) {
            this.name = name;
            this.score = ZERO_POINTS;
        }

        public void addPoint() {
            this.score++;
        }

        public int score() {
            return score;
        }

        private boolean hasAdvantage(int otherScore) {
            return score >= ADVANTAGE_THRESHOLD && score > otherScore;
        }

        public boolean isTiedWith(int otherScore) {
            return score == otherScore;
        }

        private boolean hasWon(int otherScore) {
            return score - otherScore >= TWO_POINTS;
        }

        private boolean isAheadByOnePoint(int otherScore) {
            return score - otherScore == ONE_POINT;
        }
    }
}