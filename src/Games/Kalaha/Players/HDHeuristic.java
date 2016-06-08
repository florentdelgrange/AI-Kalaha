package Games.Kalaha.Players;

import Games.Kalaha.Boards.Board;
import Games.Kalaha.Game;
import Games.Kalaha.Players.Player;

/**
 * Created by florentdelgrange on 12/05/16.
 */
public interface HDHeuristic {

    Double compute(Board board, String player);

}
