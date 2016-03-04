package Games.Kalaha.Boards;

import Board.Path.Path;

public class Board extends Path<Integer> implements IBoard {
	
	private String avatar1, avatar2;
	
	public Board(int pitsPerPlayer, String avatar1, String avatar2) {
		super(2 * (pitsPerPlayer + 1));
		this.avatar1 = avatar1;
		this.avatar2 = avatar2;
		for (int i = 0; i < 2 * (pitsPerPlayer + 1); ++i) {
			super.setPieceAt(i, 0);
		}
	}
	
	@Override
	public String getAvatar1() {
		return avatar1;
	}
	
	@Override
	public String getAvatar2() {
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
