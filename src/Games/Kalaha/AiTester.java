package Games.Kalaha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;

import Games.Kalaha.Players.*;
import Games.Kalaha.Boards.*;
import FX.SimplePlayerMaker;
import FX.PlayerMaker;

public class AiTester {
	protected static void getPlayers(String[] in_args,
			List<String> out_avatars, Map<String, Player> out_players)
			throws ClassNotFoundException {
		List<PlayerMaker> pms = new ArrayList<>();
		pms.add(new SimplePlayerMaker("random", RandomAI.class));
		pms.add(new SimplePlayerMaker("pit_max", PitsAI.class));
		pms.add(new SimplePlayerMaker("kalaha_max", KalahaAI.class));
		pms.add(new SimplePlayerMaker("kalaha_min", KalahaMinimizerAI.class));
		pms.add(new SimplePlayerMaker("sq_pit_min", SquaredPitsAI.class));
		pms.add(new SimplePlayerMaker("my_turn", MyTurnAI.class));
		int i = 0;
		for (String arg: in_args) {
			boolean found = false;
			for (PlayerMaker maker: pms) {
				if (maker.toString().equals(arg)) {
					out_avatars.add(i+"_"+arg);
					out_players.put(i+"_"+arg, (Player)maker.getPlayer());
					i++;
					found = true;
					break;
				}
			}
			if (!found)
				throw new ClassNotFoundException(arg);
		}
	}

	protected static Board getBoard(String name, List<String> avatars)
			throws ClassNotFoundException {
		if (name.endsWith(".board")) {
			Path path = Paths.get("src", "Games", "Kalaha", "Boards", name);
			return new FromFile(path, avatars);
		}
		else if (name.equals("uniform"))
			return new Uniform(6, 4, avatars);
		else
			throw new ClassNotFoundException(name);
	}

	public static void main(String[] args) {
		try {
			List<String> avatars = new ArrayList<>();
			Map<String, Integer> scores = new HashMap<>();
			Map<String, Player> players = new HashMap<>();
			getPlayers(args, avatars, players);
			avatars.stream().forEach(avatar -> scores.put(avatar, 0));
			Board board = getBoard("uniform", avatars); //TODO
			String ltg_s = "OWNER"; //TODO
			Game.LeftTokensGrantee ltg = Game.LeftTokensGrantee.valueOf(ltg_s);
			boolean emptyCaptures = false; //TODO
			for (int i=0; i<10; i++) {
				Game game = new Game(board, ltg, emptyCaptures, avatars);
				GameRunner runner = new GameRunner(game, players) {
					public void gameFinish() {
						game.getWinners().stream().forEach(avatar ->
							scores.put(avatar, scores.get(avatar)+1));
					}
				};
				runner.gameStart();
			}
			scores.entrySet().stream().forEach(entry ->
				System.out.println(entry.getKey()+"\t"+entry.getValue()));
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
