package FX;

import java.util.List;

import Board.IBoard;
import javafx.scene.Node;
import javafx.scene.text.Text;

public interface BoardMaker<Piece, Coordinate, Board extends IBoard<Piece, Coordinate>, Avatar> {

	default Node getConfigPane() {
		return new Text("");
	}
	
	Board getBoard(List<Avatar> avatars);
	
	default int getMinPlayers() {
		return 2;
	}
	
	default int getMaxPlayers() {
		return Integer.MAX_VALUE;
	}

}
