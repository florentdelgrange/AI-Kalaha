package FX;

import Board.IBoard;
import Core.IGame;
import Core.IMove;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class SimplePlayerMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Player extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>>
	implements PlayerMaker<Piece, Coordinate, Board, Avatar, Game, Move, Player> {
	
	private final Class<? extends Player> playerClass;
	private final String name;
	
	public SimplePlayerMaker(String name, Class<? extends Player> playerClass) {
		this.name = name;
		this.playerClass = playerClass;
	}

	@Override
	public Node getConfigPane() {
		return new Text("");
	}

	@Override
	public Player getPlayer() {
		try {
			return playerClass.newInstance();
		}
		catch (IllegalAccessException | InstantiationException e) {
			throw new RuntimeException("Unable to instanciate player of class " + playerClass);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

}
