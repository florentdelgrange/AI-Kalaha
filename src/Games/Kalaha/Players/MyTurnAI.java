package Games.Kalaha.Players;
import java.util.ArrayList;
import java.util.List;
import Games.Kalaha.Move;
import Games.Kalaha.Boards.Board;

/**
 * An AI playing finishing sequences when possible and random moves otherwise.
 */
public class MyTurnAI extends RandomAI {
	/**
	 * Detects an uninterruptible winning sequence.
	 * @param pits pit values, temporarily modified but restored upon exit
	 * @param first the first move of the strategy (maybe null)
	 * @return first move index or null if such a sequence does not exist
	 */
	protected static Integer detectSequence(List<Integer> pits, Integer first) {
		boolean all_zeros = true;
		for (int i=0; i<pits.size(); i++) {
			int p = pits.get(i);
			all_zeros &= p==0;
			//the last token lands in the kalaha
			if (pits.size()-i == p) {
				//try
				pits.set(i, 0);
				for (int j=i+1; j<pits.size(); j++)
					pits.set(j, pits.get(j)+1);
				//recursive call
				Integer ret = detectSequence(pits, first==null ? i : first);
				//undo
				pits.set(i, p);
				for (int j=i+1; j<pits.size(); j++)
					pits.set(j, pits.get(j)-1);
				//use the recursive result
				if (ret != null)
					//success (by induction)
					return ret;
			}
		}
		if (all_zeros)
			//success (nothing to do!)
			return first;
		else
			//we can't empty each of our pits
			return null;
	}

	/**
	 * Detects an uninterruptible winning sequence.
	 * @param board Board instance
	 * @param avatar the current player's name
	 * @return first move or null if such a sequence does not exist
	 */
	public static Move detectSequence(Board board, String avatar) {
		ArrayList<Integer> pits = new ArrayList<>(); //indices
		ArrayList<Integer> values = new ArrayList<>(); //bean counts
		for (int i = 0; i < board.getLength(); ++i) {
			if (board.getPlayer(i).equals(avatar) && !board.isKalaha(i)) {
				pits.add(i);
				values.add(board.getPieceAt(i));
			}
		}
		Integer choice = detectSequence(values, null);
		if (choice == null)
			return null;
		else
			return new Move(pits.get(choice));
	}

	@Override
	public Move pickMove(String avatar) {
		Move move = detectSequence(board, avatar);
		if (move == null)
			return super.pickMove(avatar);
		else
			return move;
	}
}
