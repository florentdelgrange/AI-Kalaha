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

import java.util.ArrayList;

import Board.IBoard;
import Core.IPiece;
import Utils.IConsumer;

/**
 * The complete implementation of a Path board. Instances of this class are meant to be passed to the game
 * implementation, not to the users or AIs.
 * 
 * @author Fabian Pijcke
 * @param <P> 
 */
public class Path<P extends IPiece> implements IBoard<P, Integer>, IPath<P> {
	
	private final ArrayList<P> elements;
	private final int length;
	
	/**
	 * Constructs an empty path.
	 * 
	 * @param length
	 */
	public Path(int length) {
		elements = new ArrayList<>(length);
		for (int i = 0; i < length; ++i) {
			elements.add(null);
		}
		
		this.length = length;
	}
	
	@Override
	public P getPieceAt(Integer c) {
		if (has(c)) {
			return elements.get(c);
		}
		return null;
	}
	
	@Override
	public int getLength() {
		return length;
	}
	
	@Override
	public void setPieceAt(Integer c, P e) {
		if (has(c)) {
			elements.set(c, e);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public void forEach(IConsumer<P> c) {
		elements.forEach(c.filter((v) -> v != null));
	}
	
	@Override
	public boolean has(Integer c) {
		return c >= 0 && c < length;
	}
	
}
