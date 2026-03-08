package Sunday_CC_Week32_Mancala.Sunday_CC_Week32_Mancala;

import java.util.Arrays;

public class MancalaGame {
    // Indices 0-5: Player 1 pits, 6: Player 1 Store
    // Indices 7-12: Player 2 pits, 13: Player 2 Store
    private int[] pits;
    private int currentPlayer;
    private String winner;
    private boolean gameOver;

    public MancalaGame() {
        reset();
    }

    public void reset() {
        pits = new int[14];
        Arrays.fill(pits, 0, 6, 4);  // 4 seeds each
        Arrays.fill(pits, 7, 13, 4); // 4 seeds each
        pits[6] = 0;  // Store 1
        pits[13] = 0; // Store 2
        currentPlayer = 1;
        winner = null;
        gameOver = false;
    }

    public int[] getPits() { return pits; }
    public int getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(int currentPlayer) { this.currentPlayer = currentPlayer; }
    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
}
