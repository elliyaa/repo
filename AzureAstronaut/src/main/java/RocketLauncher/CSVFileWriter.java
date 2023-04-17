package RocketLauncher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

public class CSVFileWriter {
	
	private Map<String, List<String>> CSVCalculatedData;
	private String CSVCalculatedDataPath = "src/resources/newfile.csv";
	String[] arrayValue;
	
	public String getCSVCalculatedDataPath() {
		return this.CSVCalculatedDataPath;
	}
	
	public CSVFileWriter (Map<String, List<String>> CSVCalculatedData) {
		this.CSVCalculatedData = CSVCalculatedData;
    }
	
	public void write() {
		try {
			  CSVWriter writer = new CSVWriter(new FileWriter(CSVCalculatedDataPath, false));
			
			  for(Map.Entry<String, List<String>> entry : CSVCalculatedData.entrySet()) {
			  
			  String entryKey = entry.getKey();
			  List<String> entryValue = entry.getValue();
			  arrayValue = new String[entryValue.size() + 1];
			  arrayValue[0] = entryKey;
			 
			  for(int i = 1; i <= entryValue.size(); i++) {
				  arrayValue[i] = entryValue.get(i - 1).trim();
			  }
			  
			  writer.writeNext(arrayValue);
			  }
			  writer.close(); 
			  System.out.println("File successfully writen.");
			} catch (IOException e) {  	
			  System.out.println("File cannot be created.");
      }
	}
	
}
