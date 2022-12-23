package Exceptions;

public class MultipleStartingPointsException extends Exception{

	public MultipleStartingPointsException(){
		super("More than one starting point.");
	}
}
