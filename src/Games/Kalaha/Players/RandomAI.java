package Games.Kalaha.Players;

import java.util.ArrayList;
import java.util.Random;

import Games.Kalaha.Move;

/**
 * A Random AI for playing Kalaha Game.
 * 
 * @author Fabian Pijcke
 */
public class RandomAI extends Player {
	
	private final Random randomizer = new Random();
	
	@Override
	public Move pickMove(String avatar) {
		ArrayList<Integer> possibleMoves = new ArrayList<>();
		for (int i = 0; i < board.getLength(); ++i) {
			if (board.getPlayer(i).equals(avatar) && !board.isKalaha(i) && board.getPieceAt(i) > 0) {
				possibleMoves.add(i);
			}
		}
		int choice = randomizer.nextInt(possibleMoves.size());
		return new Move(possibleMoves.get(choice));
	}
}
