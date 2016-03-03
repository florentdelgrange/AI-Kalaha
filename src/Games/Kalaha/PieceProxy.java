package Games.Kalaha;

public class PieceProxy implements IPiece {
	
	private final Piece piece;
	
	public PieceProxy(Piece piece) {
		this.piece = piece;
	}
	
	@Override
	public int getSize() {
		return piece.getSize();
	}
}
