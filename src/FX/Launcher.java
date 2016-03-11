package FX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Launcher extends Application {

	private final GridPane gamePane = new GridPane();
	private final GridPane boardPane = new GridPane();
	private final BorderPane playersPane = new BorderPane();
	
	private final ComboBox<GameMaker> gameCombo = new ComboBox<>();
	private final ComboBox<BoardMaker> boardCombo = new ComboBox<>();
	
	public Launcher() {
		addGameMaker(new Games.Nim.GameMaker());
	}
	
	public void addGameMaker(GameMaker maker) {
		gameCombo.getItems().add(maker);
	}
	
	public static void main(String[] args) {
		Launcher.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("MetaBoard");
		
		gamePane.add(gameCombo, 0, 0);
		gameCombo.setOnAction(this::changeGame);
		
		boardPane.add(boardCombo, 0, 0);
		boardCombo.setOnAction(this::changeBoard);
		
		
		Button launchButton = new Button("Start game !");
		
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.getChildren().add(new TitledPane("Game", gamePane));
		root.getChildren().add(new TitledPane("Board", boardPane));
		root.getChildren().add(new TitledPane("Players", playersPane));
		root.getChildren().add(launchButton);
		
		primaryStage.setScene(new Scene(root, 640, 480));
		primaryStage.show();
		
	}
	
	public void changeGame(ActionEvent e) {
		boardCombo.getItems().clear();
		boardCombo.getItems().addAll(gameCombo.getValue().getBoardMakers());
		gamePane.add(gameCombo.getValue().getConfigPane(), 0, 1);
	}
	
	public void changeBoard(ActionEvent e) {
		boardPane.add(boardCombo.getValue().getConfigPane(), 0, 1);
	}
}
