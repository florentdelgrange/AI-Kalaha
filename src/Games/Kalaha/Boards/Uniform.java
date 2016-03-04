package Games.Kalaha.Boards;

public class Uniform extends Board {

	public Uniform(int pitsPerPlayer, int tokensPerPit, String avatar1, String avatar2) {
		super(pitsPerPlayer, avatar1, avatar2);
		for (int i = 0; i < 2 * (pitsPerPlayer + 1); ++i) {
			if (!isKalaha(i)) {
				setPieceAt(i, tokensPerPit);
			}
		}
	}
	
}
