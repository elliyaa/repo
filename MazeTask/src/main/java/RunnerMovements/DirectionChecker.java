package RunnerMovements;

import MazeParts.Node;

@FunctionalInterface
public interface DirectionChecker {
	public Node checkDirection();
}
