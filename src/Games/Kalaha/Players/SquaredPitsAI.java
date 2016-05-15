package Games.Kalaha.Players;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Players.AI.Heuristic;
import Games.Kalaha.Players.AI.MaxN;
import Games.Kalaha.Players.AI.Minimax;
import Games.Kalaha.Players.AI.Utility;

import java.util.LinkedList;

/**
 * Created by florentdelgrange on 15/05/16.
 */
public class SquaredPitsAI extends Player{
    @Override
    public Move pickMove(String s) {
        Utility utility = new Utility() {
            @Override
            public Double getScore(Board board, String player) {
                Double score = 0.0;
                LinkedList<Integer> indices = new LinkedList<>();
                for (int i = 0; i < board.getLength(); ++i) {
                    if (!board.isKalaha(i) && board.getPlayer(i).equals(player)) {
                        indices.add(i);
                    }
                }
                for(Integer i : indices){
                    for(Integer j : indices){
                        score += Math.pow(board.getPieceAt(i) - board.getPieceAt(j), 2);
                    }
                }
                return -1.0 * score;
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
            heuristic = new Minimax(6, utility, players, s);
        }
        else {
            heuristic = new MaxN(3, utility, players, s);
        }
        return new Move(heuristic.compute(board));
    }
}
