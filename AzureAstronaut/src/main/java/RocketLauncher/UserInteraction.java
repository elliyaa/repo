package RocketLauncher;

import java.util.Scanner;

import Exceptions.IncorrectDataFormatException;

public class UserInteraction {
	
	private LanguageHandler language = new LanguageHandler();
	//private List<List<String>> additionalDataList = new LinkedList<List<String>>();
	private Scanner scanner = new Scanner(System.in);
	private String languageInput;

	private String senderEmailAddress;
	private String senderPassword;
	private String receiverEmailAddress;
	private String filePath;
	//private String additionalData;

	public void interact () throws IncorrectDataFormatException {
		
		System.out.println("Please, select a language (en/de).");
				
		while(true) {
	
			while(true) {
				languageInput = scanner.nextLine();
				if(language.checkLanguage(languageInput) == true) {
					break;
				}
			}
			
			System.out.println(language.getMessage(1));				
			while(true) {
				filePath = scanner.nextLine().trim();
				if(language.checkCSVPath(filePath) == true) {
					break;
				}
			}
			
//			System.out.println(language.getMessage(2));				
//			while(true) {
//				additionalData = scanner.nextLine();
//				if (!(additionalData.trim().equals(""))) {
//					if(Arrays.asList(additionalData.split(" ")).size() != 7){
//						throw new IncorrectDataFormatException();
//					} else {
//						additionalDataList.add(Arrays.asList(additionalData.split(" ")));
//					}
//				} else {
//					break;
//				}
//			}
			
			System.out.println(language.getMessage(3));				
			while(true) {
				senderEmailAddress = scanner.nextLine().trim();
				if(language.checkEmail(senderEmailAddress) == true) {
					break;
				}
			}
			
			System.out.println(language.getMessage(4));				
			senderPassword = scanner.nextLine().trim();
			
			System.out.println(language.getMessage(5));				
			while(true) {
				receiverEmailAddress = scanner.nextLine().trim();
				if(language.checkEmail(receiverEmailAddress) == true) {
					break;
				}
			}
			
			if(senderEmailAddress.isEmpty() == false && senderPassword.isEmpty() == false && receiverEmailAddress.isEmpty() == false && filePath.isEmpty() == false) {
				break;
			}
		}
	}
	
//	public List<List<String>> getAdditionalDataList() {
//		return this.additionalDataList;
//	}
	
	public String getSenderEmailAddress() {
		return this.senderEmailAddress;
	}

	public String getSenderPassword() {
		return this.senderPassword;
	}

	public String getReceiverEmailAddress() {
		return this.receiverEmailAddress;
	}

	public String getFilePath() {
		return this.filePath;
	}
}