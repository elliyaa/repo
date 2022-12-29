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
import MazeParts.Exit;
import MazeParts.Node;

public class Runner implements MazeRunner {

	private FileScanner fileScanner;
	private SimpleMaze maze;

	private List<LinkedList<Node>> mazeStructure;

	// new
	private HashMap<Node, List<Node>> checkPoints = new HashMap<>();

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
	private List<String> finalPath = new LinkedList<>();

	public List<String> getFinalPath() {
		return finalPath;
	}

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

		while (true) {

			if (this.checkIfNodeIsExit()) {

				break;
			}
			
			//UP

			if (this.isNotAWallOrNull(maze.checkUp())) {
				
				if (!visitedPaths.contains(maze.checkUp())) {
					
				///////

					if (this.isNotAWallOrNull(maze.checkRight())) {

						if (!visitedPaths.contains(maze.checkRight())) {
							
							possiblePaths.add(maze.checkRight());
							
							if(checkPoints.containsKey(mazeStructure.get(runnerX).get(runnerY))) {
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkRight());
							} else {
								checkPoints.put(mazeStructure.get(runnerX).get(runnerY), new ArrayList<Node>());
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkRight());
							}
						}
					}

					if (this.isNotAWallOrNull(maze.checkDown())) {
						if (!visitedPaths.contains(maze.checkDown())) {

							possiblePaths.add(maze.checkDown());
							
							if(checkPoints.containsKey(mazeStructure.get(runnerX).get(runnerY))) {
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkDown());
							} else {
								checkPoints.put(mazeStructure.get(runnerX).get(runnerY), new ArrayList<Node>());
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkDown());
							}

						}
					}

					if (this.isNotAWallOrNull(maze.checkLeft())) {

						if (!visitedPaths.contains(maze.checkLeft())) {
							
						possiblePaths.add(maze.checkLeft());
						
						if(checkPoints.containsKey(mazeStructure.get(runnerX).get(runnerY))) {
							checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkLeft());
						} else {
							checkPoints.put(mazeStructure.get(runnerX).get(runnerY), new ArrayList<Node>());
							checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkLeft());
						}

						}
					}

					maze.moveUp();
					finalPath.add("Up");
					visitedPaths.add(maze.checkUp());

					maze.setX(runnerX);
					maze.setY(runnerY);

					continue;

				}

			} 
			
			//RIGHT
			
			if (this.isNotAWallOrNull(maze.checkRight())) {
				

				if (!visitedPaths.contains(maze.checkRight())) {

					if (this.isNotAWallOrNull(maze.checkDown())) {
						if (!visitedPaths.contains(maze.checkDown())) {
							
							possiblePaths.add(maze.checkDown());
							
							if(checkPoints.containsKey(mazeStructure.get(runnerX).get(runnerY))) {
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkDown());
							} else {
								checkPoints.put(mazeStructure.get(runnerX).get(runnerY), new ArrayList<Node>());
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkDown());
							}
						}
					}

					if (this.isNotAWallOrNull(maze.checkLeft())) {

						if (!visitedPaths.contains(maze.checkLeft())) {
						
							possiblePaths.add(maze.checkLeft());
							
							if(checkPoints.containsKey(mazeStructure.get(runnerX).get(runnerY))) {
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkLeft());
							} else {
								checkPoints.put(mazeStructure.get(runnerX).get(runnerY), new ArrayList<Node>());
								checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkLeft());
							}

						}
					}

					
					maze.moveRight();
					finalPath.add("Right");
					visitedPaths.add(maze.checkRight());

					maze.setX(runnerX);
					maze.setY(runnerY);

					continue;
				}

			} 
			
			//DOWN
			
			if (this.isNotAWallOrNull(maze.checkDown())) {
		

				if (!visitedPaths.contains(maze.checkDown())) {

					if (this.isNotAWallOrNull(maze.checkLeft())) {

						if (!visitedPaths.contains(maze.checkLeft())) {
							
							
							possiblePaths.add(maze.checkLeft());
							
							checkPoints.put(mazeStructure.get(runnerX).get(runnerY), new ArrayList<Node>());
							checkPoints.get(mazeStructure.get(runnerX).get(runnerY)).add(maze.checkLeft());

						}
					}

					maze.moveDown();
					finalPath.add("Down");
					visitedPaths.add(maze.checkDown());

					maze.setX(runnerX);
					maze.setY(runnerY);

					continue;
				}

			} 
			
			//LEFT

			if (this.isNotAWallOrNull(maze.checkLeft())) {

				if (!visitedPaths.contains(maze.checkLeft())) {

					maze.moveLeft();
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
					
					//node part of key in checkPoints
					
					Node checkPointNode = null;
					
					for (Entry<Node, List<Node>> entry: checkPoints.entrySet()) {
						
						if(entry.getValue().contains(previousPath)) {
							
							checkPointNode = entry.getKey();
						}
					}
					
					while(true) {
						
						
							String direction = ((LinkedList<String>) finalPath).removeLast();
							
							if (direction.equals("Up")) {
								Node node = maze.checkDown(); 
								maze.moveDown(); 
								runnerX = node.getX(); 
								runnerY = node.getY(); 
								maze.setX(runnerX); 
								maze.setY(runnerY);
							}
							
							if (direction.equals("Right")) {
								Node node = maze.checkLeft(); 
								maze.moveLeft(); 
								runnerX = node.getX(); 
								runnerY = node.getY(); 
								maze.setX(runnerX); 
								maze.setY(runnerY);
							}
							
							if (direction.equals("Down")) {
								Node node = maze.checkUp(); 
								if(node != null) {
									maze.moveUp(); 
									runnerX = node.getX(); 
									runnerY = node.getY(); 
									maze.setX(runnerX); 
									maze.setY(runnerY);
							}
							}
								
							if (direction.equals("Left")) {
								Node node = maze.checkRight(); 
								maze.moveRight(); 
								runnerX = node.getX(); 
								runnerY = node.getY(); 
								maze.setX(runnerX); 
								maze.setY(runnerY);
								}	
							
							
							if(runnerX == checkPointNode.getX() && runnerY == checkPointNode.getY()){
								break;
							}
						
					}
					
					continue;

				}
				
				
				
				
	}
		return finalPath;

}
}
