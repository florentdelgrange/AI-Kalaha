package Games.Kalaha.Players.AI;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Players.Player;

/**
 * Created by florentdelgrange on 12/05/16.
 */
public interface Heuristic {

    Double compute(Board board, String player);

}
