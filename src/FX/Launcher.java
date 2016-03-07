package FX;

import Board.IBoard;
import Core.IGame;
import Core.Player;
import Core.IMove;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Launcher<B extends IBoard<?, ?>, M extends IMove<B>, A, DM extends Player<B, M, A>> extends Application {
	abstract public String getGameTitle();
	abstract public Node configPane();
	abstract public IGame<B, M, A, DM> construct();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(getGameTitle());
		
		VBox mainPane = new VBox();
		mainPane.getChildren().add(new TitledPane("Game configuration", configPane()));
	}
}
