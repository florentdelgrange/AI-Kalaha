package Games.Kalaha;

import java.util.Map;

import Core.IGameRunner;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Players.Player;

public class GameRunner implements IGameRunner<Integer, Integer, Board, String, Game, Move, Player> {
	
	private final Game game;
	private final Map<String, Player> players;

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
