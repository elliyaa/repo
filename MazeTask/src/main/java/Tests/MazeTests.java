package Tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Exceptions.MultipleStartingPointsException;
import Exceptions.NoStartingPointException;
import Exceptions.UnknownSymbolException;
import FileReader.FileScanner;
import MazeBuilder.SimpleMaze;
import MazeParts.Node;
import RunnerMovements.Runner;

public class MazeTests {

	@Test
	public void fileScannerWithProperFile() throws FileNotFoundException, UnknownSymbolException,
			NoStartingPointException, NoStartingPointException, MultipleStartingPointsException {
	
		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");

		final Node expected = fileScanner.getExits().get(0);
		Node result = fileScanner.getMazeStructure().get(1).get(2);

		assertEquals(expected, result);
	}

	@Test
	public void fileScannerWithtwoStarts() throws MultipleStartingPointsException {

		Assertions.assertThrows(MultipleStartingPointsException.class,
				() -> new FileScanner("src/resources/textTwoStarts.txt"));
	}

	@Test
	public void fileScannerWithNoStarts() throws NoStartingPointException {

		Assertions.assertThrows(NoStartingPointException.class,
				() -> new FileScanner("src/resources/textNoStart.txt"));
	}

	@Test
	public void fileScannerWithIncorrectSymbols() throws UnknownSymbolException {

		Assertions.assertThrows(UnknownSymbolException.class,
				() -> new FileScanner("src/resources/textIncorrectSymbols.txt"));
	}

	@Test
	public void checkDownTest()
			throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final Node expected = null;
		Node result = maze.checkDown();
		
		assertEquals(expected, result);
	}

	@Test
	public void checkUpTest() throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final Node expected = fileScanner.getMazeStructure().get(2).get(0);
		Node result = maze.checkUp();
		
		assertEquals(expected, result);
	}

	@Test
	public void checkLeftTest()
			throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final Node expected = null;
		Node result = maze.checkLeft();
		
		assertEquals(expected, result);

	}

	@Test
	public void checkRigthTest()
			throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		
		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final Node expected = fileScanner.getMazeStructure().get(3).get(1);
		Node result = maze.checkRight();
		
		assertEquals(expected, result);
	}

	@Test
	public void moveDownTest()
			throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze2.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final int expected = 3;
		
		maze.moveDown();
		
		int result = runner.getRunnerX();
		
		assertEquals(expected, result);
	}

	@Test
	public void moveUpTest() throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final Node expected = fileScanner.getMazeStructure().get(2).get(0);
		Node result;
		
		maze.moveUp();
		
		result = fileScanner.getMazeStructure().get(runner.getRunnerX()).get(runner.getRunnerY());
		assertEquals(expected, result);

	}

	@Test
	public void moveLeftTest()
			throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze2.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final int expected = 3;
		
		maze.moveLeft();
		
		int result = runner.getRunnerY();
		
		assertEquals(expected, result);
	}

	@Test
	public void moveRightTest()
			throws UnknownSymbolException, MultipleStartingPointsException, NoStartingPointException {

		FileScanner fileScanner = new FileScanner("src/resources/maze2.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);
		
		final int expected = 5;
		
		maze.moveRight();
		
		int result = runner.getRunnerY();
		
		assertEquals(expected, result);
	}

	@Test
	public void findSolutionTest() throws FileNotFoundException, UnknownSymbolException, NoStartingPointException,
			NoStartingPointException, MultipleStartingPointsException {

		final List<String> expected = Arrays.asList("Up", "Right", "Right", "Up");
		List<String> result;

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Runner runner = new Runner(fileScanner);
		SimpleMaze maze = new SimpleMaze(fileScanner, runner);

		result = runner.findSolution(maze);

		assertEquals(expected, result);
	}

}
