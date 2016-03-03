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

package Board.Path;

import Board.IBoardProxy;

/**
 * Common interface to Path and PathProxy, meant to be passed to both the user
 * (AI) and to the Game implementation.
 * 
 * @author Fabian Pijcke
 * @param <P>
 */
public interface IPath<P> extends IBoardProxy<P, Integer> {
	/**
	 * @return the length of the board.
	 */
	int getLength();

	/**
	 * @param c
	 * @return true if the coordinate belongs to the limits of the board.
	 */
	boolean has(Integer c);
}
