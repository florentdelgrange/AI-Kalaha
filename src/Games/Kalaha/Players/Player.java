package Games.Kalaha.Players;

import Core.IDecisionMaker;
import Core.NameAvatar;
import Games.Kalaha.Move;
import Games.Kalaha.Boards.IBoard;

public abstract class Player implements IDecisionMaker<IBoard, Move, NameAvatar> {
	
	private final NameAvatar avatar;
	private IBoard board;
	
	public Player(NameAvatar avatar) {
		this.avatar = avatar;
	}

	@Override
	public final NameAvatar getAvatar() {
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
