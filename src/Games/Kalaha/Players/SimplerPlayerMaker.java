package Games.Kalaha.Players;

import FX.PlayerMaker;
import Games.Kalaha.Players.*;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Move;

/**
 * This only stores its constructor arguments since
 * AIs can't easily receive parameters from the UI.
 */
public class SimplerPlayerMaker implements PlayerMaker<Integer, Integer, Board, String, Game, Move, Player> {
	private final String name;
	private final Player player;

	public SimplerPlayerMaker(String name, Player player) {
		this.name = name;
		this.player = player;
	}

	@Override
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
