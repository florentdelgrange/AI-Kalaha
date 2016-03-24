package Game;

import Board.IBoard;

/**
 * In several games, the round-robin principle is applied to players. I.e., the
 * players play in turn, one after the other always in the same order. This
 * interface provides default implementations for this behaviour.
 * 
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 */
public interface RoundRobin<Piece, Coordinate, Board extends IBoard<Piece, Coordinate>, Avatar> extends IGame<Piece, Coordinate, Board, Avatar> {

	@Override
	public default Avatar getCurrentPlayer() {
		return getPlayers().get(0);
	}
	
	/**
	 * Forwards in the player order.
	 */
	public default void setNextPlayer() {
		getPlayers().add(getPlayers().remove(0));
	}
	
	/**
	 * Backwards in the player order.
	 */
	public default void setPreviousPlayer() {
		getPlayers().add(0, getPlayers().remove(getPlayers().size() - 1));
	}
	
	@Override
	public default void disqualify(Avatar avatar) {
		getPlayers().remove(avatar);
	}
}
