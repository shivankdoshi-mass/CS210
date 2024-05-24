package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {
	private List<Fragment> fragments;

	public Assembler(List<Fragment> fragments) {
		this.fragments = new ArrayList<>(fragments);
	}

	public List<Fragment> getFragments() {
		return fragments;
	}

	public boolean assembleOnce() {
		int maxOverlap = -1;
		Fragment mergeLeft = null;
		Fragment mergeRight = null;

		for (int i = 0; i < fragments.size(); i++) {
			for (int j = 0; j < fragments.size(); j++) {
				if (i != j) {
					Fragment left = fragments.get(i);
					Fragment right = fragments.get(j);
					int overlap = left.calculateOverlap(right);
					if (overlap > maxOverlap || (overlap == maxOverlap
							&& left.mergedWith(right).length() < mergeLeft.mergedWith(mergeRight).length())) {
						maxOverlap = overlap;
						mergeLeft = left;
						mergeRight = right;
					}
				}
			}
		}

		if (maxOverlap > 0) {
			Fragment merged = mergeLeft.mergedWith(mergeRight);
			fragments.remove(mergeLeft);
			fragments.remove(mergeRight);
			fragments.add(merged);
			return true;
		}
		return false;
	}

	public void assembleAll() {
		while (assembleOnce()) {
		}
	}
}
