package Exceptions;

public class IncorrectDataFormatException extends Exception {
	public IncorrectDataFormatException () {
		super("Please check the quality of the data. There shouldn't be any empty cells or values in incorrect format.");
	}
}
