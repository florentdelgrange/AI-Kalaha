package Games.Kalaha.Boards;

import java.util.ArrayList;
import java.util.List;

import FX.BoardMaker;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Uniform extends Board {
	
	public static class Maker implements BoardMaker<Integer, Integer, Board, String> {
		
		private final Spinner<Integer> pitsPerPlayerSpinner, tokensPerPitSpinner;
		
		public Maker() {
			pitsPerPlayerSpinner = new Spinner<>(1, Integer.MAX_VALUE / 2 - 2, 6);
			tokensPerPitSpinner = new Spinner<>(1, Integer.MAX_VALUE / 12, 4);
		}
		
		@Override
		public Node getConfigPane() {
			GridPane grid = new GridPane();
			grid.add(new Text("Pits per player : "), 0, 0);
			grid.add(pitsPerPlayerSpinner, 1, 0);
			grid.add(new Text("Tokens per pit : "), 0, 1);
			grid.add(tokensPerPitSpinner, 1, 1);
			return grid;
		}

		@Override
		public Board getBoard(List<String> avatars) {
			return new Uniform(pitsPerPlayerSpinner.getValue(), tokensPerPitSpinner.getValue(), avatars);
		}
		
		@Override
		public String toString() {
			return "Uniform";
		}
		
	}
	
	private final List<String> avatars;
	private final int playerLength; // Not necessary, but boring to compute everytime.

	public Uniform(int pitsPerPlayer, int tokensPerPit, List<String> avatars) {
		super(avatars.size() * (pitsPerPlayer + 1), avatars);
		
		this.playerLength = pitsPerPlayer + 1;
		
		this.avatars = new ArrayList<>();
		this.avatars.addAll(avatars);
		
		for (int i = 0; i < getLength(); ++i) {
			if (!isKalaha(i)) {
				setPieceAt(i, tokensPerPit);
			}
		}
	}

	@Override
	public String getPlayer(Integer c) {
		return avatars.get(coordinate(c) / playerLength);
	}

	@Override
	public boolean isKalaha(Integer c) {
		return coordinate(c + 1) % playerLength == 0;
	}
	
	@Override
	public List<Integer> getCaptures(Integer c) {
		if (isKalaha(c)) {
			return new ArrayList<>();
		}
		
		int baseC = c % playerLength;
		int baseOpp = (playerLength - 2) - baseC;
		int opp = c / playerLength * playerLength + baseOpp;
		
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 1; i < avatars.size(); ++i) {
			ret.add(coordinate(opp + i * playerLength));
		}
		return ret;
	}
	
	public Uniform(Board board, List<String> avatars) {
		super(board);
		this.avatars = avatars;
		this.playerLength = getLength() / avatars.size();
	}
	
	@Override
	public Board clone() {
		return new Uniform(this, avatars);
	}
}
