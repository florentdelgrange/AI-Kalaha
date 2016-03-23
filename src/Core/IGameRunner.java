package Core;

import java.util.Map;

import Board.IBoard;
import Move.Movement.IllegalMovementException;

public interface IGameRunner<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		DM extends Player<Piece, Coordinate, Board, Avatar, Game, Move>> {
	
	Game getGame();
	Map<Avatar, DM> getPlayers();
	
	default public void gameInit() {
		getPlayers().values().forEach(player -> {
			player.informBoard(getGame().getBoardClone());
			player.informAvatars(getGame().getPlayers());
		});
		getGame().getPlayers().forEach(avatar -> {
			getPlayers().get(avatar).informAvatar(avatar);
		});
	}
	
	default public void gameStep() throws IllegalMovementException {
		Avatar currentPlayer = getGame().getCurrentPlayer();
		Move move = getPlayers().get(currentPlayer).pickMove(currentPlayer);
		if (move.isLegal(getGame())) {
			move.apply(getGame());
		}
		else {
			throw new IllegalMovementException();
		}
	}
	
	default public void gameLoop() {
		while (!getGame().isGameEnded()) {
			try {
				gameStep();
			}
			catch (IllegalMovementException e) {
				try {
					gameStep();
				}
				catch (IllegalMovementException f) {
					getGame().disqualify(getGame().getCurrentPlayer());
				}
			}
		}
	}
	
	default public void gameFinish() {
		getPlayers().values().forEach(player -> player.informEnd(getGame().getWinners()));
	}
	
	/**
	 * Default behaviour of a game.
	 */
	default public void gameStart() {
		gameInit();
		gameLoop();
		gameFinish();
	}
	
}
