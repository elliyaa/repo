package MazeBuilder;

import MazeParts.Node;

public interface Maze {

    /**
     * Checks if a movement is possible.
     * 
     *@return A boolean value indicating if it is possible to go in the certain direction.
     *@throws
     * */
	public Node checkUp();
	public Node checkDown();
	public Node checkLeft();
	public Node checkRight();

	
	/**
	 * 
	 * @return None.
	 * */
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
}
