package Games.Kalaha.Players;

import Core.Player;
import Games.Kalaha.Move;
import Games.Kalaha.Boards.IBoard;

public abstract class Player implements Player<IBoard, Move, String> {
	
	private final String avatar;
	private IBoard board;
	
	public Player(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public final String getAvatar() {
		return avatar;
	}

	@Override
	public void informBoard(IBoard board) {
		this.board = board;
	}
	
	public IBoard getBoard() {
		return board;
	}

}
