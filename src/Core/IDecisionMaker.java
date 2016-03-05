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

package Core;

import java.util.List;

import Board.IBoard;
import Board.IBoardProxy;

/**
 * An entity making decisions. This can be either an AI or a user interface a
 * human can use to input moves.
 * 
 * @author Fabian Pijcke
 * @param <Piece>
 * @param <Coordinate>
 * @param <BoardProxy>
 * @param <Avatar>
 * @param <Move>
 */
public interface IDecisionMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		BoardProxy extends IBoardProxy<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, BoardProxy, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, BoardProxy, Avatar, Game>> {

	/**
	 * This method will be called at the beginning of each game to inform the
	 * decision maker on which board he has to play.
	 * 
	 * @param board
	 */
	void informBoard(BoardProxy board);

	/**
	 * This method will be called when the decision maker has to pick a move. He
	 * then has to give back a move that the game will try to apply.
	 * 
	 * @return the move chosen by the decision maker.
	 */
	Move pickMove();

	/**
	 * When the game is over, this method is called so that the decision maker
	 * knows who the winners are.
	 * 
	 * @param winners
	 */
	void informEnd(List<Avatar> winners);

}
