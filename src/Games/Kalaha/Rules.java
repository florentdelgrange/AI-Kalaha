package Games.Kalaha;

enum LeftTokensFor { OWNER, OPPONENT, NOBODY }

public class Rules {
	public final LeftTokensFor leftTokensFor;
	public final boolean emptyCapture;
	
	public Rules(LeftTokensFor leftTokensFor, boolean emptyCapture) {
		this.leftTokensFor = leftTokensFor;
		this.emptyCapture = emptyCapture;
	}
}
