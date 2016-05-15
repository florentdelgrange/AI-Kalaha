package Games.Kalaha.Players;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Players.AI.Heuristic;
import Games.Kalaha.Players.AI.MaxN;
import Games.Kalaha.Players.AI.Minimax;
import Games.Kalaha.Players.AI.Utility;

import java.util.HashMap;

/**
 * Created by florentdelgrange on 15/05/16.
 */
public class KalahaMinimizerAI extends Player{
    @Override
    public Move pickMove(String s) {
        Utility utility = new Utility() {
            @Override
            public Double getScore(Board board, String player) {
                HashMap<String, Integer> sums = board.getSums(true, false);
                return -1.0 * ( sums.values().stream().reduce(0, (a, b) -> a + b) - sums.get(player) );
            }

            @Override
            public Game.LeftTokensGrantee getLeftTokensGrantee() {
                return leftTokensGrantee;
            }

            @Override
            public boolean getEmptyCapture() {
                return emptyCapture;
            }
        };
        Heuristic heuristic;
        if(players.size() == 2) {
            heuristic = new Minimax(7, utility, players, s);
        }
        else {
            heuristic = new MaxN(3, utility, players, s);
        }
        return new Move(heuristic.compute(board));
    }
}
