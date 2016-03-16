package FX;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StringAvatarMaker implements AvatarMaker<String> {
	
	private static int playerId = 0;
	
	TextField nameField = new TextField("Player " + ++playerId);
	
	@Override
	public Node getConfigPane() {
		HBox hbox = new HBox();
		hbox.getChildren().add(new Text("Name "));
		hbox.getChildren().add(nameField);
		return hbox;
	}

	@Override
	public String getAvatar() {
		return nameField.getText();
	}

}
