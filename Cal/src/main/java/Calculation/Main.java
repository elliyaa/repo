package Calculation;

public class Main {
	
	private String[] strings;
	
	public Main(){
		
	}
	
	public Main(String... strings) {
		this.strings = strings;
	}
	
	private Main enterCalculation = new Main("*+3X4");
	
	public String[] getEnterCalculation() {
		return enterCalculation.strings;
	} 
	
}
