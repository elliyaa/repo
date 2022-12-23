package MazeBuilder;

import java.util.LinkedList;
import java.util.List;
import FileReader.FileScanner;
import MazeParts.Node;
import RunnerMovements.Runner;

public class SimpleMaze implements Maze {
	private FileScanner fileScanner;
	private Runner runner;
	
	private List<LinkedList<Node>> mazeStructure;
	private int x;
	private int y;
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public SimpleMaze(FileScanner fileScanner, Runner runner) {
		this.runner = runner;
		this.fileScanner = fileScanner;
		mazeStructure = fileScanner.getMazeStructure();
		x = runner.getRunnerX();
		y = runner.getRunnerY();
	}
	
	@Override
	public Node checkUp() {
		try {
			return mazeStructure.get(x-1).get(y);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public Node checkDown() {
		try {
			return mazeStructure.get(x+1).get(y);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public Node checkLeft() {	
		try {
			return mazeStructure.get(x).get(y-1);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public Node checkRight() {
		try {
			return mazeStructure.get(x).get(y + 1);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public void moveUp() {
		runner.setRunnerX(x - 1);
	}

	@Override
	public void moveDown() {
		runner.setRunnerX(x  + 1);
	}

	@Override
	public void moveLeft() {
		runner.setRunnerY(y  - 1);
	}

	@Override
	public void moveRight() {
		runner.setRunnerY(y  + 1);
	}
	
}
