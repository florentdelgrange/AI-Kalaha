package Games.Nim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import FX.AvatarMaker;
import FX.BoardMaker;
import FX.PlayerMaker;
import FX.StringAvatarMaker;
import Games.Nim.Boards.Default;
import Games.Nim.Players.HumanDialogBox;
import Games.Nim.Players.Player;
import Games.Nim.Players.RandomAI;
import Piece.AnonymousToken;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

public class GameMaker implements FX.GameMaker<AnonymousToken, Integer, Default, String, Game, Move, Player, GameRunner> {
	
	private final Spinner<Integer> maxLeapSpinner = new Spinner<>(1, Integer.MAX_VALUE, 3);
	
	@Override
	public String toString() {
		return "Nim";
	}

	@Override
	public Node getConfigPane() {
		return new VBox(maxLeapSpinner);
	}

	@Override
	public Game getGame(Default board, List<String> avatars) {
		return new Game(avatars, board, maxLeapSpinner.getValue());
	}
	
	@Override
	public GameRunner getGameRunner(Game game, Map<String, Player> players) {
		return new GameRunner(game, players);
	}

	@Override
	public List<BoardMaker<AnonymousToken, Integer, Default, String>> getBoardMakers() {
		ArrayList<BoardMaker<AnonymousToken, Integer, Default, String>> ret = new ArrayList<>();
		ret.add(new Default.Maker());
		return ret;
	}

	@Override
	public List<PlayerMaker<AnonymousToken, Integer, Default, String, Game, Move, Player>> getPlayerMakers() {
		ArrayList<PlayerMaker<AnonymousToken, Integer, Default, String, Game, Move, Player>> ret = new ArrayList<>();
		ret.add(new HumanDialogBox.Maker());
		ret.add(new RandomAI.Maker());
		return ret;
	}
	
	@Override
	public AvatarMaker<String> getNewAvatarMaker() {
		return new StringAvatarMaker();
	}

}
