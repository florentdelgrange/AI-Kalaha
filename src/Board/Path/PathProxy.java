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

import Core.IPiece;
import Utils.IConsumer;

/**
 * 
 * @author Fabian Pijcke
 *
 * @param <P>
 */
public class PathProxy<P extends IPiece> implements IPath<P> {

	private final Path<P> pieces;

	/**
	 * Constructs a read-only version of a Path board.
	 * @param pieces
	 */
	public PathProxy(Path<P> pieces) {
		this.pieces = pieces;
	}
	
    @Override
    public int getLength() {
        return pieces.getLength();
    }
    
    @Override
    public P getPieceAt(Integer c) {
        return pieces.getPieceAt(c);
    }
    
    @Override
    public void forEach(IConsumer<P> c) {
        pieces.forEach(c);
    }
    
    @Override
    public boolean has(Integer c) {
        return pieces.has(c);
    }
}
