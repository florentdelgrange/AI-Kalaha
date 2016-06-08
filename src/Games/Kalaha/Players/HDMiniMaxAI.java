package Games.Kalaha.Players;

import Games.Kalaha.Move;

/**
 * Created by florentdelgrange on 03/06/16.
 */
public class HDMiniMaxAI extends Player{

    HDHeuristic heuristic;

    public HDMiniMaxAI(HDHeuristic heuristic){
        this.heuristic = heuristic;
    }

    @Override
    public Move pickMove(String s) {
        HDMiniMax minimax;
        if(players.size() == 2) {
            minimax = new HDMiniMax(12, heuristic, players, s, leftTokensGrantee, emptyCapture);
        }
        else {
            minimax = new HDMaxN(6, heuristic, players, s, leftTokensGrantee, emptyCapture);
        }
        return new Move(minimax.compute(board));
    }
}
