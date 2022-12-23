package MazeTests;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Exceptions.MultipleStartsException;
import Exceptions.NoStartingPointException;
import Exceptions.UnknownSymbolException;
import FileReader.FileScanner;
import MazeBuilder.SimpleMaze;
import MazeParts.Path;
import MazeParts.Wall;

public class Tests {

	@Test
	public void fileScannerWithProperFile() throws FileNotFoundException, UnknownSymbolException, MultipleStartsException, NoStartingPointException {

		final Integer expected = 3;
		Integer result;

		FileScanner fileScanner = new FileScanner("src/resources/maze.txt");
		Wall testingObject = (Wall) fileScanner.getBoard().get(0).get(3);
		result = testingObject.getY();
		Assertions.assertEquals(expected, result);
	}

	@Test
	public void fileScannerWithtwoStarts() throws MultipleStartsException, UnknownSymbolException, NoStartingPointException {
		
			Assertions.assertThrows(InputMismatchException.class, ( ) -> new FileScanner("C:\\workspace\\e_tasks\\M\\texts\\twoStarts.txt"));
		}
	
	@Test
	public void fileScannerWithNoStarts() throws NoStartingPointException, UnknownSymbolException, MultipleStartsException {
		
			Assertions.assertThrows(NoStartingPointException.class, () ->  new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\noStart.txt"));
	}

	@Test
	public void fileScannerWithIncorrectSymbols() throws MultipleStartsException, NoStartingPointException {
	
			Assertions.assertThrows(NoStartingPointException.class, () ->  new FileScanner("C:\\workspace\\e_tasks\\M\\texts\\incorrectSymbols.txt"));
		}


	@Test
	public void checkDownTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {
		
		final boolean expected = true;
		boolean result;
		
		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(1).get(3);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		result = maze.checkDown();
		Assertions.assertEquals(expected, result);
		
	}

	@Test
	public void checkUpTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {
		
		final boolean expected = true;
		boolean result;
		
		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(2).get(3);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		result = maze.checkUp();
		Assertions.assertEquals(expected, result);
	}

	@Test
	public void checkLeftTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {

		final boolean expected = true;
		boolean result;
		
		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(3).get(2);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		result = maze.checkLeft();
		Assertions.assertEquals(expected, result);

	}

	@Test
	public void checkRigthTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {
		
		final boolean expected = true;
		boolean result;

		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(3).get(4);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		result = maze.checkRight();
		Assertions.assertEquals(expected, result);

	}

	@Test
	public void moveDownTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {

		final Integer expected = 6;
		Integer result;

		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(5).get(1);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		if (maze.checkDown()) {
			maze.moveDown();
		}
		result = fileScanner.getCurrentX();
		Assertions.assertEquals(expected, result);
	}

	@Test
	public void moveUpTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {
		
		final Integer expected = 4;
		Integer result;

		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(5).get(1);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		if(maze.checkUp()) {
			maze.moveUp();
		}
		result = fileScanner.getCurrentX();
		Assertions.assertEquals(expected, result);
	
	}

	@Test
	public void moveLeftTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {

		final Integer expected = 0;
		Integer result;

		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(5).get(1);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		if (maze.checkLeft()) {
			maze.moveLeft();
		}
		result = fileScanner.getCurrentY();
		Assertions.assertEquals(expected, result);

	}

	@Test
	public void moveRightTest() throws UnknownSymbolException, MultipleStartsException, NoStartingPointException {

		final Integer expected = 2;
		Integer result;

		FileScanner fileScanner = new FileScanner("‪C:\\workspace\\e_tasks\\M\\texts\\maze.txt");
		SimpleMaze maze = new SimpleMaze(fileScanner);
		Path testingObject = (Path) fileScanner.getBoard().get(5).get(1);
		fileScanner.setCurrentX(testingObject.getX());
		fileScanner.setCurrentY(testingObject.getY());
		if (maze.checkRight()) {
			maze.moveRight();
		}
		
		result = fileScanner.getCurrentY();
		Assertions.assertEquals(expected, result);

	}
	
	@Test
	public void findSolutionTest() {

	}

}