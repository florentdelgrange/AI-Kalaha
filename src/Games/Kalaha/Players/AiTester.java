package Games.Kalaha.Players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;

import Games.Kalaha.*;
import Games.Kalaha.Boards.*;
import Games.Kalaha.Players.AI.*;
import FX.SimplePlayerMaker;
import FX.PlayerMaker;

/**
 * Command-line artificial intelligence implementation comparator.
 * Since most AIs (except RandomAI and MyTurnAI) are deterministic algorithms,
 * we only compare two of them twice (the order is important).
 * Starting from a random board could be tempting but how would we know
 * when someone gets advantaged? We often can't explore the whole search space.
 */
public class AiTester {
	public static final Player pitsAI = new MiniMaxAI(HeuristicSet.pitsMaximizer);
	public static final Player kalahaAI = new MiniMaxAI(HeuristicSet.kalahaMaximizer);
	public static final Player kalahaMinimizerAI = new MiniMaxAI(HeuristicSet.kalahaMinimizer);
	public static final Player sqPitsAI = new MiniMaxAI(HeuristicSet.squaredPitsMinimizer);

	/**
	 * Instantiates a player object for each name in the input list.
	 * @param in_args input list
	 * @param out_avatars output avatar (String) list
	 * @param out_players output player (Object) list
	 * @throws ClassNotFoundException when trying to use a non-existing player
	 */
	protected static void getPlayers(String[] in_args,
			List<String> out_avatars, Map<String, Player> out_players)
			throws ClassNotFoundException {
		List<PlayerMaker> pms = new ArrayList<>();
		pms.add(new SimplePlayerMaker("random", RandomAI.class));
		pms.add(new SimplerPlayerMaker("pit_max", pitsAI));
		pms.add(new SimplerPlayerMaker("kalaha_max", kalahaAI));
		pms.add(new SimplerPlayerMaker("kalaha_min", kalahaMinimizerAI));
		pms.add(new SimplerPlayerMaker("sq_pit_min", sqPitsAI));
		pms.add(new SimplePlayerMaker("my_turn", MyTurnAI.class));
		boolean first = true;
		for (String arg: in_args) {
			if (first) {
				first = false;
				continue;
			}
			boolean found = false;
			for (PlayerMaker maker: pms) {
				if (maker.toString().equals(arg)) {
					int i = 1;
					String s;
					while (out_players.get(s=arg+(i==1 ? "" : i)) != null)
						i++;
					out_avatars.add(s);
					out_players.put(s, (Player)maker.getPlayer());
					found = true;
					break;
				}
			}
			if (!found)
				throw new ClassNotFoundException(arg);
		}
	}

	/**
	 * Instantiates a Board from a description.
	 * @param name "uniform" or a board file name
	 * @param avatars existing avatar list
	 * @return new Board instance
	 * @throws IllegalArgumentException when passed an invalid name
	 */
	protected static Board getBoard(String name, List<String> avatars)
			throws IllegalArgumentException {
		if (name.endsWith(".board")) {
			Path path = Paths.get("src", "Games", "Kalaha", "Boards", name);
			return new FromFile(path, avatars);
		}
		else if (name.equals("uniform"))
			return new Uniform(6, 4, avatars);
		else
			throw new IllegalArgumentException(name);
	}

	/**
	 * Entry point
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		try {
			List<String> avatars = new ArrayList<>();
			Map<String, Player> players = new HashMap<>();
			getPlayers(args, avatars, players);
			String ltg_s = "OWNER"; //TODO
			Game.LeftTokensGrantee ltg = Game.LeftTokensGrantee.valueOf(ltg_s);
			boolean emptyCaptures = false; //TODO
			for (int i=0; i<Integer.parseInt(args[0]); i++) {
				Board board = getBoard("uniform", avatars); //TODO
				Game game = new Game(board, ltg, emptyCaptures, avatars);
				GameRunner runner = new GameRunner(game, players) {
					public void gameFinish() {
						game.getWinners().stream().forEach(avatar ->
							System.out.println(avatar));
					}
				};
				runner.gameStart();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
