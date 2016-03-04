package Games.Kalaha.Boards;

import Board.Path.PathProxy;

public class BoardProxy extends PathProxy<Integer> implements IBoard {
	
	private Board board;
	
	public BoardProxy(Board board) {
		super(board);
		this.board = board;
	}
	
	@Override
	public String getAvatar1() {
		return board.getAvatar1();
	}
	
	@Override
	public String getAvatar2() {
		return board.getAvatar2();
	}
}
