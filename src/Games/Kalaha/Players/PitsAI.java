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
        Utility utility = new Utility() {
            @Override
            public Double getScore(Board board, String player) {
                return 1.0 * board.getSums(false, true).get(player);
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
