package Sunday_CC_Week32_Mancala.Sunday_CC_Week32_Mancala;

import org.springframework.stereotype.Service;

@Service
public class MancalaService {

    public void makeMove(MancalaGame game, int pitIndex) {
        if (game.isGameOver()) return;

        int[] pits = game.getPits();
        int seeds = pits[pitIndex];
        if (seeds == 0) return;

        pits[pitIndex] = 0;
        int currentPos = pitIndex;

        while (seeds > 0) {
            currentPos = (currentPos + 1) % 14;

            if (game.getCurrentPlayer() == 1 && currentPos == 13) continue;
            if (game.getCurrentPlayer() == 2 && currentPos == 6) continue;

            pits[currentPos]++;
            seeds--;
        }

        handleCapture(game, currentPos);

        if (!isLastSeedInOwnStore(game, currentPos)) {
            game.setCurrentPlayer(game.getCurrentPlayer() == 1 ? 2 : 1);
        }

        checkGameOver(game);
    }

    private void handleCapture(MancalaGame game, int lastPos) {
        int[] pits = game.getPits();
        int player = game.getCurrentPlayer();

        // must land in own empty pit
        boolean ownPit = (player == 1 && lastPos >= 0 && lastPos <= 5) ||
                (player == 2 && lastPos >= 7 && lastPos <= 12);

        if (ownPit && pits[lastPos] == 1) {
            int oppositePos = 12 - lastPos;
            if (pits[oppositePos] > 0) {
                int captured = pits[oppositePos] + pits[lastPos];
                pits[oppositePos] = 0;
                pits[lastPos] = 0;
                int storeIdx = (player == 1) ? 6 : 13;
                pits[storeIdx] += captured;
            }
        }
    }

    private boolean isLastSeedInOwnStore(MancalaGame game, int lastPos) {
        return (game.getCurrentPlayer() == 1 && lastPos == 6) ||
                (game.getCurrentPlayer() == 2 && lastPos == 13);
    }

    private void checkGameOver(MancalaGame game) {
        int[] pits = game.getPits();
        boolean p1Empty = true, p2Empty = true;

        for (int i = 0; i < 6; i++) if (pits[i] > 0) p1Empty = false;
        for (int i = 7; i < 13; i++) if (pits[i] > 0) p2Empty = false;

        if (p1Empty || p2Empty) {
            game.setGameOver(true);
            // collect remaining seeds
            for (int i = 0; i < 6; i++) { pits[6] += pits[i]; pits[i] = 0; }
            for (int i = 7; i < 13; i++) { pits[13] += pits[i]; pits[i] = 0; }

            if (pits[6] > pits[13]) game.setWinner("Player 1 Wins!");
            else if (pits[13] > pits[6]) game.setWinner("Player 2 Wins!");
            else game.setWinner("It's a Tie!");
        }
    }
}