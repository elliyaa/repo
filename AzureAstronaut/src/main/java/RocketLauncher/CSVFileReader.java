package RocketLauncher;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVFileReader {

	private String[] line;
	private String CSVFile;
	
	private LinkedHashMap<String, List<String>> CSVData = new LinkedHashMap<>();
	
	public CSVFileReader(String path){
		this.CSVFile = path;	
	}
	
	public CSVFileReader () {
	}
	
	public void read () throws CsvValidationException {
		try (CSVReader reader = new CSVReader(new FileReader(CSVFile))) {
			
			while((line = reader.readNext()) != null) {
				line = line[0].split(";");
				CSVData.put(line[0], Arrays.asList(line).subList(1, line.length));
			}
			
		} catch (IOException e) {
			System.out.println("File is not found");
		}
	}
	
	public LinkedHashMap<String, List<String>> getCSVData() {
		return this.CSVData;
	}
}
