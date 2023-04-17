package RunnerMovements;

import MazeParts.Wall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import FileReader.FileScanner;
import MazeBuilder.SimpleMaze;
import MazeParts.Node;

public class Runner implements MazeRunner {
	
	public Runner(FileScanner fileScanner) {
		this.fileScanner = fileScanner;
		this.mazeStructure = fileScanner.getMazeStructure();
		this.runnerX = fileScanner.getCurrentX();
		this.runnerY = fileScanner.getCurrentY();
	}

	private FileScanner fileScanner;
	private SimpleMaze maze;

	private List<LinkedList<Node>> mazeStructure;
	private HashMap<Node, List<Node>> checkPoints = new HashMap<>();
	private List<Node> visitedPaths = new ArrayList<>();
	private Stack<Node> possiblePaths = new Stack<>();
	private List<String> finalPath = new LinkedList<>();

	private HashMap<String, MovementChecker> actions = new HashMap<>();
	private HashMap<String, DirectionChecker> directions = new HashMap<>();
	private List<String> directionsList = new ArrayList<>();

	private int runnerX;
	private int runnerY;
	private boolean isADeadEnd = true;

	public int getRunnerX() {
		return runnerX;
	}

	public int getRunnerY() {
		return runnerY;
	}

	public void setRunnerX(int runnerX) {
		this.runnerX = runnerX;
	}

	public void setRunnerY(int runnerY) {
		this.runnerY = runnerY;
	}

	public List<String> getFinalPath() {
		return finalPath;
	}

	public boolean checkIfNodeIsExit() {
		return fileScanner.getExits().stream().anyMatch(e -> e.getX() == this.runnerX && e.getY() == this.runnerY);
	}

	public boolean isNotAWallOrNull(Object node) {
		return !(node instanceof Wall || node == null);
	}

	public void setCoordinates() {
		maze.setX(runnerX);
		maze.setY(runnerY);
	}

	public void checkForExcessPaths() {
		 possiblePaths.removeAll(visitedPaths);
	}

	public void checkMove(Node node) {
		if (this.isNotAWallOrNull(node) && !visitedPaths.contains(node)) {
			possiblePaths.add(node);
			this.addToCheckPoints(mazeStructure.get(runnerX).get(runnerY), node);
		}
	}

	public void addToCheckPoints(Node currentNode, Node adjacentNode) {
		if (checkPoints.containsKey(currentNode)) {
			checkPoints.get(currentNode).add(adjacentNode);
		} else {
			checkPoints.put(currentNode, new ArrayList<Node>());
			checkPoints.get(currentNode).add(adjacentNode);
		}
	}

	public List<String> backtrackPath() {
		if (possiblePaths.isEmpty()) {
			finalPath.clear();
			return finalPath;
		}

		Node previousPath = possiblePaths.pop();
		Node checkPointNode = null;

		for (Entry<Node, List<Node>> entry : checkPoints.entrySet()) {
			if (entry.getValue().contains(previousPath)) {
				checkPointNode = entry.getKey();
			}
		}

		while (true) {

			String direction = ((LinkedList<String>) finalPath).removeLast();
			Node node = null;

			switch (direction) {
			case "Up":
				node = maze.checkDown();
				maze.moveDown();
				break;
			case "Right":
				node = maze.checkLeft();
				maze.moveLeft();
				break;
			case "Down":
				node = maze.checkUp();
				maze.moveUp();
				break;
			case "Left":
				node = maze.checkRight();
				maze.moveRight();
				break;
			}

			this.setCoordinates();
			runnerX = node.getX();
			runnerY = node.getY();

			if (runnerX == checkPointNode.getX() && runnerY == checkPointNode.getY()) {
				break;
			}
		}
		return finalPath;
	}

	public List<String> runThroughMaze() {

		if (this.checkIfNodeIsExit()) {
			return finalPath;
		}

		for (int i = 0; i < directions.size(); i++) {

			Node result = directions.get(directionsList.get(i)).checkDirection();

			if (this.isNotAWallOrNull(result) && !visitedPaths.contains(result)) {

				isADeadEnd = false;
				for (int j = i + 1; j < directions.size(); j++) {
					Node node = directions.get(directionsList.get(j)).checkDirection();
					this.checkMove(node);
				}

				actions.get(directionsList.get(i)).move();
				finalPath.add(directionsList.get(i));
				visitedPaths.add(result);

				this.checkForExcessPaths();
				this.setCoordinates();

				break;
			}
		}

		if (isADeadEnd) {
			this.backtrackPath();
		}

		if (!finalPath.isEmpty()) {
			isADeadEnd = true;
			this.runThroughMaze();
		}
		return finalPath;
	}

	@Override
	public List<String> findSolution(SimpleMaze simpleMaze) {

		if (fileScanner.getExits().size() == 0) {
			return finalPath;
		}

		maze = simpleMaze;
		visitedPaths.add(mazeStructure.get(runnerX).get(runnerY));

		actions.put("Up", maze::moveUp);
		actions.put("Right", maze::moveRight);
		actions.put("Down", maze::moveDown);
		actions.put("Left", maze::moveLeft);

		directions.put("Up", maze::checkUp);
		directions.put("Right", maze::checkRight);
		directions.put("Down", maze::checkDown);
		directions.put("Left", maze::checkLeft);

		directionsList.add("Up");
		directionsList.add("Right");
		directionsList.add("Down");
		directionsList.add("Left");

		return this.runThroughMaze();
	}
}