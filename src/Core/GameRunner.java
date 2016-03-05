package Core;

import java.util.Map;

import Board.IBoard;
import Board.IBoardProxy;
import Move.Movement.IllegalMovementException;

public class GameRunner<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		BoardProxy extends IBoardProxy<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, BoardProxy, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, BoardProxy, Avatar, Game>,
		DM extends IDecisionMaker<Piece, Coordinate, Board, BoardProxy, Avatar, Game, Move>> {
	
	private final Game game;
	private final Map<Avatar, DM> players;
	
	public GameRunner(Game game, Map<Avatar, DM> players) {
		this.players = players;
		this.game = game;
	}
	
	public void init() {
		players.values().forEach(player -> player.informBoard(game.getBoardProxy()));
	}
	
	public void step() {
		players.get(game.getCurrentPlayer()).pickMove().apply(game);
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
