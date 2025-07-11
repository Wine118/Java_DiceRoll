import java.util.Random;

public class DiceRollGameLogic {

    private int player1Score;
    private int player2Score;
    private Random random;
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";

    public DiceRollGameLogic() {
        player1Score = 0;
        player2Score = 0;
        random = new Random();
    }

    public void setPlayer1Name(String name) {
        if(name != null && !name.trim().isEmpty()){
            this.player1Name = name.trim();
        }
    }

    public void setPlayer2Name(String name) {
        if(name != null && !name.trim().isEmpty()){
            this.player2Name = name.trim();
        }
    }

    // Roll dice for Player 1
    public int[] rollForPlayer1() {
        int die1 = rollDie();
        int die2 = rollDie();
        player1Score = die1 + die2;
        return new int[]{die1, die2};
    }

    // Roll dice for Player 2
    public int[] rollForPlayer2() {
        int die1 = rollDie();
        int die2 = rollDie();
        player2Score = die1 + die2;
        return new int[]{die1, die2};
    }

    // Helper method to roll one die (1 to 6)
    private int rollDie() {
        return random.nextInt(6) + 1;
    }

    // Get the winner
    public String getWinner() {
        if (player1Score > player2Score) {
            return player1Name + " score" + player1Score+ " and "+player2Name + " score " + player2Score+"\n\n"+player1Name+" Win!";
        } else if (player2Score > player1Score) {
            return player1Name + " score" + player1Score+ " and "+player2Name + " score " + player2Score+"\n\n"+player2Name + " Win!";
        } else {
            return "It's a Tie!";
        }
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    // Optional: Get scores
    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }
}

