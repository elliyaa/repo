package RocketLauncher;

import java.util.List;

public interface CalculatorInterface {
	
	/**
	 * Calculates the average of a list of floats. 
	 *
	 * @param It takes a String List as the information is taken from a file and it needs to be parsed first.   
	 * @return The message and the calculated result
	 */

	public String calculateAverage(List<String> data);
	
	/**
	 * Calculates the Maximum Value of a list of floats. 
	 *
	 * @param It takes a String List as the information is taken from a file and it needs to be parsed first.   
	 * @return The message and the calculated result
	 */
	
	public String calculateMax(List<String> data);
	
	/**
	 * Calculates the Minimum Value of a list of floats. 
	 *
	 * @param It takes a String List as the information is taken from a file and it needs to be parsed first.   
	 * @return The message and the calculated result
	 */
	
	public String calculateMin(List<String> data);
	
	/**
	 * Calculates the Median Value of a list of floats. 
	 *
	 * @param It takes a String List as the information is taken from a file and it needs to be parsed first.   
	 * @return The message and the calculated result
	 */
	
	public String calculateMedian(List<String> data);
	
	/**
	 * Calculates which day is the most appropriate for launching according to the instructions
	 *  
	 * @return A message, which indicates which day is the most appropriate for launching
	 */
	
	public String calculateLaunchDay();
}
