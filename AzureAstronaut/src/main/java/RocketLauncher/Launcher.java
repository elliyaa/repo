package RocketLauncher;

import java.io.IOException;
import javax.mail.MessagingException;
import com.opencsv.exceptions.CsvValidationException;
import Exceptions.IncorrectDataFormatException;

public class Launcher {
	
	public static void main (String args[]) throws MessagingException, IOException, IncorrectDataFormatException, CsvValidationException {
		
		UserInteraction userInteraction = new UserInteraction();
		userInteraction.interact();
		CSVFileReader fileReader = new CSVFileReader(userInteraction.getFilePath());
		fileReader.read();
		ConditionCalculator calculator = new ConditionCalculator(fileReader.getCSVData());
		calculator.addLaunchDay();
		CSVFileWriter writer = new CSVFileWriter(calculator.getCSVCalculatedData());
		writer.write();
		EmailTransfer emailTransfer = new EmailTransfer(userInteraction.getReceiverEmailAddress(), userInteraction.getSenderEmailAddress(), userInteraction.getSenderPassword(), writer.getCSVCalculatedDataPath());
		emailTransfer.sendMessage();
	}
	
}
