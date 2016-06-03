package Games.Kalaha.Players.AI;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;

import java.util.ArrayList;

/**
 * Created by florentdelgrange on 15/05/16.
 * This is the Max-n implementation.
 */
public class MaxN extends MiniMax {

    public MaxN(int maxDepth, Heuristic heuristic, ArrayList<String> playersArray, String max,
                Game.LeftTokensGrantee leftTokensGrantee, Boolean emptyCapture) {
        super(maxDepth, heuristic, playersArray, max, leftTokensGrantee, emptyCapture);
    }

    @Override
    public Integer compute(Board board) {
        maxValue(board, 0, 0);
        return action;
    }

    public double[] maxValue(Board board, int currentPlayer, int depth){
        if(terminalTest(board) || depth == maxDepth)
            return players.stream().mapToDouble(avatar ->
                heuristic.compute(board, avatar)).toArray();
        else{
            double[] v = new double[players.size()];
            v[currentPlayer] = Double.NEGATIVE_INFINITY;
            for(Integer a : actions(board, players.get(currentPlayer))){
                CurrentState result = result(board, currentPlayer, a);
                double[] nextV;
                if(result.avatar.equals(players.get(currentPlayer)))
                    nextV = maxValue(result.board, currentPlayer, depth + 1);
                else
                    nextV = maxValue(result.board, (currentPlayer + 1) % players.size(), depth + 1);
                if(nextV[currentPlayer] > v[currentPlayer]) {
                    if (depth == 0)
                        action = a;
                    v = nextV;
                }
            }
            return v;
        }
    }
}
