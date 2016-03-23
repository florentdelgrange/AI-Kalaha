package FX;

import java.util.List;
import java.util.Map;

import Board.IBoard;
import Core.IGame;
import Core.IMove;
import javafx.scene.Node;
import javafx.scene.text.Text;

public interface GameMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Player extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>,
		GameRunner extends Core.IGameRunner<Piece, Coordinate, Board, Avatar, Game, Move, Player>> {

	@Override
	String toString();
	
	default Node getConfigPane() {
		return new Text("");
	}
	
	List<BoardMaker<Piece, Coordinate, Board, Avatar>> getBoardMakers();
	List<PlayerMaker<Piece, Coordinate, Board, Avatar, Game, Move, Player>> getPlayerMakers();
	AvatarMaker<Avatar> getNewAvatarMaker();
	
	Game getGame(Board board, List<Avatar> avatars);
	GameRunner getGameRunner(Game game, Map<Avatar, Player> players);
	
}