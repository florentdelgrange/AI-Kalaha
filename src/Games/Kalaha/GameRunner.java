package Games.Kalaha;

import java.util.Map;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Players.Player;

public class GameRunner extends Core.GameRunner<Integer, Integer, Board, String, Game, Move, Player> {
	
	private final Game game;
	private final Map<String, Player> players;

	public GameRunner(Game game, Map<String, Player> players) {
		super(game, players);
		this.players = players;
		this.game = game;
	}
	
	@Override
	public void init() {
		super.init();
		players.values().forEach(player -> {
			player.informEmptyCapture(game.getEmptyCapture());
			player.informLeftTokensGrantee(game.getLeftTokensGrantee());
		});
	}
	
	@Override
	public void step() {
		super.step();
		game.printStatus();
	}

}
