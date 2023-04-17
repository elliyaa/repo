package RocketLauncher;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EmailTransfer {
	
	UserInteraction userInteraction = new UserInteraction();
	
	Properties properties = new Properties();
	String fromEmail;
    String password;
    String toEmail;
    
    String subject = "CSV result";
    String body = "Hello,\n\nPlease find attached the CSV file.";
    String attachmentPath;
    
	public EmailTransfer(String receiver, String sender, String password, String path) throws MessagingException, IOException {
		
		this.fromEmail = sender;
		this.toEmail = receiver;
		this.password = password;
		this.attachmentPath = path;
		
		//properties.setProperty("mail.smtp.host", "smtp.abv.bg");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable","true");
		//properties.put("mail.smtp.starttls.enable", "true");
	    // properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		//properties.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
		properties.put("mail.smtp.host", "smtp.abv.bg");
		properties.put("mail.smtp.port", "465");
		
	}
	
	public void sendMessage() throws AddressException, MessagingException, IOException {
		Session session = Session.getInstance(properties, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(fromEmail, password);
		    }
		});
		 
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		
		try {
		InternetAddress sender = new InternetAddress(fromEmail);	
		message.setFrom(sender);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject(subject);
		
		BodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setText(body);
		
		MimeBodyPart attachmentPart = new MimeBodyPart();
		attachmentPart.attachFile(new File(attachmentPath));
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		multipart.addBodyPart(attachmentPart);
		
		message.setContent(multipart);
		Transport.send(message);
		
		System.out.println("Message sent successfully!");
		
		} catch (Exception e) {
			System.out.println("There was a problem with the email. Please, check the email addresses provided and your password.");
		}
	}
}
