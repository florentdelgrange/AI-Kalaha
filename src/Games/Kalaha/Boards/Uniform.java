package Games.Kalaha.Boards;

import Core.Avatar;
import Games.Kalaha.Piece;

public class Uniform extends Board {

	public Uniform(int pitsPerPlayer, int tokensPerPit, Avatar avatar1, Avatar avatar2) {
		super(pitsPerPlayer, avatar1, avatar2);
		for (int i = 0; i < 2 * (pitsPerPlayer + 1); ++i) {
			getPieceAt(i).increment(tokensPerPit);
		}
	}
	
}
