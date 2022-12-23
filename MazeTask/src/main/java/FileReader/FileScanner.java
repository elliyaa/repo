package FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import MazeParts.Exit;
import MazeParts.Node;
import MazeParts.Path;
import MazeParts.Start;
import MazeParts.Wall;

public class FileScanner implements SymbolChecker{

	private List<Exit> exits = new ArrayList<>();
	private List<Start> starts = new ArrayList<>();
	private List<LinkedList<Node>> mazeStructure = new LinkedList<LinkedList<Node>>();
	
	private int currentX;
	private int currentY;
	private int lineCounter = 0;
	
	public void checkExitCount(Exit e) {
		exits.add(e);
	}
	
	public void checkStartCount(Start s) {
		starts.add(s);
	}
	
	private Map<String, SymbolChecker> symbolChecker = new HashMap<>();
	
	@Override
	public void checkSymbol() {
		
	}
	
	public void addSymbols(int lineCounter, int i) {
		symbolChecker.put(" ", ( ) -> mazeStructure.get(lineCounter).add(new Path(lineCounter, i)));
		symbolChecker.put("*", ( ) -> mazeStructure.get(lineCounter).add(new Wall(lineCounter, i)));
		symbolChecker.put("S", ( ) -> mazeStructure.get(lineCounter).add(new Start(lineCounter, i)));
		symbolChecker.put("E", ( ) -> mazeStructure.get(lineCounter).add(new Exit(lineCounter, i)));
	}
	
	public FileScanner(String mazeFile) {
		try (Scanner scanner = new Scanner(new File(mazeFile))) {
			while (scanner.hasNext()) {

				String[] textLine = scanner.nextLine().split("");

				mazeStructure.add(lineCounter, new LinkedList<Node>());

				for (int i = 0; i < textLine.length; i++) {

					if (textLine[i].equals(" ")) {
						this.mazeStructure.get(lineCounter).add(new Path(lineCounter, i));
					} else if (textLine[i].equals("*")) {
						this.mazeStructure.get(lineCounter).add(new Wall(lineCounter, i));
					} else if (textLine[i].equals("S")) {
						this.mazeStructure.get(lineCounter).add(new Start(lineCounter, i));
						Start s = (Start) mazeStructure.get(lineCounter).get(i);
						checkStartCount(s);
					} else if (textLine[i].equals("E")) {
						this.mazeStructure.get(lineCounter).add(new Exit(lineCounter, i));
						Exit e = (Exit) mazeStructure.get(lineCounter).get(i);
						checkExitCount(e);
					
					
					
					} else {
						throw new InputMismatchException();

					}
					
				}
				lineCounter++;
			}
			
			if(starts.size() == 1) {
				this.currentX = starts.get(0).getX();
				this.currentY = starts.get(0).getY();
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
	
	public List<Exit> getExits(){
		return exits;
	}

}
