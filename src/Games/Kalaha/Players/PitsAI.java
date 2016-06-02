package Games.Kalaha.Players;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Players.AI.*;

/**
 * Created by florentdelgrange on 14/05/16.
 */
public class PitsAI extends Player{
    @Override
    public Move pickMove(String s) {
        Heuristic utility = (board1, player) -> 1.0 * board1.getSums(false, true).get(player);
        Minimax heuristic;
        if(players.size() == 2) {
            heuristic = new Minimax(12, utility, players, s, leftTokensGrantee, emptyCapture);
        }
        else {
            heuristic = new MaxN(6, utility, players, s, leftTokensGrantee, emptyCapture);
        }
        return new Move(heuristic.compute(board));
    }
}
