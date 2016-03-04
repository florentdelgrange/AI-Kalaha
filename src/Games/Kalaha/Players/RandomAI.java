package Games.Kalaha.Players;

import java.util.List;
import java.util.Random;

import Games.Kalaha.Move;

public class RandomAI extends Player {
	
	private final Random randomizer;

	public RandomAI(String avatar) {
		super(avatar);
		this.randomizer = new Random();
	}

	@Override
	public Move pickMove() {
		int offset = 0;
		if (getBoard().getAvatar(0) != getAvatar()) {
			offset = getBoard().getLength() / 2;
		}
		
		Move move;
		do {
			move = new Move(offset + randomizer.nextInt(getBoard().getLength() / 2 - 1));
		} while (getBoard().getPieceAt(move.getPit()) == 0);
		
		return move;
	}

	@Override
	public void informEnd(List<String> winners) {
		// I don't care !
	}
	
	
}
