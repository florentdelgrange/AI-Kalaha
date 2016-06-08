package Games.Kalaha.Players;

import java.lang.reflect.InvocationTargetException;

import FX.PlayerMaker;
import FX.SimplePlayerMaker;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;

/**
 * HDMiniMax instance maker, based on Fabian Pijcke's work
 * @see SimplePlayerMaker
 */
public class HDMiniMaxMaker implements PlayerMaker<Integer, Integer, Board, String, Game, Move, Player> {
	private final HDHeuristic heuristic;
	private final String name;

	public HDMiniMaxMaker(String name, HDHeuristic heuristic) {
		this.name = name;
		this.heuristic = heuristic;
	}

	@Override
	public Player getPlayer() {
		try {
			return HDMiniMaxAI.class
				.getConstructor(HDHeuristic.class).newInstance(heuristic);
		}
		catch (IllegalAccessException | InstantiationException |
				NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to instanciate HDMiniMaxAI");
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
