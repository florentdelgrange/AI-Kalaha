package FX;

import java.util.List;

import Board.IBoard;
import Core.IGame;
import Core.IMove;
import javafx.scene.Node;

public interface GameMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Player extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>> {

	@Override
	String toString();
	
	Node getConfigPane();
	List<BoardMaker<Piece, Coordinate, Board>> getBoardMakers();
	List<PlayerMaker<Piece, Coordinate, Board, Avatar, Game, Move, Player>> getPlayerMakers();
	
	Game getGame(Board board, List<Avatar> players);
	
}