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
