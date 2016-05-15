package Games.Kalaha.Players.AI;

import Games.Kalaha.Boards.Board;

import java.util.ArrayList;

/**
 * Created by florentdelgrange on 15/05/16.
 * This is the Max-n implementation.
 */
public class MaxN extends Minimax{

    public MaxN(int maxDepth, Utility utility, ArrayList<String> playersArray, String max) {
        super(maxDepth, utility, playersArray, max);
    }

    @Override
    public Integer compute(Board board) {
        int currentPlayer = 0;
        for(int i = 0; i < playersArray.size(); i++)
            if(playersArray.get(i).equals(max)) {
                currentPlayer = i;
                break;
            }
        maxValue(board, currentPlayer, 0);
        return action;
    }

    public double[] maxValue(Board board, int currentPlayer, int depth){
        if(terminalTest(board) || depth == maxDepth)
            return playersArray.stream().mapToDouble(avatar ->
                utility.getScore(board, avatar)).toArray();
        else{
            if(playersArray.get(currentPlayer).equals(max))
                depth ++;
            double[] v = new double[playersArray.size()];
            v[currentPlayer] = Double.NEGATIVE_INFINITY;
            for(Integer a : actions(board, playersArray.get(currentPlayer))){
                double[] nextV = maxValue(result(board, a), (currentPlayer + 1) % playersArray.size(), depth);
                if(nextV[currentPlayer] > v[currentPlayer]) {
                    if (playersArray.get(currentPlayer).equals(max))
                        action = a;
                    v = nextV;
                }
            }
            return v;
        }
    }
}
