package search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Searcher<T> {
	private final SearchProblem<T> searchProblem;

	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
	}

	public List<T> findSolution() {
		Queue<List<T>> frontier = new ArrayDeque<>();
		Set<T> visited = new HashSet<>();

		List<T> initialPath = new ArrayList<>();
		initialPath.add(searchProblem.getInitialState());
		frontier.add(initialPath);
		visited.add(searchProblem.getInitialState());

		while (!frontier.isEmpty()) {
			List<T> currentPath = frontier.poll();
			T currentState = currentPath.get(currentPath.size() - 1);

			if (searchProblem.isGoal(currentState)) {
				return currentPath;
			}

			for (T successor : searchProblem.getSuccessors(currentState)) {
				if (!visited.contains(successor)) {
					visited.add(successor);
					List<T> newPath = new ArrayList<>(currentPath);
					newPath.add(successor);
					frontier.add(newPath);
				}
			}
		}
		return new ArrayList<>();  // Return empty list if no solution found
	}

	public final boolean isValidSolution(List<T> solution) {
		if (solution.isEmpty() || !solution.get(0).equals(searchProblem.getInitialState())) {
			return false;
		}
		for (int i = 1; i < solution.size(); i++) {
			T prev = solution.get(i - 1);
			T curr = solution.get(i);
			if (!searchProblem.getSuccessors(prev).contains(curr)) {
				return false;
			}
		}
		return searchProblem.isGoal(solution.get(solution.size() - 1));
	}
}
