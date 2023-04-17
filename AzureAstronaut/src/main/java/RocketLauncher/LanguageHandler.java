package RocketLauncher;

public class LanguageHandler {
	private String currentLanguage = "en";
	private String pathRegex = "^(?:[a-zA-Z]:)?[\\\\/]?[\\w\\\\/]*(?:\\.\\w+)?$";
	private String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";	
	private String message;
	
	public boolean checkLanguage (String input) {  
			if (input.toLowerCase().equals("en") || input.toLowerCase().equals("de")) {
				this.currentLanguage = input;
				return true;
			} else {
				System.out.println("Please, enter either 'en' or 'de'. Those are the suppported languages.");
		}
			return false;
	}
	
	public boolean checkCSVPath (String path) { 
		if (path.matches(pathRegex) && path.length() > 0) {
			return true;
		} else {
			System.out.println(this.getMessage(6));	
	}
		return false;
	}
	
	public boolean checkEmail (String email) { 
		if (email.matches(emailRegex)) {
			return true;
		} else {
			System.out.println(this.getMessage(7));
	}
		return false;
	}
	
	public String getMessage(int key) {
		if (currentLanguage.toLowerCase().trim().equals("de")) {
			return getGermanMessage(key);
		} else if (currentLanguage.toLowerCase().trim().equals("en")) {
			return getEnglishMessage(key);
		} else {
			return "Please enter a valid language.";
		}
	}
	
	public String getEnglishMessage(int key) {
		switch(key) {
			case 1: 
				message = "Please, enter the file path of the CSV file containing the data.";
			break;
			case 2: 
				message = "Would you like to add any more data to the file. If the answer is yes, "
						+ "add the neccassary information using spaces. You should include day, temperature, "
						+ "wind, humidity, precipitatation, lightning and clouds. Please, enter just one entry. "
						+ "You will be given the chance to entry another one right after you finish with the first."
						+ "If the answer is no leave empty space.";
			break;
			case 3: 
				message = "Please, enter your email address.";
			break;
			case 4: 
				message = "Please, enter your password.";
			break;
			case 5: 
				message = "Please, enter the receiver's email address.";
			break;
			case 6: 
				message = "Please, enter a valid path.";
			break;
			case 7: 
				message = "Please, enter a valid email.";
			break;
		}
		return message;
	}
	
	public String getGermanMessage(int key) {
		switch(key) {
		case 1: 
			message = "Bitte geben Sie den Dateipfad der CSV-Datei ein, die die Daten enthält.";
		break;
		case 2: 
			message = "Möchten Sie weitere Daten zur Datei hinzufügen. Wenn die Antwort ja ist, fügen Sie "
					+ "die erforderlichen Informationen mit Leerzeichen hinzu. Sie sollten Tag, Temperatur, "
					+ "Wind, Luftfeuchtigkeit, Niederschlag, Blitze und Wolken angeben. Bitte, geben Sie nur einen "
					+ "Eintrag ein. Sie haben die Möglichkeit, an einem weiteren teilzunehmen, direkt nachdem "
					+ "Sie mit dem ersten fertig sind.Wenn die Antwort nein ist, lassen Sie das Feld leer Raum.";
		break;
		case 3: 
			message = "Geben Sie bitte Ihre Email-Adresse ein.";
		break;
		case 4: 
			message = "Bitte geben Sie Ihr Passwort ein.";
		break;
		case 5: 
			message = "Bitte geben Sie die E-Mail-Adresse des Empfängers ein";
		break;
		case 6: 
			message = "Bitte geben Sie einen gültigen Pfad ein.";
		break;
		case 7: 
			message = "Bitte geben Sie eine gültige Email-Adresse ein.";
		break;
	}
		return message;
	}
}