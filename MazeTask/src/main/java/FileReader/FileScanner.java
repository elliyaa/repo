package FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Exceptions.MultipleStartingPointsException;
import Exceptions.NoStartingPointException;
import Exceptions.UnknownSymbolException;
import MazeParts.Exit;
import MazeParts.Node;
import MazeParts.Path;
import MazeParts.Start;
import MazeParts.Wall;

public class FileScanner {

	private List<Node> exits = new ArrayList<>();
	private List<LinkedList<Node>> mazeStructure = new LinkedList<LinkedList<Node>>();
	private HashMap<String, SymbolChecker> symbols = new HashMap<>();

	private int currentX;
	private int currentY;
	private int lineCounter = 0;
	private int startCounter = 0;

	public void fillSymbolsList() {
		symbols.put(" ", (lineCounter, i) -> mazeStructure.get(lineCounter).add(new Path(lineCounter, i)));
		symbols.put("*", (lineCounter, i) -> mazeStructure.get(lineCounter).add(new Wall(lineCounter, i)));
		symbols.put("S", (lineCounter, i) -> {
			mazeStructure.get(lineCounter).add(new Start(lineCounter, i));
			startCounter++;
			currentX = lineCounter;
			currentY = i;
		});
		symbols.put("E", (lineCounter, i) -> {
			mazeStructure.get(lineCounter).add(new Exit(lineCounter, i));
			exits.add(mazeStructure.get(lineCounter).get(i));
		});
	}

	public FileScanner(String mazeFile)
			throws NoStartingPointException, UnknownSymbolException, MultipleStartingPointsException {

		this.fillSymbolsList();

		try (Scanner scanner = new Scanner(new File(mazeFile))) {

			while (scanner.hasNext()) {

				String[] textLine = scanner.nextLine().split("");
				mazeStructure.add(lineCounter, new LinkedList<Node>());

				for (int i = 0; i < textLine.length; i++) {

					try {
						symbols.get(textLine[i]).checkSymbol(lineCounter, i);
					} catch (NullPointerException e) {
						throw new UnknownSymbolException();
					}
				}

				lineCounter++;
			}

			if (startCounter == 0) {
				throw new NoStartingPointException();
			} else if (startCounter > 1) {
				throw new MultipleStartingPointsException();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public List<LinkedList<Node>> getMazeStructure() {
		return this.mazeStructure;
	}

	public List<Node> getExits() {
		return exits;
	}
}
