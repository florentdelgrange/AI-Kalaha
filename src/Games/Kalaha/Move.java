package Games.Kalaha;

import Core.IMove;
import Games.Kalaha.Boards.Board;

public final class Move implements IMove<Board> {
	
	private int pit, tokens, captured = 0;
	
	public Move(Integer pit) {
		super();
		this.pit = pit;
	}
	
	public Integer getPit() {
		return pit;
	}
	
	private void add(Board board, int tokens) {
		int full = board.getLength();
		int half = full / 2;
		
		int count = tokens / (full - 1);
		int offset = tokens % (full - 1);
		
		if (count > 0) {
			for (int i = 0; i < full; ++i) {
				if (!board.isKalaha(i) || board.getAvatar(i) == board.getAvatar(pit)) {
					board.setPieceAt(i, board.getPieceAt(i) + count);
				}
			}
		}
		
		int i = pit;
		while (offset > 0) {
			++i;
			if (board.isKalaha(i) && board.getAvatar(i) != board.getAvatar(pit)) {
				continue;
			}
			if (offset == 1 && board.getPieceAt(i) == 0 && board.getAvatar(i) == board.getAvatar(pit)) {
				board.setPieceAt(i, 0);
				int opp = full - 2 - i;
				captured = board.getPieceAt(opp);
				int kalaha = pit / half * half + half - 1; 
				board.setPieceAt(opp, 0);
				board.setPieceAt(kalaha, board.getPieceAt(kalaha) + captured + 1);
			}
			else {
				board.setPieceAt(i, board.getPieceAt(i) + 1);
			}
			--offset;
		}
	}
	
	@Override
	public void apply(Board board) {
		tokens = board.getPieceAt(pit);
		board.setPieceAt(pit, 0);
		add(board, tokens);
	}

	@Override
	public void cancel(Board board) {
		// TODO
	}

	public boolean isLegal(Game game) {
		return game.getBoard().getPieceAt(pit) > 0 && game.getBoard().getAvatar(pit) == game.getCurrentPlayer().getAvatar() && !game.getBoard().isKalaha(pit);
	}
	
	public boolean hasReplay(Board board) {
		int offset = tokens % (board.getLength() - 1);
		return board.isKalaha(pit + offset) && board.getAvatar(pit + offset) == board.getAvatar(pit);
	}

}
