package Games.Kalaha.Players;

import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Boards.Board;

public abstract class Player extends Core.Player<Integer, Integer, Board, String, Game, Move> {
	
	protected Game.LeftTokensGrantee leftTokensGrantee;
	protected boolean emptyCapture; 
	
	public void informLeftTokensGrantee(Game.LeftTokensGrantee leftTokensGrantee) {
		this.leftTokensGrantee = leftTokensGrantee;
	}
	
	public void informEmptyCapture(boolean emptyCapture) {
		this.emptyCapture = emptyCapture;
	}
	
}
