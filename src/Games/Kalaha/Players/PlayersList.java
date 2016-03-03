package Games.Kalaha.Players;

import java.util.ArrayList;

public class PlayersList {

	/**
	 * @return the list of classes playable using a graphical interface.
	 */
	public static ArrayList<Class<? extends Player>> playersListFX() {
		ArrayList<Class<? extends Player>> list = new ArrayList<>();
		list.add(RandomAI.class);

		return list;
	}
}
