package FX;

import javafx.scene.Node;

public interface AvatarMaker<Avatar> {
	
	public Node getConfigPane();
	
	public Avatar getAvatar();

}
