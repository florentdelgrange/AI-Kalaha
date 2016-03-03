package Games.Kalaha;

import Core.IMove;
import Games.Kalaha.Boards.Board;

public final class Move implements IMove<Board> {
	
	private int pit, tokens;
	
	public Move(Integer pit) {
		super();
		this.pit = pit;
	}
	
	public Integer getPit() {
		return pit;
	}
	
	private void add(Board board, int tokens, int multiplier) {
		int count = tokens / (board.getLength() - 1);
		int offset = tokens % (board.getLength() - 1);
		
		System.out.println(count);
		System.out.println(offset);
		
		for (int i = 1; i <= board.getLength(); i++) {
			if (board.isKalaha(pit + i) && board.getAvatar(pit + i) != board.getAvatar(pit)) {
				continue;
			}
			board.getPieceAt(pit + i).increment(multiplier * (i > offset ? count : count + 1));
		}
	}
	
	@Override
	public void apply(Board board) {
		tokens = board.getPieceAt(pit).getSize();
		board.getPieceAt(pit).empty();
		add(board, tokens, 1);
	}

	@Override
	public void cancel(Board board) {
		add(board, tokens, -1);
		board.getPieceAt(pit).empty();
		board.getPieceAt(pit).increment(tokens);
	}

	public boolean isLegal(Game game) {
		return game.getBoard().getPieceAt(pit).getSize() > 0 && game.getBoard().getAvatar(pit) == game.getCurrentPlayer().getAvatar();
	}
	
	public boolean hasReplay(Board board) {
		int offset = tokens % (board.getLength() - 1);
		return board.isKalaha(pit + offset) && board.getAvatar(pit + offset) == board.getAvatar(pit);
	}

}
