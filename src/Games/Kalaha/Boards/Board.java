package Games.Kalaha.Boards;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Board.Path;
import Games.Kalaha.Game;

public abstract class Board extends Path<Integer> {
	
	private final List<String> avatars;
	
	public Board(int length, List<String> avatars) {
		super(length);
		for (int i = 0; i < length; ++i) {
			super.setPieceAt(i, 0);
		}
		this.avatars = avatars;
	}
	
	/**
	 * Constructs a read-only board from a given board.
	 * 
	 * @param board
	 */
	protected Board(Board board) {
		super(board);
		this.avatars = board.avatars;
	}
	
	@Override
	public Integer getPieceAt(Integer c) {
		return super.getPieceAt(coordinate(c));
	}
	
	@Override
	public void setPieceAt(Integer c, Integer p) {
		super.setPieceAt(coordinate(c), p);
	}
	
	public abstract String getPlayer(Integer c);
	public abstract boolean isKalaha(Integer c);
	public abstract List<Integer> getCaptures(Integer c);
	
	public int playerSum(String player) {
		int sum = 0;
		for (int i = 0; i < getLength(); ++i) {
			if (getPlayer(i).equals(player)) {
				sum += getPieceAt(i);
			}
		}
		return sum;
	}
	
	protected final Integer coordinate(Integer c) {
		return Math.floorMod(c, getLength());
	}
	
	public HashMap<String, Integer> getSums(boolean kalahas, boolean pits) {
		HashMap<String, Integer> sums = new HashMap<>();
		avatars.forEach(avatar -> sums.put(avatar, 0));
		for (int i = 0; i < getLength(); ++i) {
			if (isKalaha(i) && kalahas || !isKalaha(i) && pits) {
				String avatar = getPlayer(i);
				sums.put(avatar, sums.get(avatar) + getPieceAt(i));
			}
		}
		return sums;
	}
	
	public Map<String, Integer> getScores(Game.LeftTokensGrantee leftTokensGrantee) {
		HashMap<String, Integer> scores;
		switch (leftTokensGrantee) {
		case OWNER:
			scores = getSums(true, true);
			break;
		case ENDER:
			scores = getSums(true, false);
			int bonus = getSums(false, true).values().stream().reduce(0, (a, b) -> a + b);
			String ender = avatars.get(avatars.size() - 1);
			scores.put(ender, scores.get(ender) + bonus);
			break;
		default:
		case NOBODY:
			scores = getSums(true, false);
		}
		
		return Collections.unmodifiableMap(scores);
	}
	
	@Override
	public abstract Board readOnlyBoard();
	
	@Override
	public abstract Board clone();

}
