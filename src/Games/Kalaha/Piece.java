package Games.Kalaha;

public class Piece implements IPiece {
	
	private int size;
	
	public Piece(int size) {
		this.size = size;
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	public void empty() {
		size = 0;
	}
	
	public void increment() {
		++size;
	}
	
	public void increment(int n) {
		size += n;
	}
}
