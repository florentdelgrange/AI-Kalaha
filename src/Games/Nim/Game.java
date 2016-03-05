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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Games.Nim.Players.Player;
import Move.Movement.IllegalMovementException;

/**
 * The game of Nim is played on a path with a token. The players move in turn
 * the stone towards position 0. The maximum displacement is a parameter of the
 * game. The initial position of the stone is another parameter. The last player
 * is automatically declared winner. There is always a single winner. There must
 * be at least one player and there is no upper bound.
 * 
 * The players list is handled in a round-robin cushion : The first player in
 * the list is the one to play next move. When he has picked a move, he is
 * thrown back at the end of the players list.
 * 
 * @author Fabian Pijcke
 */
public class Game implements Core.Game<Board, Move, String, Player> {

	private Board board;
	private ArrayList<Player> players;
	private int maxLeap;

	/**
	 * Constructs a game of Nim with the given list of players (the first player
	 * plays first, and so on), the given maximum leap and the given token
	 * initial position.
	 * 
	 * @param players
	 * @param maxLeap
	 * @param initialPosition
	 */
	public Game(ArrayList<Player> players, int maxLeap, int initialPosition) {
		this.players = players;
		this.maxLeap = maxLeap;
		this.board = new Board(initialPosition + 1);

		for (Player p : players) {
			p.informBoard(new BoardProxy(this.board));
			p.informMaxLeap(maxLeap);
		}
		
		printStatus();
	}

	/**
	 * @return the maximum leap allowed in this game.
	 */
	public int getMaxLeap() {
		return maxLeap;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Player getCurrentPlayer() {
		return getPlayers().get(0);
	}

	@Override
	public boolean isGameEnded() {
		return board.getTokenPosition() == 0 || getPlayers().size() == 1;
	}

	@Override
	public List<Player> getWinners() {
		int winner = (board.getTokenPosition() == 0) ? players.size() - 1 : 0;
		return Collections.singletonList(getPlayers().get(winner));
	}

	@Override
	public void applyMove(Move move) {
		if (!move.isLegal(this)) {
			throw new IllegalMovementException();
		}
		move.apply(getBoard());
		players.add(players.remove(0));

		if (isGameEnded()) {
			List<String> avatars = new ArrayList<>();
			for (Player p : getWinners()) {
				avatars.add(p.getAvatar());
			}
			players.forEach((player) -> player.informEnd(avatars));
		}
		
		printStatus();
	}

	/**
	 * Prints the current status of the game (token position, next player).
	 */
	public void printStatus() {
		if (isGameEnded()) {
			System.out.println("Winner : " + getWinners().get(0).getAvatar());
		} else {
			System.out.println("Token position : " + board.getTokenPosition());
			System.out.println("Player in hand : " + getCurrentPlayer().getAvatar());
			System.out.println();
		}
	}
	
	@Override
	public void disqualify(Player player) {
		// TODO
	}

}
