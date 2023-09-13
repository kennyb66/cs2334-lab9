
public enum Peg {
	LEFT, MIDDLE, RIGHT;

	public static Peg other(Peg p1, Peg p2) throws IllegalArgumentException, NullPointerException {
		if (p1 == null || p2 == null) {
			throw new NullPointerException();
		}
		if (p1 == p2) {
			throw new IllegalArgumentException();
		}
		if (p1 != RIGHT && p2 != RIGHT) {
			return RIGHT;
		} else if (p1 != MIDDLE && p2 != MIDDLE) {
			return MIDDLE;
		} else
			return LEFT;
	}
}
