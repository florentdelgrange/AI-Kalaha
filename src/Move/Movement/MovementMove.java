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

package Move.Movement;

import Board.IBoard;
import Core.IMove;

/**
 * This model of movement is indicated when the pieces are moved from one place
 * to another on the board. It is not adapted to games where moves can combine
 * several atomic movements (like checkers).
 * 
 * @author Fabian Pijcke
 * @param <C>
 * @param <P>
 * @param <D>
 */
public class MovementMove<P, C, D extends IBoard<P, C>> implements IMove<D> {

	private final C start, destination;
	private P startPiece, destinationPiece;

	/**
	 * Constructs a basic movement move.
	 * 
	 * @param start
	 * @param destination
	 */
	public MovementMove(C start, C destination) {
		this.start = start;
		this.destination = destination;
	}

	/**
	 * @return the start position of the move.
	 */
	public C getStart() {
		return start;
	}

	/**
	 * @return the destination position of the move.
	 */
	public C getDestination() {
		return destination;
	}

	@Override
	public void apply(D board) {
		startPiece = board.getPieceAt(getStart());
		destinationPiece = board.getPieceAt(getDestination());
		board.setPieceAt(getStart(), null);
		board.setPieceAt(getDestination(), startPiece);
	}

	@Override
	public void cancel(D board) {
		board.setPieceAt(getDestination(), destinationPiece);
		board.setPieceAt(getStart(), startPiece);
	}

}
