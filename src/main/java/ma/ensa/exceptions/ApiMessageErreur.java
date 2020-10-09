package ma.ensa.exceptions;

import java.time.Instant;

public class ApiMessageErreur {
	
	private String messageErreur;
	private String requeteURI;
	private Instant timeStamp;
	
	
	
	
	public ApiMessageErreur(String messageErreur, String requeteURI, Instant timeStamp) {
		this.messageErreur = messageErreur;
		this.requeteURI = requeteURI;
		this.timeStamp = timeStamp;
	}
	public String getMessageErreur() {
		return messageErreur;
	}
	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	public String getRequeteURI() {
		return requeteURI;
	}
	public void setRequeteURI(String requeteURI) {
		this.requeteURI = requeteURI;
	}
	public Instant getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	

}
