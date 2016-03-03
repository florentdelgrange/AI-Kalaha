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
package Games.Kalaha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Core.GameHistory;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Players.Player;
import Move.Movement.IllegalMovementException;

public class Game extends GameHistory<Board, Move, Player> {
	
	private final Board board;
	private Player currentPlayer;
	
	public Game(Player player1, Player player2, Board board) {
		super(new ArrayList<>(Arrays.asList(player1, player2)));
		
		this.board = board;
		player1.informBoard(board);
		player2.informBoard(board);
		this.currentPlayer = player1;
	}

	@Override
	public Board getBoard() {
		return board;
	}
	
	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public boolean isGameEnded() {
		return getBoard().player1Sum() == 0 || getBoard().player2Sum() == 0;
	}

	@Override
	public List<Player> getWinners() {
		int player1Score = getBoard().player1Sum() + getBoard().getPieceAt(getBoard().getLength() / 2 - 1).getSize();
		int player2Score = getBoard().player2Sum() + getBoard().getPieceAt(getBoard().getLength() - 1).getSize();
		
		System.out.println("Player 1 : " + player1Score);
		System.out.println("Player 2 : " + player2Score);
		
		if (player1Score == player2Score) {
			return getPlayers();
		}
		else if (player1Score > player2Score) {
			return Collections.singletonList(getPlayers().get(0));
		}
		else {
			return Collections.singletonList(getPlayers().get(1));
		}
	}
	
	public void printStatus() {
		if (getTurn() > 0) {
			System.out.println("Last move : " + getMove(-1).getPit());
		}
		
		for (int i = 0; i < getBoard().getLength() / 2 - 1; i++) {
			System.out.print("	" + getBoard().getPieceAt(i).getSize());
		}
		System.out.print("\n" + getBoard().getPieceAt(-1).getSize());
		for (int i = 0; i < getBoard().getLength() / 2; i++) {
			System.out.print("	");
		}
		System.out.println(getBoard().getPieceAt(getBoard().getLength() / 2 - 1).getSize());
		for (int i = 0; i < getBoard().getLength() / 2 - 1; i++) {
			System.out.print("	" + getBoard().getPieceAt(getBoard().getLength() - 2 - i).getSize());
		}
		
		System.out.println("\nNext player : " + getCurrentPlayer().getAvatar().getName());
		System.out.println();
		
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		}
	}
	
	@Override
	public void applyMove(Move move) {
		if (!move.isLegal(this)) {
			throw new IllegalMovementException();
		}
			
		super.applyMove(move);
		if (!move.hasReplay(getBoard())) {
			currentPlayer = (currentPlayer == getPlayers().get(0) ? getPlayers().get(1) : getPlayers().get(0));
		}
	}
	
	public void step() {
		Move move = getCurrentPlayer().pickMove();
		try {
			applyMove(move);
		}
		catch (IllegalMovementException e) {
			move = getCurrentPlayer().pickMove();
			try {
				applyMove(move);
			}
			catch (IllegalMovementException f) {
				// TODO make other player win.
			}
		}
	}
}
