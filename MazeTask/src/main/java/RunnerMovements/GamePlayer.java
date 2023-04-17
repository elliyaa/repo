package RunnerMovements;

import FileReader.FileScanner;
import MazeBuilder.SimpleMaze;

public class GamePlayer {

	public static void main(String args[]) throws Exception {

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		try {
			runner.findSolution(maze);
			System.out.println(runner.getFinalPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
