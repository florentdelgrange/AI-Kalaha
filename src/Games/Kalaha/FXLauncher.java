package Games.Kalaha;

import Core.Game;
import FX.Launcher;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Players.Player;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class FXLauncher extends Launcher<Board, Move, String, Player> {
	
	private final CheckBox emptyCapture;
	private final ComboBox<T>

	@Override
	public String getGameTitle() {
		return "Kalaha";
	}

	@Override
	public Node configPane() {
		return null;
	}

	@Override
	public Game<Board, Move, String, Player> construct() {
		// TODO Auto-generated method stub
		return null;
	}

}
