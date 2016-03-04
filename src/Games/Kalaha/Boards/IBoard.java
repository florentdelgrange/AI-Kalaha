package Games.Kalaha.Boards;

import Board.Path.IPath;

public interface IBoard extends IPath<Integer> {
	
	/*
	 *   8 7 6 5
	 *  9       4
	 *   0 1 2 3
	 */

	default public String getAvatar(Integer c) {
		return (c % getLength()) < getLength() / 2 ? getAvatar1() : getAvatar2(); 
	}

	default public boolean isKalaha(Integer c) {
		return (c + 1) % (getLength() / 2) == 0;
	}
	
	public String getAvatar1();
	public String getAvatar2();
	
	default public int player1Sum() {
		int sum = 0;
		for (int i = 0; i < getLength() / 2 - 1; ++i) {
			sum += getPieceAt(i);
		}
		return sum;
	}
	
	default public int player2Sum() {
		int sum = 0;
		for (int i = getLength() / 2; i < getLength() - 1; ++i) {
			sum += getPieceAt(i);
		}
		return sum;
	}

}
