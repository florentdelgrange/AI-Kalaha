package Games.Kalaha.Players;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Players.AI.Heuristic;
import Games.Kalaha.Players.AI.MaxN;
import Games.Kalaha.Players.AI.Minimax;

/**
 * Created by florentdelgrange on 15/05/16.
 */
public class KalahaAI extends Player{
    @Override
    public Move pickMove(String s) {
        Heuristic heuristic = (board1, player) -> 1.0 * board1.getSums(true, false).get(player);
        Minimax minimax;
        if(players.size() == 2) {
            minimax = new Minimax(12, heuristic, players, s, leftTokensGrantee, emptyCapture);
        }
        else {
            minimax = new MaxN(6, heuristic, players, s, leftTokensGrantee, emptyCapture);
        }
        return new Move(minimax.compute(board));
    }
}
