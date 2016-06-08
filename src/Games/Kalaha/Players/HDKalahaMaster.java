package Games.Kalaha.Players;

import Games.Kalaha.Move;

/**
 * This AI combines the best from both KalahaAI (the best implemented AI)
 * with HDMyTurnAI which always win when a very specific sequence is detected.
 *
 * Results are stored in analysis.raw.csv, have fun peeking at it.
 * A pie chart was created using LibreOffice Calc in analysis.ods.
 */
public class HDKalahaMaster extends HDMiniMaxAI {
	public HDKalahaMaster() {
		super(HDHeuristicSet.kalahaMaximizer);
	}

	@Override
	public Move pickMove(String s) {
		Move move = HDMyTurnAI.detectSequence(board, s);
		if (move == null)
			return super.pickMove(s);
		else
			return move;
	}
}
