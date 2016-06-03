package Games.Kalaha.Players;

import Games.Kalaha.Move;
import Games.Kalaha.Players.AI.Heuristic;
import Games.Kalaha.Players.AI.MaxN;
import Games.Kalaha.Players.AI.MiniMax;

/**
 * Created by florentdelgrange on 15/05/16.
 * TODO : drop
 */
public class KalahaAI extends Player{
    @Override
    public Move pickMove(String s) {
        Heuristic heuristic = (board1, player) -> 1.0 * board1.getSums(true, false).get(player);
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
