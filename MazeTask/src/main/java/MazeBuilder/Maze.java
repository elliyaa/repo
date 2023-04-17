package MazeBuilder;

import MazeParts.Node;

public interface Maze {

    /**
     *@return The Node object that is above the current one and indicates if a movement is possible.
     **/
	public Node checkUp();
	
	/**
     *@return The Node object that is below the current one and indicates if a movement is possible.
     **/
	public Node checkDown();
	
	/**
     *@return The Node object that is on the left side of the current one and indicates if a movement is possible.
     **/
	public Node checkLeft();
	
	/**
     *@return The Node object that is on the right side of the current one and indicates if a movement is possible.
     **/
	public Node checkRight();

	
	/**
	 * Move the instance of the Runner object in the four possible directions.
	 **/
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
}
