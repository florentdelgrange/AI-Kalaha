package Games.Kalaha.Players;

import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Boards.Board;

/**
 * A Kalaha Player plays like in other games but as this game has two
 * parameters, the player is informed of which set of rules is used for the
 * current game. For this reason a single instance can not play two games
 * simultaneously if the rules are not the same (unless he does not care about
 * these rules).
 * 
 * @author Fabian Pijcke
 *
 */
public abstract class Player extends Core.Player<Integer, Integer, Board, String, Game, Move> {
	
	protected Game.LeftTokensGrantee leftTokensGrantee;
	protected boolean emptyCapture; 
	
	/**
	 * Who are the remaining tokens for when the game finishes ?
	 * @param leftTokensGrantee
	 */
	public void informLeftTokensGrantee(Game.LeftTokensGrantee leftTokensGrantee) {
		this.leftTokensGrantee = leftTokensGrantee;
	}
	
	/**
	 * Is a capture of zero opposing tokens still a capture ?
	 * @param emptyCapture
	 */
	public void informEmptyCapture(boolean emptyCapture) {
		this.emptyCapture = emptyCapture;
	}
	
}
