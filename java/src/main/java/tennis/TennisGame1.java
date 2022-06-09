package tennis;

public class TennisGame1 implements TennisGame {
    private static final int ZERO_POINTS = 0;
    private static final int ONE_POINT = 1;
    private static final int TWO_POINTS = 2;
    private static final int THREE_POINTS = 3;
    private static final int ADVANTAGE_THRESHOLD = 4;

    // It passes all the tests
    // It minimizes duplication in all its forms
    // It expresses its intent clearly to the reader
    // It removes unnecessary elements

    // Step 0: Identify Code smells - Long method, Heavy Indentation, Temporary Field
    // Step 1: Rename methods, fields, and variables to understand the domain better.
    // Step 2: Extract methods to paint a clear picture of what the code is doing.
    // Step 3: Remove temporary variables. Return fields early.
    // Step 4: Substitute Algorithm for Advantage method
    // Step 5: Substitute Algorithm for ScoreBothPlayers method
    // Step 6: Eliminate Primitive Obsession by introduction a Value Object called Player to hold the score of the player.
    
    private int playerOneScore = ZERO_POINTS;
    private int playerTwoScore = ZERO_POINTS;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        // replace with ternary?
        if (playerName == "player1")
            playerOneScore += ONE_POINT;
        else
            playerTwoScore += ONE_POINT;
    }

    public String getScore() {
        if (playerOneScore == playerTwoScore) {
            return tiedScore();
        }
        boolean playersAreInAdvantagePlay = playerOneScore >= ADVANTAGE_THRESHOLD || playerTwoScore >= ADVANTAGE_THRESHOLD;
        if (playersAreInAdvantagePlay) {
            return scoreForAdvantagePlay();
        } else {
            return scoreForBothPlayers();
        }
    }


    private String scoreForBothPlayers() {
        return String.format("%s-%s", translate(playerOneScore), translate(playerTwoScore));
    }

    private String translate(int score) {
        // make an enum called Score (ZERO, ONE, TWO, THREE, ) with mappings to String values
        switch (score) {
            case ONE_POINT:
                return "Fifteen";
            case TWO_POINTS:
                return "Thirty";
            case THREE_POINTS:
                return "Forty";
            default:
                return "Love";
        }
    }

    private String scoreForAdvantagePlay() {
        if (playerOneIsAheadByOnePoint()) { // this could be  Enum called ScoringState {ADVANTAGE_PLAYER_ONE, ADVANTAGE_PLAYER_TWO, PLAYER_ONE_WIN, PLAYER_TWO_WIN}
            return "Advantage player1";
        }
        if (playerTwoIsAheadByOnePoint()) {
            return "Advantage player2";
        }
        if (playerOneHasWon()) {
            return "Win for player1";
        }
        return "Win for player2";
    }

    private boolean playerOneHasWon() {
        return playerOneScore - playerTwoScore >= TWO_POINTS;
    }

    private boolean playerTwoIsAheadByOnePoint() {
        return playerTwoScore - playerOneScore == ONE_POINT;
    }

    private boolean playerOneIsAheadByOnePoint() {
        return playerOneScore - playerTwoScore == ONE_POINT;
    }

    private String tiedScore() {
        switch (playerOneScore) {
            case ZERO_POINTS:
                return "Love-All";
            case ONE_POINT:
                return "Fifteen-All";
            case TWO_POINTS:
                return "Thirty-All";
            default:
                return "Deuce";
        }
    }
}