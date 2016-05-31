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

    protected int maxDepth;
    protected Utility utility;
    protected ArrayList<String> players;
    protected String max;
    protected Integer action;

    public Minimax(int maxDepth, Utility utility, ArrayList<String> players, String max){
        this.maxDepth = maxDepth;
        this.utility = utility;
        this.players = players;
        this.max = max;
        //order initialization
        while(!players.get(0).equals(max))
            players.add(players.remove(0));
    }

    @Override
    public Integer compute(Board board) {
        maxValue(board, 0, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
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

    protected class CurrentState{
        protected final Board board;
        protected final String avatar;
        protected CurrentState(Board board, String avatar){
            this.board = board;
            this.avatar = avatar;
        }
    }

    public CurrentState result(Board board, Integer currentPlayer, Integer a){
        ArrayList playersInOrder = new ArrayList(players.size());
        playersInOrder.addAll(players.subList(currentPlayer, players.size()));
        playersInOrder.addAll(players.subList(0, currentPlayer));
        Game clonedGame = new Game(board.clone(), utility.getLeftTokensGrantee(),
                utility.getEmptyCapture(), playersInOrder);
        Move move = new Move(a);
        move.apply(clonedGame);
        return new CurrentState(clonedGame.getBoard(), clonedGame.getCurrentPlayer());
    }

    public Double maxValue(Board board, int currentPlayer, int depth, Double alpha, Double beta){
        if(terminalTest(board) || depth == maxDepth)
            return utility.getScore(board, max);
        else{
            Double v = Double.NEGATIVE_INFINITY;
            for(Integer a : actions(board, players.get(currentPlayer))) {
                CurrentState result = result(board, currentPlayer, a);
                if(result.avatar.equals(players.get(currentPlayer)))
                    v = Math.max(v, maxValue(result.board, currentPlayer, depth + 1, alpha, beta));
                else
                    v = Math.max(v, minValue(result.board,
                            (currentPlayer + 1) % players.size(), depth + 1, alpha, beta));
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
        if(terminalTest(board) || depth == maxDepth){
            return utility.getScore(board, max);
        }
        else{
            Double v = Double.POSITIVE_INFINITY;
            for(Integer a: actions(board, players.get(currentPlayer))) {
                CurrentState result = result(board, currentPlayer, a);
                if(result.avatar.equals(players.get(currentPlayer)))
                    v = Math.min(v, maxValue(result.board, currentPlayer, depth + 1, alpha, beta));
                else
                    v = Math.min(v, maxValue(result.board,
                        (currentPlayer + 1) % players.size(), depth + 1, alpha, beta));
                if(v <= alpha)
                    return v;
                beta = Math.min(beta, v);
            }
            return v;
        }
    }
}
