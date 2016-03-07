/*
 Copyright 2015-2016 Fabian Pijcke

 This file is part of MetaBoard.

 MetaBoard is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 MetaBoard is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with MetaBoard. If not, see <http://www.gnu.org/licenses/>.
 */

package Board;

import Utils.IConsumer;

/**
 * The board contains the current state of the game.
 * 
 * @author Fabian Pijcke
 * @param <P>
 *            The class of pieces that will be put on the board.
 * @param <C>
 *            The coordinates used on the board.
 */
public interface IBoard<P, C> {

	/**
	 * Puts the given piece at the given coordinate. The behaviour is not
	 * specified if the piece was already present on the board.
	 * 
	 * @param coord
	 * @param piece
	 */
	void setPieceAt(C coord, P piece);
	
	/**
	 * @param coord
	 * @return the piece at coordinate coord.
	 */
	P getPieceAt(C coord);
	
	/**
	 * @param coord
	 * @return true if and only if the coordinate coord belongs to the board.
	 */
	boolean has(C coord);
	
	/**
	 * applies a function to each of the piece on the board (skipping nulls).
	 * @param consumer
	 */
	void forEach(IConsumer<P> consumer);

	/**
	 * @return true if this board is read only. This should be the case for
	 *         boards passed to players so that they can choose their next move.
	 *         When a board is read only, the method setPieceAt will throw a
	 *         ReadOnlyBoardException if called.
	 */
	boolean isReadOnly();
	
}
