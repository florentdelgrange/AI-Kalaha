package Games.Kalaha.Players;
import java.util.List;
import java.util.AbstractList;

public class HDMyTurnAI_Test extends HDMyTurnAI {
	public static void main(String[] args) {
		int[] b = {0, 0, 1};
		List<Integer> bl = asList(b);
		System.out.println(detectSequence(bl, null));
		int[] c = {0, 2, 0};
		List<Integer> cl = asList(c);
		System.out.println(detectSequence(cl, null));
		int[] d = {0, 2, 1};
		List<Integer> dl = asList(d);
		System.out.println(detectSequence(dl, null));
		int[] e = {3, 1, 0};
		List<Integer> el = asList(e);
		System.out.println(detectSequence(el, null));
		int[] f = {3, 1, 1};
		List<Integer> fl = asList(f);
		System.out.println(detectSequence(fl, null));
	}

	/**
	 * Converts an array to a List.
	 * @url http://stackoverflow.com/a/1074063
	 */
	private static List<Integer> asList(final int[] is) {
		return new AbstractList<Integer>() {
			@Override public Integer get(int i) {return is[i];}
			@Override public int size() {return is.length;}
			@Override public Integer set(int i, Integer v) {
				int old = is[i];
				is[i] = v;
				return old;
			}
		};
	}
}
