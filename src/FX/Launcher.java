package FX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Board.IBoard;
import Core.GameRunner;
import Core.IGame;
import Core.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Launcher extends Application {

	private final GridPane gamePane = new GridPane();
	private final GridPane boardPane = new GridPane();
	private final VBox playerPanes = new VBox();
	
	private final ComboBox<GameMaker> gameCombo = new ComboBox<>();
	private final ComboBox<BoardMaker> boardCombo = new ComboBox<>();
	
	private final Button addPlayerButton = new Button("Add player");
	private final Button resetPlayersButton = new Button("Reset players");
	
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
		
		GridPane playersPane = new GridPane();
		playersPane.setHgap(10);
		playersPane.setVgap(20);
		addPlayerButton.setDisable(true);
		addPlayerButton.setOnAction(this::addPlayer);
		resetPlayersButton.setDisable(true);
		resetPlayersButton.setOnAction(this::resetPlayers);
		playersPane.add(addPlayerButton, 0, 0);
		playersPane.add(resetPlayersButton, 1, 0);
		playersPane.add(playerPanes, 0, 1, 2, 1);
		
		Button launchButton = new Button("Start game !");
		launchButton.setOnAction(this::launchGame);
		
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
		gamePane.add(gameCombo.getValue().getConfigPane(), 0, 1);
		boardCombo.getItems().clear();
		boardCombo.getItems().addAll(gameCombo.getValue().getBoardMakers());
	}
	
	public void changeBoard(ActionEvent e) {
		addPlayerButton.setDisable(false);
		resetPlayersButton.setDisable(false);
		boardPane.add(boardCombo.getValue().getConfigPane(), 0, 1);
		resetPlayers(null);
	}
	
	public void resetPlayers(ActionEvent e) {
		playerPanes.getChildren().clear();
		for (int i = 0; i < boardCombo.getValue().getMinPlayers(); ++i) {
			addPlayer(null);
		}
	}

	public void addPlayer(ActionEvent e) {
		if (playerPanes.getChildren().size() < boardCombo.getValue().getMaxPlayers()) {
			playerPanes.getChildren().add(new PlayerPane());
		}
	}
	
	public void launchGame(ActionEvent e) {
		IBoard board = boardCombo.getValue().getBoard();
		List avatars = new ArrayList();
		playerPanes.getChildren().forEach(pp -> avatars.add(((PlayerPane) pp).getAvatar()));
		IGame game = gameCombo.getValue().getGame(board, avatars);
		
		Map players = new HashMap();
		playerPanes.getChildren().forEach(pp -> players.put(((PlayerPane) pp).getAvatar(), ((PlayerPane) pp).getPlayer()));
		GameRunner runner = gameCombo.getValue().getGameRunner(game, players);
		runner.start();
	}
	
	public class PlayerPane extends GridPane {
		
		private final AvatarMaker avatarMaker;
		private final ComboBox<PlayerMaker> playerCombo = new ComboBox<>();
		
		public PlayerPane() {
			super();
			
			avatarMaker = gameCombo.getValue().getNewAvatarMaker();
			
			playerCombo.getItems().addAll(gameCombo.getValue().getPlayerMakers());
			playerCombo.setOnAction(this::changePlayer);
			Button dropButton = new Button("drop player");
			dropButton.setOnAction(this::drop);
			
			this.add(playerCombo, 0, 0);
			this.add(dropButton, 1, 0);
		}
		
		public Object getAvatar() {
			return avatarMaker.getAvatar();
		}
		
		public Player getPlayer() {
			return playerCombo.getValue().getPlayer();
		}
		
		public void changePlayer(ActionEvent e) {
			this.add(avatarMaker.getConfigPane(), 0, 1, 2, 1);
			this.add(playerCombo.getValue().getConfigPane(), 0, 2, 2, 1);
		}
		
		public void drop(ActionEvent e) {
			if (playerPanes.getChildren().size() > boardCombo.getValue().getMinPlayers()) {
				playerPanes.getChildren().remove(this);
			}
		}
		
	}
	
}
