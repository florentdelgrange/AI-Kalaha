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

package Games.Chess;

import Board.Grid.InversibleGrid;

/**
 * @author Fabian Pijcke
 */
public class Board extends InversibleGrid<Piece, Coordinate> implements IBoard {
    
    public Board() {
        super(8, 8);
    }
    
    public Board(int w, int h) {
        super(w, h);
    }
    
    public Board(IBoard b) {
        super(b.getWidth(), b.getHeight());
        b.getPieces().forEach(p -> setPieceAt(b.getCoordinate(p), p));
    }
    
}
