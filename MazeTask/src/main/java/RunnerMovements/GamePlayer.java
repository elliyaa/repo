package RunnerMovements;

import Exceptions.MultipleStartingPointsException;
import Exceptions.NoStartingPointException;
import Exceptions.UnknownSymbolException;
import FileReader.FileScanner;
import MazeBuilder.SimpleMaze;

public class GamePlayer {

	public static void main(String args[]) throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		System.out.println(runner.getFinalPath());
		try {
			runner.findSolution(maze);
			System.out.println(runner.getFinalPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
