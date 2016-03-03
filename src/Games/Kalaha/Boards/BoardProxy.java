package Games.Kalaha.Boards;

import Board.Path.PathProxy;
import Core.Avatar;

public class BoardProxy extends PathProxy<Integer> implements IBoard {
	
	private Board board;
	
	public BoardProxy(Board board) {
		super(board);
		this.board = board;
	}
	
	@Override
	public Avatar getAvatar1() {
		return board.getAvatar1();
	}
	
	@Override
	public Avatar getAvatar2() {
		return board.getAvatar2();
	}
}
