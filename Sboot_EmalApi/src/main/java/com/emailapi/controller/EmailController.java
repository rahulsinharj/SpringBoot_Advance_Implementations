package com.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emailapi.model.EmailRequest;
import com.emailapi.model.EmailResponse;
import com.emailapi.service.EmailService;

@RestController
@CrossOrigin						// Cross-Origin Resource Sharing (CORS) is a security concept that allows restricting the resources implemented in web browsers. It prevents the JavaScript code producing or consuming the requests against different origin.  
public class EmailController {

	@Autowired
	private EmailService emailService;

/*===========[ JSon ke format me bheja hua data iss @RequestBody ke karan emailRequest me aajayega ]===========	

	Anyone can consume this API for sending email :
   -------------------------------------------------
		http://localhost:9091/sendemail
		Method type : POST
		Content type : JSON
		Body =>
	
		{
		    "receiverMail":"rj532903@gmail.com",
		    "subject":"Dummy Subject",
		    "textMessage":"Hello dear, This message is only for Demo Purposes."
		}
*/
	
/*	@PostMapping(value = "/sendemail", 
			  	consumes = MediaType.APPLICATION_JSON_VALUE, 
			  	produces = MediaType.APPLICATION_JSON_VALUE )		// import org.springframework.http.MediaType
*/	
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) 		
	{
		System.out.println("EmailRequest : "+emailRequest);
		
		try {
			boolean emailResponseStatus = this.emailService.sendTextEmail(emailRequest.getReceiverMail(), emailRequest.getSubject() , emailRequest.getTextMessage());
			if(emailResponseStatus) {
			 // return ResponseEntity.status(HttpStatus.CREATED).body("Email sent Successfully to "+emailRequest.getReceiverMail());		// This will be just a normal String TEXT - in RESPONSE. 
				
			 /* EmailResponse emailResponse = new EmailResponse("Email sent Successfully");
				return new ResponseEntity<>(emailResponse, HttpStatus.OK);
			 */
 			 // return new ResponseEntity<>(new EmailResponse("Email sent Successfully !"), HttpStatus.OK);
				return new ResponseEntity<>(new EmailResponse("Email sent Successfully to "+emailRequest.getReceiverMail()), HttpStatus.CREATED);	// This will be just a JSON Formatted Obj - in RESPONSE. 
			}
			else {
				//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not sent, carefully write details again !");
				//return new ResponseEntity(HttpStatus.NOT_FOUND);
				
				EmailResponse emailResponse = new EmailResponse("Email not sent, carefully write email credentials again");
				return new ResponseEntity<>(emailResponse, HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in sending mail !");
			//return new ResponseEntity(HttpStatus.NOT_FOUND);
			
			EmailResponse emailResponse = new EmailResponse("Error in sending mail");
			return new ResponseEntity<>(emailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	
	}
	
//==========[ User can take "receiverMail" , "subject" , "textMessage" as in this method parameter ]=============

/*	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEmail() 		
	{
		try {
			String receiverMail = "rj532903@gmail.com";
			
			String subject = "Normal Message for check";
			
			String textMessage = "Hello dear, This message is only for Demo Purposes. " 
									+ "\n" + "\n" + "Thanks & regards,"
									+ "\n" + "Rahul Sinha";
			
			//	return new ResponseEntity<>(this.emailService.sendTextEmail(receiverMail, subject, textMessage), HttpStatus.OK);
		
			boolean emailResponseStatus = this.emailService.sendTextEmail(receiverMail, subject, textMessage);
			if(emailResponseStatus) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Email sent Successfully to "+receiverMail);
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in sending mail");
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in sending mail");
		}
	
	}
*/
	
	@GetMapping("/home")
	public ResponseEntity<EmailResponse> tokenTest()
	{
		EmailResponse res = new EmailResponse("Hi Rahul");
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
	
}
