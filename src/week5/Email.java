//Fabian Zarama
//109599744
//CSE 214
//Homework #5

package week5;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Email implements Serializable {
	private String to;// The String literal which stores the “to” field.
	private String cc;// The String literal which stores the “cc” field.
	private String bcc;// The String literal which stores the “bcc” field.
	private String subject;// The String literal which stores the “subject”
							// field.
	private String body;// The String literal which stores all of the text in
						// the email’s body.
	private GregorianCalendar timestamp;// Represents the time that this email
										// was created.
	private DateFormat df;

	/**
	 * Contructor for email set up to null
	 * and start of timestamp to current creation.
	 */
	public Email() {
		to = null;
		cc = null;
		bcc = null;
		subject = null;
		body = null;
		timestamp = new GregorianCalendar();
		//df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

	}


	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the timestamp
	 */
	public GregorianCalendar getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(GregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}

}
