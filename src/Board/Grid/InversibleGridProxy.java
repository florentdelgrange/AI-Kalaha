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

package Board.Grid;

import java.util.List;

/**
 * Proxy for the InversibleMap2D {@link GridProxy}
 * 
 * @author Fabian Pijcke
 * @param <P>
 * @param <C>
 * @param <D>
 */
public class InversibleGridProxy<P, C extends GridCoordinate, D extends InversibleGrid<P, C>> extends GridProxy<P, C, D> implements IInversibleGrid<P, C> {
    
    private final D pieces;

    /**
     * Creates a read-only version of a Map2DProxy.
     * @param pieces
     */
    public InversibleGridProxy(D pieces) {
        super(pieces);
        this.pieces = pieces;
    }
    
    @Override
    public C getCoordinate(P piece) {
        return pieces.getCoordinate(piece);
    }

    @Override
    public List<P> getPieces() {
        return pieces.getPieces();
    }
}
