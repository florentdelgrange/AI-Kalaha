package Games.Kalaha.Players;

import Games.Kalaha.Move;
import Games.Kalaha.Players.AI.Minimax;
import Games.Kalaha.Players.AI.TokenUtility;
import Games.Kalaha.Players.AI.Utility;

/**
 * Created by florentdelgrange on 14/05/16.
 */
public class TokenMinimaxAI extends Player{
    @Override
    public Move pickMove(String s) {
        Utility utility = new TokenUtility(leftTokensGrantee, emptyCapture);
        Minimax minimax = new Minimax(1000000, utility, avatars, s);
        return new Move(minimax.compute(board));
    }
}
