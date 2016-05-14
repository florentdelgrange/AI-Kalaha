package Games.Kalaha.Players.AI;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;

import java.util.ArrayList;

/**
 * Created by florentdelgrange on 11/05/16.
 * Classic alpha-beta Minimax implementation for 2 players.
 */
public class Minimax implements Heuristic{

    private int maxDepth;
    private Utility utility;
    private ArrayList<String> playersArray;
    private String max;
    private Integer action;

    public Minimax(int maxDepth, Utility utility, ArrayList<String> playersArray, String max){
        this.maxDepth = maxDepth;
        this.utility = utility;
        this.playersArray = playersArray;
        this.max = max;
    }

    @Override
    public Integer compute(Board board) {
        int currentPlayer = 0;
        for(int i = 0; i < playersArray.size(); i++)
            if(playersArray.get(i).equals(max)) {
                currentPlayer = i;
                break;
            }
        maxValue(board, currentPlayer, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        return action;
    }

    public boolean terminalTest(Board board) {
        return board.getSums(false, true).containsValue(0);
    }

    public ArrayList<Integer> actions(Board board, String currentPlayer) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        for (int i = 0; i < board.getLength(); ++i) {
            if (board.getPlayer(i).equals(currentPlayer)
                    && !board.isKalaha(i) && board.getPieceAt(i) > 0) {
                possibleMoves.add(i);
            }
        }
        return possibleMoves;
    }

    public Board result(Board board, Integer a){
        Game clonedGame = new Game(board.clone(), utility.getLeftTokensGrantee(),
                utility.getEmptyCapture(), playersArray);
        Move move = new Move(a);
        move.apply(clonedGame);
        return clonedGame.getBoard();
    }

    public Double maxValue(Board board, int currentPlayer, int depth, Double alpha, Double beta){
        if(terminalTest(board) || depth == maxDepth)
            return utility.getScore(board, max);
        else{
            Double v = Double.NEGATIVE_INFINITY;
            for(Integer a : actions(board, playersArray.get(currentPlayer))) {
                v = Math.max(v, minValue(result(board, a),
                        (currentPlayer + 1) % playersArray.size(), depth, alpha, beta));
                if (v >= beta)
                    return v;
                if(depth == 0 && alpha < v)
                    this.action = a;
                alpha = Math.max(alpha, v);
            }
            return v;
        }
    }

    public Double minValue(Board board, int currentPlayer, int depth, Double alpha, Double beta){
        if(terminalTest(board)){
            return utility.getScore(board, max);
        }
        else{
            Double v = Double.POSITIVE_INFINITY;
            for(Integer a: actions(board, playersArray.get(currentPlayer))) {
                v = Math.min(v, maxValue(result(board, a),
                        (currentPlayer + 1) % playersArray.size(), depth + 1, alpha, beta));
                if(v <= alpha)
                    return v;
                beta = Math.min(beta, v);
            }
            return v;
        }
    }
}
