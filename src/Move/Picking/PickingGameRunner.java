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

package Move.Picking;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import Board.IBoard;
import Core.IGame;
import Core.GameRunner;
import Core.IMove;
import Move.Movement.IllegalMovementException;

/**
 * @author Fabian Pijcke
 * @param <B>
 * @param <M>
 * @param <DM>
 */
public abstract class PickingGameRunner<B extends IBoard<?, ?>, M extends IMove<B>, A, DM extends IPickingDecisionMaker<? super B, M, A>> extends GameRunner<B, M, A, DM> {
	
	private IGame<B, A> game;
	private Map<A, DM> players;
	
	public PickingGameRunner(IGame<B, A> game, Map<A, DM> players) {
		super(game, players);
		
		this.players = players;
		this.game = game;
	}
    
	public abstract List<M> getPossibleMoves();
    
	@Override
    public void step() {
		List<M> allowedMoves = getPossibleMoves();
		DM curPlayer = players.get(game.getCurrentPlayer());
		
        curPlayer.informMoves(Collections.unmodifiableList(getPossibleMoves()));
        M m = curPlayer.pickMove();
        if (allowedMoves.contains(m)) {
        	m.apply(game.getBoard());
        }
        else {
        	throw new IllegalMovementException();
        }
    }
    
}
