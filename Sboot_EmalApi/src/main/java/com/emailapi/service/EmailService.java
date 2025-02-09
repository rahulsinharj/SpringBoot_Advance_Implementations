package com.emailapi.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Value("${myem.pass}")			// Fetching from Application.properties file
	private String pass;
	
	
	// Include this Dependency : https://mvnrepository.com/artifact/com.sun.mail/javax.mail
	
/*========================================================================================================================	
=================[This method is responsible to sending Normal TEXT email :]==============================================*/ 
	    
	public boolean sendTextEmail(String receiverMail, String subject, String textMessage) 
	{
		System.out.println("Pass : " +pass);
		
		boolean sendStatus = false;
		
		final String senderMail = "rahulsinhainfo1@gmail.com";
		
		Properties properties = System.getProperties();			// Getting System Properties
			System.out.println("Properties : " +properties);
			
	//======{Setting important information to properties object}========
			
		String host = "smtp.gmail.com";							// Variable for Gmail	
		properties.put("mail.smtp.host", host);					// Setting Host
		properties.put("mail.smtp.port", "465");				// Setting Port
		properties.put("mail.smtp.ssl.enable", "true");			// Enabling SSL
		properties.put("mail.smtp.auth", "true");				// Enabling Auth
			
			System.out.println("Properties : " +properties);
				
	//======={STEP1 - Getting SESSION object}===========================
			
		Session session = Session.getInstance(properties, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(senderMail, pass);
			}
				
		});  
			
		session.setDebug(true);							// For Enabling Debugging facilites as well
			
	//======={STEP2 - Compose the message(Text, Multimedia)}===========================		
			
		MimeMessage mimeMessage = new MimeMessage(session);
		try 
		{
			mimeMessage.setFrom(senderMail);			// Setting Sender's Email 
			
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail));		// Setting Receiver's Email , or Recipient Email
				
//			mimeMessage.addRecipients(Message.RecipientType.TO, new InternetAddress[]{
//					new InternetAddress("rahulksinha08@gmail.com") ,
//					new InternetAddress("rahulkumarsinha.17@gmail.com") ,
//					new InternetAddress("rj532903@gmail.com")
//			});
				
			mimeMessage.setSubject(subject);			// Adding Subject to Message
			mimeMessage.setText(textMessage);			// Adding Normal TEXT as message body
			// mimeMessage.setContent(textMessage, "text/html");	// Sending HTML body text in email 
			
		//======={STEP3 - Send the Message using Transport class}===========================		
			
			Transport.send(mimeMessage);
			
			System.out.println("####-----Email send Successfully to "+receiverMail+" - at "+new Date());
	
			sendStatus = true;
		
		} 
		catch (MessagingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return sendStatus;
	}
	
}
