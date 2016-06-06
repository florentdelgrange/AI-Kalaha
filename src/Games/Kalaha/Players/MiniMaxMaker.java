package Games.Kalaha.Players;

import java.lang.reflect.InvocationTargetException;

import Board.IBoard;
import Core.IMove;
import Game.IGame;
import FX.PlayerMaker;
import Games.Kalaha.Players.AI.Heuristic;
import Games.Kalaha.Players.AI.MiniMaxAI;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;

/**
 * MiniMax instance maker, based on Fabian Pijcke's work
 * @see SimplePlayerMaker
 */
public class MiniMaxMaker implements PlayerMaker<Integer, Integer, Board, String, Game, Move, Player> {
	private final Heuristic heuristic;
	private final String name;

	public MiniMaxMaker(String name, Heuristic heuristic) {
		this.name = name;
		this.heuristic = heuristic;
	}

	@Override
	public Player getPlayer() {
		try {
			return MiniMaxAI.class
				.getConstructor(Heuristic.class).newInstance(heuristic);
		}
		catch (IllegalAccessException | InstantiationException |
				NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to instanciate MiniMaxAI");
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
