package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.SearchProblem;

public class EightPuzzle implements SearchProblem<List<Integer>> {
	private List<Integer> startingValues;

	public EightPuzzle(List<Integer> startingValues) {
		this.startingValues = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		return new ArrayList<>(startingValues);
	}

	@Override
	public boolean isGoal(List<Integer> state) {
		List<Integer> goalState = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0);
		return state.equals(goalState);
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		List<List<Integer>> successors = new ArrayList<>();
		int zeroIndex = currentState.indexOf(0);
		int[] moves = {-1, 1, -3, 3};  // left, right, up, down (3x3 grid)

		for (int move : moves) {
			int newIndex = zeroIndex + move;
			if (newIndex >= 0 && newIndex < 9 && !(zeroIndex % 3 == 2 && move == 1) && !(zeroIndex % 3 == 0 && move == -1)) {
				List<Integer> newState = new ArrayList<>(currentState);
				// Swap 0 with the adjacent element
				newState.set(zeroIndex, newState.get(newIndex));
				newState.set(newIndex, 0);
				successors.add(newState);
			}
		}
		return successors;
	}
}
