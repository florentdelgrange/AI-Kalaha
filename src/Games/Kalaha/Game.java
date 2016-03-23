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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import Core.IGame;
import Games.Kalaha.Boards.Board;

public class Game implements IGame<Integer, Integer, Board, String> {
	
	public static enum LeftTokensGrantee { ENDER, OWNER, NOBODY };
	
	private final Board board;
	private ArrayList<String> avatars;
	
	private final LeftTokensGrantee leftTokensGrantee;
	private final boolean emptyCapture;
	
	public Game(Board board, LeftTokensGrantee leftTokensGrantee, boolean emptyCapture, List<String> avatars) {
		this.avatars = new ArrayList<>();
		this.avatars.addAll(avatars);
		
		this.board = board;
		
		this.leftTokensGrantee = leftTokensGrantee;
		this.emptyCapture = emptyCapture;
	}
	
	@Override
	public Board getBoardClone() {
		return getBoard().readOnlyBoard();
	}
	
	public boolean getEmptyCapture() {
		return emptyCapture;
	}
	
	public LeftTokensGrantee getLeftTokensGrantee() {
		return leftTokensGrantee;
	}
	
	@Override
	public List<String> getPlayers() {
		return avatars;
	}

	@Override
	public Board getBoard() {
		return board;
	}
	
	@Override
	public String getCurrentPlayer() {
		return avatars.get(0);
	}
	
	public void setNextPlayer() {
		avatars.add(avatars.remove(0));
	}
	
	public void setPreviousPlayer() {
		avatars.add(0, avatars.remove(avatars.size() - 1));
	}

	@Override
	public boolean isGameEnded() {
		return getBoard().getSums(false, true).containsValue(0);
	}


	@Override
	public List<String> getWinners() {
		Map<String, Integer> scores = board.getScores(leftTokensGrantee);
		
		int m = scores.values().stream().reduce(0, Math::max);
		ArrayList<String> winners = new ArrayList<>();
		avatars.stream().filter(avatar -> scores.get(avatar) == m).forEach(avatar -> winners.add(avatar));
		
		return Collections.unmodifiableList(winners);
	}
	
	@Override
	public void disqualify(String avatar) {
		avatars.remove(avatar);
	}
}
