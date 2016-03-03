package Games.Kalaha.Boards;

import Board.Path.Path;
import Core.Avatar;

public class Board extends Path<Integer> implements IBoard {
	
	private Avatar avatar1, avatar2;
	
	public Board(int pitsPerPlayer, Avatar avatar1, Avatar avatar2) {
		super(2 * (pitsPerPlayer + 1));
		this.avatar1 = avatar1;
		this.avatar2 = avatar2;
		for (int i = 0; i < 2 * (pitsPerPlayer + 1); ++i) {
			super.setPieceAt(i, 0);
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
	public Integer getPieceAt(Integer c) {
		return super.getPieceAt(Math.floorMod(c, getLength()));
	}
	
	@Override
	public void setPieceAt(Integer c, Integer p) {
		super.setPieceAt(Math.floorMod(c,  getLength()), p);
	}
	
}
