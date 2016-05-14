package Games.Kalaha.Players.AI;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;

/**
 * Created by florentdelgrange on 14/05/16.
 */
public class TokenUtility implements  Utility{
    private Game.LeftTokensGrantee leftTokensGrantee;
    private boolean emptyCapture;

    public TokenUtility(Game.LeftTokensGrantee leftTokensGrantee, boolean emptyCapture){
        this.leftTokensGrantee = leftTokensGrantee;
        this.emptyCapture = emptyCapture;
    }

    @Override
    public Double getScore(Board board, String player) {
        return 1.0 * board.getSums(true, true).get(player);
    }

    @Override
    public Game.LeftTokensGrantee getLeftTokensGrantee() {
        return leftTokensGrantee;
    }

    @Override
    public boolean getEmptyCapture() {
        return emptyCapture;
    }
}
