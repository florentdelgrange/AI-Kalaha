package FX;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StringAvatarMaker implements AvatarMaker<String> {
	
	TextField nameField = new TextField();
	
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
