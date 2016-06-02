package Games.Kalaha.Players;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Players.AI.MaxN;
import Games.Kalaha.Players.AI.Minimax;
import Games.Kalaha.Players.AI.Heuristic;

import java.util.LinkedList;

/**
 * Created by florentdelgrange on 15/05/16.
 */
public class SquaredPitsAI extends Player{
    @Override
    public Move pickMove(String s) {
        Heuristic utility = (board1, player) -> {
            Double score = 0.0;
            LinkedList<Integer> indices = new LinkedList<>();
            for (int i = 0; i < board1.getLength(); ++i) {
                if (!board1.isKalaha(i) && board1.getPlayer(i).equals(player)) {
                    indices.add(i);
                }
            }
            for(Integer i : indices){
                for(Integer j : indices){
                    score += Math.pow(board1.getPieceAt(i) - board1.getPieceAt(j), 2);
                }
            }
            return -1.0 * score;
        };
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
