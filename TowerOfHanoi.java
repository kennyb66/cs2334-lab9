import java.util.*;

public class TowerOfHanoi {
	private Map<Peg, Deque<Integer>> diskStacks = new HashMap<>();
	
	public TowerOfHanoi(int numDisks, Peg start) {
		Deque<Integer> numbers = new LinkedList<>();
		
		if(start == null) {
			throw new NullPointerException();
		}
		if(numDisks <= 0) {
			throw new IllegalArgumentException();
		}
		for(int i = 1; i <= numDisks; i++) {
			numbers.add(i);
		}
		diskStacks.put(Peg.LEFT, new LinkedList<>());
		diskStacks.put(Peg.MIDDLE, new LinkedList<>());
		diskStacks.put(Peg.RIGHT, new LinkedList<>());
		diskStacks.put(start, numbers);
	}
	public Deque<Integer> getDiskStack(Peg peg) {
		Deque<Integer> empty = new LinkedList<>();
		if(peg == null) {
			throw new NullPointerException();
		}
		if(diskStacks.containsKey(peg) == false) {
			return empty;
		}
		Deque<Integer> copy = new LinkedList<>();
		copy.addAll(diskStacks.get(peg));
		return copy;
	}
	public void moveDisk(Move move) {
		if(move == null) {
			throw new NullPointerException();
		}
		if(diskStacks.get(move.from).isEmpty()) {
			throw new IllegalArgumentException();
		}
		if(!diskStacks.get(move.to).isEmpty() && diskStacks.get(move.from).peek() > diskStacks.get(move.to).peek()) {
			throw new IllegalArgumentException();
		}
		diskStacks.get(move.to).push(diskStacks.get(move.from).pop());
	}
	public String toString() {
		Iterator<Integer> reverse = this.getDiskStack(Peg.LEFT).descendingIterator();		
		Iterator<Integer> reverse2 = this.getDiskStack(Peg.MIDDLE).descendingIterator();
		Iterator<Integer> reverse3 = this.getDiskStack(Peg.RIGHT).descendingIterator();
		
		Deque<Object> leftReverse = new LinkedList<>();
		Deque<Object> middleReverse = new LinkedList<>();
		Deque<Object> rightReverse = new LinkedList<>();

		while(reverse.hasNext()) {
			leftReverse.add(reverse.next());
		}
		while(reverse2.hasNext()) {
			middleReverse.add(reverse2.next());
		}
		while(reverse3.hasNext()) {
			rightReverse.add(reverse3.next());
		}
		return "  LEFT: " + leftReverse + System.lineSeparator() +
		       "MIDDLE: " + middleReverse + System.lineSeparator() +
		       " RIGHT: " + rightReverse;
	}
	public static List<Move> solve(int numDisks, Peg start, Peg end) {
		List<Move> toReturn = new ArrayList<Move>();
		if(numDisks < 0) {
			throw new IllegalArgumentException();
		}
		if(start == null || end == null) {
			throw new NullPointerException();
		}
		if(start.equals(end)) {
			return toReturn;
		}
		if(numDisks == 0) {
			return toReturn;
		}
		if(numDisks == 1) {
			Move move = Move.move(start, end);
			toReturn.add(move);
		}
		else {
			toReturn.addAll(solve(numDisks - 1, start, Peg.other(start, end)));
			Move move = Move.move(start, end);
			toReturn.add(move);
			toReturn.addAll(solve(numDisks - 1, Peg.other(start, end), end));
		}
		return toReturn;
}
}