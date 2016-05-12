package Games.Kalaha.Players.AI;

import Core.Player;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Move;

import java.util.ArrayList;

/**
 * Created by florentdelgrange on 11/05/16.
 */
public class Minimax implements Heuristic{

    private int maxDepth;
    private Utility utility;
    private Player[] playersArray;

    public Minimax(int maxDepth, Utility utility, Player[] playersArray){
        this.maxDepth = maxDepth;
        this.utility = utility;
        this.playersArray = playersArray;
    }

    public boolean terminalTest(Board board) {
        return board.getSums(false, true).containsValue(0);
    }

    @Override
    public void compute(Board board) {
        
    }

    public ArrayList<Integer> actions(Board board, Player currentPlayer) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        for (int i = 0; i < board.getLength(); ++i) {
            if (board.getPlayer(i).equals(currentPlayer) && !board.isKalaha(i) && board.getPieceAt(i) > 0) {
                possibleMoves.add(i);
            }
        }
        return possibleMoves;
    }
/*
    public Double maxValue(Board board, Double alpha, Double beta, int currentPlayer){
        if(terminalTest(board))
            return utility.compute(board);
        else{
            Double v = Double.NEGATIVE_INFINITY;
            for(Integer a : actions(board, playersArray[currentPlayer])){

            }
        }
    } */
}
