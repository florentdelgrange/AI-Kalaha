package Core;

import java.util.Map;

import Board.IBoard;
import Move.Movement.IllegalMovementException;

public class GameRunner<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		DM extends Player<Piece, Coordinate, Board, Avatar, Game, Move>> {
	
	private final Game game;
	private final Map<Avatar, DM> players;
	
	public GameRunner(Game game, Map<Avatar, DM> players) {
		this.players = players;
		this.game = game;
	}
	
	public void init() {
		players.values().forEach(player -> {
			player.informBoard(game.getBoardClone());
			player.informAvatars(game.getPlayers());
		});
		game.getPlayers().forEach(avatar -> {
			players.get(avatar).informAvatar(avatar);
		});
	}
	
	public void step() throws IllegalMovementException {
		Avatar currentPlayer = game.getCurrentPlayer();
		Move move = players.get(currentPlayer).pickMove(currentPlayer);
		if (move.isLegal(game)) {
			move.apply(game);
		}
		else {
			throw new IllegalMovementException();
		}
	}
	
	public void mainLoop() {
		while (!game.isGameEnded()) {
			try {
				step();
			}
			catch (IllegalMovementException e) {
				try {
					step();
				}
				catch (IllegalMovementException f) {
					game.disqualify(game.getCurrentPlayer());
				}
			}
		}
	}
	
	public void finish() {
		players.values().forEach(player -> player.informEnd(game.getWinners()));
	}
	
	/**
	 * Default behaviour of a game.
	 */
	public void start() {
		init();
		mainLoop();
		finish();
	}
	
}
