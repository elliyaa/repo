package RunnerMovements;

import MazeParts.Wall;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import FileReader.FileScanner;
import MazeBuilder.SimpleMaze;
import MazeParts.Exit;
import MazeParts.Node;

public class Runner implements MazeRunner {

	private FileScanner fileScanner;
	private SimpleMaze maze;

	private List<LinkedList<Node>> mazeStructure;;

	private int runnerX;
	private int runnerY;

	public boolean isNotAWallOrNull(Object node) {
		if (node instanceof Wall || node == null) {
			return false;
		}
		return true;
	}

	public int getRunnerX() {
		return runnerX;
	}

	public void setRunnerX(int runnerX) {
		this.runnerX = runnerX;
	}

	public int getRunnerY() {
		return runnerY;
	}

	public void setRunnerY(int runnerY) {
		this.runnerY = runnerY;
	}

	private List<Node> visitedPaths = new ArrayList<>();
	private Stack<Node> possiblePaths = new Stack<>();
	private List<String> finalPath = new ArrayList<>();

	public List<String> getFinalPath() {
		return finalPath;
	}

	private int movementCounter = 0;

	public Runner(FileScanner fileScanner) {

		this.fileScanner = fileScanner;
		this.mazeStructure = fileScanner.getMazeStructure();
		this.runnerX = fileScanner.getCurrentX();
		this.runnerY = fileScanner.getCurrentY();

	}

	public boolean checkIfNodeIsExit() {
		for (Exit e : fileScanner.getExits()) {
			if (e.getX() == this.runnerX && e.getY() == this.runnerY) {
				return true;
			}
		}
		return false;
	}

	// visited --

	@Override
	public List<String> findSolution(SimpleMaze simpleMaze) throws Exception {

		maze = simpleMaze;
		visitedPaths.add(mazeStructure.get(runnerX).get(runnerY));
		possiblePaths.push(mazeStructure.get(runnerX).get(runnerY));

		while (true) {

			if (this.checkIfNodeIsExit()) {

				break;
			}

			if (this.isNotAWallOrNull(maze.checkUp())) {

				if (!visitedPaths.contains(maze.checkUp())) {

					if (this.isNotAWallOrNull(maze.checkRight())) {

						if (!visitedPaths.contains(maze.checkRight())) {
							possiblePaths.add(maze.checkRight());
						}
					}

					if (this.isNotAWallOrNull(maze.checkDown())) {
						if (!visitedPaths.contains(maze.checkDown())) {
							possiblePaths.add(maze.checkDown());
						}
					}

					if (this.isNotAWallOrNull(maze.checkLeft())) {

						if (!visitedPaths.contains(maze.checkLeft())) {
							possiblePaths.add(maze.checkLeft());
						}
					}


					maze.moveUp();
					movementCounter++;
					finalPath.add("Up");
					visitedPaths.add(maze.checkUp());

					maze.setX(runnerX);
					maze.setY(runnerY);

					continue;

				}

			} 
			
			if (this.isNotAWallOrNull(maze.checkRight())) {

				if (!visitedPaths.contains(maze.checkRight())) {

					if (this.isNotAWallOrNull(maze.checkDown())) {
						if (!visitedPaths.contains(maze.checkDown())) {
							possiblePaths.add(maze.checkDown());
						}
					}

					if (this.isNotAWallOrNull(maze.checkLeft())) {

						if (!visitedPaths.contains(maze.checkLeft())) {
							possiblePaths.add(maze.checkLeft());
						}
					}

					maze.moveRight();
					movementCounter++;
					finalPath.add("Right");
					visitedPaths.add(maze.checkRight());

					maze.setX(runnerX);
					maze.setY(runnerY);

					continue;
				}

			} 
			
			if (this.isNotAWallOrNull(maze.checkDown())) {

				if (!visitedPaths.contains(maze.checkDown())) {

					if (this.isNotAWallOrNull(maze.checkLeft())) {

						if (!visitedPaths.contains(maze.checkLeft())) {
							possiblePaths.add(maze.checkLeft());
						}
					}

					maze.moveDown();
					movementCounter++;
					finalPath.add("Down");
					visitedPaths.add(maze.checkDown());

					maze.setX(runnerX);
					maze.setY(runnerY);

					continue;
				}

			} 

			if (this.isNotAWallOrNull(maze.checkLeft())) {

				if (!visitedPaths.contains(maze.checkLeft())) {

					maze.moveLeft();
					movementCounter++;
					finalPath.add("Left");
					visitedPaths.add(maze.checkLeft());

					maze.setX(runnerX);
					maze.setY(runnerY);

					continue;
				}

			} 

				if (possiblePaths.isEmpty()) {
					break;
				} else {
					Node previousPath = possiblePaths.pop();
					
					while(previousPath.getX() != runnerX && previousPath.getY() != runnerY) {
						
						
							String direction = finalPath.remove(finalPath.size() - 1);
							
							switch(direction) {
							
							case "Up": {Node node = maze.checkDown(); maze.moveDown(); runnerX = node.getX(); runnerY = node.getY(); maze.setX(runnerX); maze.setY(runnerY);} 
							case "Right": {Node node = maze.checkLeft(); maze.moveLeft(); runnerX = node.getX(); runnerY = node.getY(); maze.setX(runnerX); maze.setY(runnerY);} ;
							case "Down": {Node node = maze.checkUp(); maze.moveUp(); runnerX = node.getX(); runnerY = node.getY(); maze.setX(runnerX); maze.setY(runnerY);} 
							case "Left": {Node node = maze.checkRight(); maze.moveRight(); runnerX = node.getX(); runnerY = node.getY(); maze.setX(runnerX); maze.setY(runnerY);} 
							}
						
					}

					continue;
				}
	}
		return finalPath;

}
}
