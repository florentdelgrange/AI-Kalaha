package Games.Kalaha;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Boards.Uniform;
import Games.Kalaha.Players.RandomAI;

public class Launcher {

	public static void main(String[] args) {
		String avatar1 = "Player 1";
		String avatar2 = "Player 2";
		
		Board board = new Uniform(6, 4, avatar1, avatar2);
		RandomAI ai1 = new RandomAI(avatar1);
		RandomAI ai2 = new RandomAI(avatar2);
		
		new Game(ai1, ai2, board).start();
	}

}
