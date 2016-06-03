package Games.Kalaha.Players.AI;

import Games.Kalaha.Move;
import Games.Kalaha.Players.Player;

/**
 * Created by florentdelgrange on 03/06/16.
 */
public class MiniMaxAI extends Player{

    Heuristic heuristic;

    public MiniMaxAI(Heuristic heuristic){
        this.heuristic = heuristic;
    }

    @Override
    public Move pickMove(String s) {
        MiniMax minimax;
        if(players.size() == 2) {
            minimax = new MiniMax(12, heuristic, players, s, leftTokensGrantee, emptyCapture);
        }
        else {
            minimax = new MaxN(6, heuristic, players, s, leftTokensGrantee, emptyCapture);
        }
        return new Move(minimax.compute(board));
    }
}
