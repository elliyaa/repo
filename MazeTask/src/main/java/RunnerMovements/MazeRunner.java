package RunnerMovements;


import java.util.List;

import MazeBuilder.SimpleMaze;

public interface MazeRunner {

	/**
	 * The method is used for generating a way out of the maze.
	 * 
	 * @param SimpleMaze object used for calling the methods of the Maze interface.
	 * @return A list of strings containing the directions used to solve the maze.
	 * */
	
	public List<String> findSolution(SimpleMaze simpleMaze);
}


