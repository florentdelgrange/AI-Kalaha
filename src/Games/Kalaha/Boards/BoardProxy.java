package Games.Kalaha.Boards;

import Board.Path.PathProxy;
import Core.Avatar;
import Games.Kalaha.IPiece;
import Games.Kalaha.PieceProxy;

public class BoardProxy extends PathProxy<IPiece> implements IBoard {
	
	private Board board;
	
	public BoardProxy(Board board) {
		super(board);
		this.board = board;
	}
	
	@Override
	public PieceProxy getPieceAt(Integer c) {
		return new PieceProxy(board.getPieceAt(c));
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
