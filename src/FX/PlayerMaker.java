package FX;

import Board.IBoard;
import Core.IGame;
import Core.IMove;
import javafx.scene.Node;
import javafx.scene.text.Text;

public interface PlayerMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Player extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>> {
	
	@Override
	String toString();
	
	default Node getConfigPane() {
		return new Text("");
	}
	
	Player getPlayer();

}
