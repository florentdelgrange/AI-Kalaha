package Games.Kalaha.Boards;

import Board.Path.Path;
import Core.Avatar;
import Games.Kalaha.IPiece;
import Games.Kalaha.Piece;

public class Board extends Path<IPiece> implements IBoard {
	
	private Avatar avatar1, avatar2;
	
	public Board(int pitsPerPlayer, Avatar avatar1, Avatar avatar2) {
		super(2 * (pitsPerPlayer + 1));
		this.avatar1 = avatar1;
		this.avatar2 = avatar2;
		for (int i = 0; i < 2 * (pitsPerPlayer + 1); ++i) {
			super.setPieceAt(i, new Piece(0));
		}
	}
	
	@Override
	public Avatar getAvatar1() {
		return avatar1;
	}
	
	@Override
	public Avatar getAvatar2() {
		return avatar2;
	}
	
	@Override
	public Piece getPieceAt(Integer c) {
		return (Piece) super.getPieceAt(Math.floorMod(c, getLength()));
	}
	
	@Override
	public void setPieceAt(Integer c, IPiece p) {
		getPieceAt(c).increment(p.getSize());
	}
	
}
