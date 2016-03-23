package Games.Kalaha.Players;

import java.util.ArrayList;
import java.util.Random;

import FX.PlayerMaker;
import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Boards.Board;

public class RandomAI extends Player {
	
	public static class Maker implements PlayerMaker<Integer, Integer, Board, String, Game, Move, Player> {
		@Override
		public Player getPlayer() {
			return new RandomAI();
		}
		
		@Override
		public String toString() {
			return "Random-playing AI";
		}
	}
	
	private final Random randomizer = new Random();
	
	@Override
	public Move pickMove(String avatar) {
		ArrayList<Integer> possibleMoves = new ArrayList<>();
		for (int i = 0; i < board.getLength(); ++i) {
			if (board.getPlayer(i).equals(avatar) && !board.isKalaha(i) && board.getPieceAt(i) > 0) {
				possibleMoves.add(i);
			}
		}
		int choice = randomizer.nextInt(possibleMoves.size());
		return new Move(possibleMoves.get(choice));
	}
}
