//Fabian Zarama
//109599744
//CSE 214
//Homework #5

package week5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Folder implements Serializable{
	private ArrayList<Email> emails;	//Stores all of the emails contained in this folder. 
	private String name;				//Stores the name of the folder.
	private String currentSortingMethod;//Stores the current sorting method
										//Default is date descending.
	/**
	 * Default constructor
	 */
	public Folder() {
		emails = new ArrayList<Email>();
		name = null;
		currentSortingMethod = "Date Descending";
		//this.sortByDateDescending();
	}
	
	/**
	 * Adds an email to the folder according to the current sorting method.
	 * @param email
	 */
	public void addEmail(Email email){
		emails.add(email);
	}

	/**
	 * Removes an email from the folder by index.
	 * @param index
	 * @return returns the email removed
	 */
	public Email removeEmail(int index){
		return emails.remove(index);
	}
	
	/**
	 * Sorts the emails alphabetically by subject in ascending order.
	 */
	public void sortBySubjectAscending(){
		Collections.sort(emails, new SubjectAscending());
	}
	
	/**
	 * Sorts the emails alphabetically by subject in descending order.
	 */
	public void sortBySubjectDescending(){
		Collections.sort(emails, new SubjectDescending());
	}
	
	/**
	 * Sorts the emails by date in ascending order.
	 */
	public void sortByDateAscending(){
		Collections.sort(emails, new DateAscending());
	}
	
	/**
	 * Sorts the emails by date in descending order.
	 */
	public void sortByDateDescending(){
		Collections.sort(emails, new DateDescending());
	}

	/**
	 * @return the emails
	 */
	public ArrayList<Email> getEmails() {
		return emails;
	}
	
	/**
	 * @param emails the emails to set
	 */
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the currentSortingMethod
	 */
	public String getCurrentSortingMethod() {
		return currentSortingMethod;
	}
	
	/**
	 * @param currentSortingMethod the currentSortingMethod to set
	 */
	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}
}

/**
 * Classes to implements in the sort in the following methods 
 * public void sortBySubjectAscending(){
 * public void sortBySubjectDescending(){
 * public void sortByDateAscending(){
 * public void sortByDateDescending(){
 */


class SubjectAscending implements Comparator{

	/**
	 * Compares two objects to sort subject ascending
	 */
	public int compare(Object o1, Object o2) {
		Email e1 = (Email) o1;
		Email e2 = (Email) o2;
		return (e1.getSubject().compareTo(e2.getSubject()));
	}
	
}

class SubjectDescending implements Comparator{

	/**
	 * Compares two objects to sort subject descending
	 */
	public int compare(Object o1, Object o2) {
		Email e1 = (Email) o1;
		Email e2 = (Email) o2;
		return (e2.getSubject().compareTo(e1.getSubject()));
	}
	
}

class DateAscending implements Comparator{

	/**
	 * Compares two objects to sort date ascending
	 */
	public int compare(Object o1, Object o2) {
		Email e1 = (Email) o1;
		Email e2 = (Email) o2;
		return (e1.getTimestamp().compareTo(e2.getTimestamp()));
	}
	
}

class DateDescending implements Comparator{

	/**
	 * Compares two objects to sort date descending
	 */
	public int compare(Object o1, Object o2) {
		Email e1 = (Email) o1;
		Email e2 = (Email) o2;
		return (e2.getTimestamp().compareTo(e1.getTimestamp()));
	}
	
}