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

package Games.Nim;

import java.util.Map;

import Games.Nim.Boards.Default;
import Games.Nim.Players.Player;
import Piece.AnonymousToken;

/**
 * Terminal-oriented launcher for the game.
 * 
 * @author Fabian Pijcke
 */
public class GameRunner extends Core.GameRunner<AnonymousToken, Integer, Default, String, Game, Move, Player> {
	
	private final Map<String, Player> players;
	private final Game game;
	
	@Override
	public void init() {
		super.init();
		players.values().forEach(player -> player.informMaxLeap(game.getMaxLeap()));
	}
	
	public GameRunner(Game game, Map<String, Player> players) {
		super(game, players);
		this.game = game;
		this.players = players;
	}

}
