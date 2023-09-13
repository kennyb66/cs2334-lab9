import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

	public static List<Integer> binarySearch(List<String> strings, String target, int fromIdx, int toIdx) {
		int midIdx = (fromIdx + toIdx) / 2;
		List<Integer> values = new ArrayList<Integer>();
		if (strings.get(midIdx).equals(target)) {
			values.add(midIdx);
		} else if (fromIdx > toIdx) {
			values.add(-1);
		} else {
			if (target.compareTo(strings.get(midIdx)) < 0) {
				values.add(midIdx);
				values.addAll(binarySearch(strings, target, fromIdx, midIdx - 1));
			} else {
				values.add(midIdx);
				values.addAll(binarySearch(strings, target, midIdx + 1, toIdx));
			}
		}
		return values;
	}
}