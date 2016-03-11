package FX;

import Board.IBoard;
import javafx.scene.Node;

public interface BoardMaker<Piece, Coordinate, Board extends IBoard<Piece, Coordinate>> {

	@Override
	String toString();
	
	Node getConfigPane();
	
	Board getBoard();
}
