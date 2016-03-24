package Games.Kalaha;

import java.util.Map;

import Core.IGameRunner;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Players.Player;

/**
 * The kalaha game runs in a very typical way. The only difference is that
 * players need to be informed of the additional rules.
 * 
 * @author Fabian Pijcke
 */
public class GameRunner implements IGameRunner<Integer, Integer, Board, String, Game, Move, Player> {
	
	private final Game game;
	private final Map<String, Player> players;

	/**
	 * Constructs a Kalaha GameRunner.
	 * @param game
	 * @param players
	 */
	public GameRunner(Game game, Map<String, Player> players) {
		this.game = game;
		this.players = players;
	}
	
	@Override
	public Game getGame() {
		return game;
	}
	
	@Override
	public Map<String, Player> getPlayers() {
		return players;
	}
	
	@Override
	public void gameInit() {
		IGameRunner.super.gameInit();
		players.values().forEach(player -> {
			player.informEmptyCapture(game.getEmptyCapture());
			player.informLeftTokensGrantee(game.getLeftTokensGrantee());
		});
	}

}
