package FX;

import Board.IBoard;
import Core.IGame;
import Core.IMove;
import javafx.scene.Node;

public interface PlayerMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Player extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>> {
	
	@Override
	String toString();
	
	Node getConfigPane();
	
	Player getPlayer();

}
