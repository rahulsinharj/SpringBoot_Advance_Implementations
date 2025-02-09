package com.emailapi.model;

public class EmailRequest {

	private String receiverMail;
	private String subject;
	private String textMessage;
	
	public EmailRequest() {
		super();
	}
	public EmailRequest(String receiverMail, String subject, String textMessage) {
		super();
		this.receiverMail = receiverMail;
		this.subject = subject;
		this.textMessage = textMessage;
	}
	public String getReceiverMail() {
		return receiverMail;
	}
	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	@Override
	public String toString() {
		return "EmailRequest [receiverMail=" + receiverMail + ", subject=" + subject + ", textMessage=" + textMessage
				+ "]";
	}
	
	
}
