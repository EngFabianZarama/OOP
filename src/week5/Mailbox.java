//Fabian Zarama
//109599744
//CSE 214
//Homework #5

package week5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mailbox implements Serializable {
	private Folder inbox; // Stores the inbox, which is a special folder which
							// should never be allowed to be deleted or renamed.
							// All new emails go here.
	private Folder trash; // Stores the trash, which is a special folder which
							// should never be allowed to be deleted or renamed.
							// When an email is deleted, it is moved here.
	private ArrayList<Folder> folders; // Stores all of the custom folders
										// contained in the mailbox.
	public static Mailbox mailbox; // Stores the main mailbox that will used by
									// the application.

	/**
	 * Constructor of mailbox to initialize inbox, trash, folders
	 */
	public Mailbox() {
		inbox = new Folder();
		trash = new Folder();
		folders = new ArrayList();
		inbox.setName("Inbox");
		trash.setName("Trash");
	}

	/**
	 * Adds the given folder to the list of custom folders.
	 * 
	 * @param folder
	 */
	public void addFolder(Folder folder) {
		if (folders == null) {
			folders.add(folder);
			return;
		}
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(folder.getName())) {
				System.out.println("\nThis folder " + folder.getName() + " already exists.");
				return;
			}
		}
		folders.add(folder);
	}

	/**
	 * Removes the given folder from the list of custom folders
	 * 
	 * @param name
	 */
	public void deleteFolder(String name) {
		folders.remove(this.getFolder(name));
		System.out.println("Folder " + "\"" + name + "\"" + " removed successfully\n");
	}

	/**
	 * Gets user input on the contents of the email and adds it to the inbox.
	 */
	public void composeEmail(Email e) {
		inbox.addEmail(e);
	}

	/**
	 * Moves the email to the trash.
	 * 
	 * @param email
	 */
	public void deleteEmail(Email email) {
		trash.addEmail(email);
		System.out.println("\"" + email.getSubject() + "\" has successfully been moved to the trash.\n");
	}

	/**
	 * Removes all emails from the trash folder.
	 */
	public void clearTrash() {
		for (int i = 0; i < trash.getEmails().size(); i++)
			trash.getEmails().remove(i);
	}

	/**
	 * Takes the given email and puts in in the given folder. If the folder
	 * cannot be found, instead move it to the Inbox.
	 * 
	 * @param email
	 * @param target
	 */
	public void moveEmail(Email email, Folder target) {
		Email tem = new Email();
		tem = email;
		if (target.getName().equals("Trash")) {
			target.addEmail(email);
			// find folder with email to delete it
			for (int i = 0; i < this.folders.size(); i++) {
				for (int j = 0; j < this.folders.get(i).getEmails().size(); j++) {
					if (this.folders.get(i).getEmails().get(j).getSubject().equals(email.getSubject())) {
						this.folders.get(i).getEmails().remove(j);
					}
				}
			}
			// find email in inbox to delete it
			for (int i = 0; i < this.inbox.getEmails().size(); i++) {
				if (this.inbox.getEmails().get(i).getSubject().equals(email.getSubject())) {
					this.inbox.getEmails().remove(i);
				}
			}
			System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
			return;
		} else if (target.getName().equals("Inbox")) {
			target.addEmail(email);
			// find folder with email to delete it
			for (int i = 0; i < this.folders.size(); i++) {
				for (int j = 0; j < this.folders.get(i).getEmails().size(); j++) {
					if (this.folders.get(i).getEmails().get(j).getSubject().equals(email.getSubject())) {
						this.folders.get(i).getEmails().remove(j);
						System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
						return;
					}
				}
			}

			// find email in trash to delete it
			for (int i = 0; i < this.inbox.getEmails().size(); i++) {
				if (this.trash.getEmails().get(i).getSubject().equals(email.getSubject())) {
					this.trash.getEmails().remove(i);
					System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
					return;
				}
			}
			System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
			return;
		}

		// if email is in trash?
		if (trash.getEmails().indexOf(email) >= 0) {

			for (int i = 0; i < trash.getEmails().size(); i++) {
				if (folders.get(i).getName().equals(target.getName())) {
					target.addEmail(email);
					i=trash.getEmails().size();
				}
			}
			for (int i = 0; i < trash.getEmails().size(); i++) {
				if (this.trash.getEmails().get(i).getSubject().equals(email.getSubject())) {
					this.trash.getEmails().remove(i);
					System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
					return;
				}
			}
		}

		
		
		//if it is in inbox
		for (int i = 0; i < inbox.getEmails().size(); i++) {
			if (this.inbox.getEmails().get(i).getSubject().equals(email.getSubject())) {
				this.inbox.getEmails().remove(i);
				System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
			}
		}
		
		//if its in folder to remove
		for(int j =0;j<folders.size();j++){
			for (int i = 0; i < folders.get(j).getEmails().size(); i++) {
					if(folders.get(j).getEmails().get(i).equals(email)){
						folders.get(j).getEmails().remove(i);
						System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
					}
			}
		}
		
		
		// from inbox to a folder
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(target.getName())) {
				target.addEmail(tem);
				i=folders.size();
				return;
			}
		}
		
		
		// else move email to inbox
		inbox.addEmail(email);
		folders.remove(folders.indexOf(email));
		System.out.println("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
	}

	/**
	 * 
	 * @param name
	 * @return the object Folder, found by name
	 */
	public Folder getFolder(String name) {
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(name)) {
				return folders.get(i);
			}
		}
		System.out.println("\nFolder \"" + name + "\" not found.");
		return null;
	}

	/**
	 * 
	 * @return the object Folder inbox
	 */
	public Folder getInbox() {
		return inbox;
	}

	/**
	 * 
	 * @return the object Folder inbox
	 */
	public Folder getTrash() {
		return trash;
	}

	/**
	 * 
	 * @return the Array list of the folders Folder
	 */
	public ArrayList<Folder> getFolders() {
		return folders;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in).useDelimiter("\\n");

		Mailbox mb = new Mailbox();
		try {
			FileInputStream file = new FileInputStream("myMailboxFile.obj");
			ObjectInputStream fin = new ObjectInputStream(file);
			mb = (Mailbox) fin.readObject();
			file.close();
		} catch (IOException a) {
			System.out.println("Previous save not found, starting with an empty mailbox.\n");
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
		}

		boolean exit = false;
		while (exit != true) {
			System.out.println("\nMailbox:");
			System.out.println("--------");

			printFolders(mb);

			System.out.println("\nA – Add folder");
			System.out.println("R – Remove folder");
			System.out.println("C – Compose email");
			System.out.println("F – Open folder");
			System.out.println("I – Open Inbox");
			System.out.println("T – Open Trash");
			System.out.println("E – Empty Trash");
			System.out.println("Q – Quit\n");
			System.out.print("Enter a user option: ");
			String ch = input.nextLine();

			switch (ch.toLowerCase()) {
			case "a":// add folder
				System.out.print("Enter folder name: ");
				String forderName = input.nextLine();
				Folder folder = new Folder();
				folder.setName(forderName);
				mb.addFolder(folder);
				break;
			case "r":// remove folder
				System.out.print("Enter folder name: ");
				Folder fold = mb.getFolder(input.nextLine());
				if (fold == null) {
					System.out.println("This folder can not be removed.\n");
					break;
				}
				
				mb.deleteFolder(fold.getName());
				break;
			case "c":// Compose Email
				Email email = new Email();
				try {
					System.out.println("Enter recipient (To): ");
					email.setTo(input.nextLine());
					System.out.println("\nEnter carbon copy recipients (CC): ");
					email.setCc(input.nextLine());
					System.out.println("\nEnter blind carbon copy recipients (BCC): ");
					email.setBcc(input.nextLine());
					System.out.println("\nEnter subject line: ");
					email.setSubject(input.nextLine());
					System.out.println("\nEnter body:");
					email.setBody(input.nextLine());

				} catch (Exception e) {
					System.out.println("Email was not added to Inbox.");
					break;
				}

				mb.composeEmail(email);

				System.out.println("Email successfully added to Inbox.");
				break;
			case "f":// Open Folder

				System.out.print("Enter folder name: ");
				fold = mb.getFolder(input.nextLine());
				if (fold == null) {
					break;
				}
				folderOptions(fold, mb);

				break;
			case "i":// Open inbox
				folderOptions(mb.getInbox(), mb);
				break;
			case "t":// open Trash
				folderOptions(mb.getTrash(), mb);
				break;
			case "e":// Empty trash
				mb.getTrash().getEmails().clear();
				System.out.println("Trash folder is clean\n");
				break;
			case "q":// Save file and quit
				try {
					FileOutputStream file = new FileOutputStream("myMailboxFile.obj");
					ObjectOutputStream fout = new ObjectOutputStream(file);
					fout.writeObject(mb);
					fout.close();
				} catch (IOException a) {
					System.out.println("Could not save file\n");
					break;
				}
				System.out.println("Program successfully exited and mailbox saved.");
				exit = true;
				break;
			default:
				System.out.println("\n" + ch + " is not an option.\n");
			}
		}

	}

	/**
	 * Prints the options on each folder called fold
	 * 
	 * @param fold
	 * @param mb
	 */
	private static void folderOptions(Folder fold, Mailbox mb) {
		try {
			fold.sortByDateDescending();
			Scanner input = new Scanner(System.in);
			boolean retw = false;
			while (retw != true) {
				System.out.println(fold.getName() + "\n");
				if (fold.getEmails().isEmpty()) {
					System.out.println(fold.getName() + " is empty");
					return;
				} else {
					System.out.println("Index\t|\t  Time\t\t|\tSubject");
					for (int i = 0; i < fold.getEmails().size(); i++) {
						System.out.println(i + 1 + "\t|  " + fold.getEmails().get(i).getTimestamp().toZonedDateTime().format(DateTimeFormatter.ofPattern("hh:mma")) + " " + fold.getEmails().get(i).getTimestamp().toZonedDateTime().format(DateTimeFormatter.ofPattern("dd/mm/uuuu")) + "\t|\t" + fold.getEmails().get(i).getSubject());
					}
				}
				System.out.println("\nM – Move email");
				System.out.println("D – Delete email");
				System.out.println("V – View email contents");
				System.out.println("SA – Sort by subject line in ascending order");
				System.out.println("SD – Sort by subject line in descending order");
				System.out.println("DA – Sort by date in ascending order");
				System.out.println("DD – Sort by date in descending order");
				System.out.println("R – Return to mailbox\n");
				System.out.print("Enter a user option: ");
				String op = input.nextLine();

				switch (op.toLowerCase()) {
				case "m":// move email
					if (fold.getEmails().isEmpty()) {
						System.out.println("There is no Email " + fold.getName());
						break;
					}
					String index = null;
					int ind = 0;
					while (ind == 0) {
						System.out.print("Enter the index of the email to move: ");
						index = input.nextLine();

						if (!index.matches("[a-zA-Z]+")) {
							ind = Integer.parseInt(index);
							if (ind < 1) {
								System.out.println("Not a valid index\n");
								ind = 0;
							}
							if (ind > fold.getEmails().size()) {
								System.out.println("Index cant be bigger than " + fold.getEmails().size());
								ind = 0;
							}

						} else {
							System.out.println("The index has to be an integer\n");
						}
					}
					ind--;
					System.out.println("\nFolders:");
					printFolders(mb);
					System.out
							.print("\nSelect a folder to move \"" + fold.getEmails().get(ind).getSubject() + "\" to: ");
					String folderTo = input.nextLine();
					Folder fldMove;

					if (folderTo.equals(fold.getName())) {
						System.out.println("The email is at your destination folder\n");
						break;
					}

					if (folderTo.equals("Inbox")) {
						fldMove = mb.inbox;
						mb.moveEmail(fold.getEmails().get(ind), fldMove);
						break;
					} else if (folderTo.equals("Trash")) {
						fldMove = mb.trash;
						mb.moveEmail(fold.getEmails().get(ind), fldMove);
						break;
					} else {
						fldMove = mb.getFolder(folderTo);
						if (fldMove == null) {
							break;
						}
						mb.moveEmail(fold.getEmails().get(ind), fldMove);
						break;
					}

				case "d":// delete email
					if (fold.getName().equals("Trash")) {
						System.out.println("Email is already in Trash");
						break;
					}
					if (fold.getEmails().isEmpty()) {
						System.out.println("Ther is no Email to delete ");
						break;
					}
					index = null;
					ind = 0;
					while (ind == 0) {
						System.out.print("Enter email index: ");
						index = input.nextLine();

						if (!index.matches("[a-zA-Z]+")) {
							ind = Integer.parseInt(index);
							if (ind < 1) {
								System.out.println("Not a valid index\n");
								ind = 0;
							}
							if (ind > fold.getEmails().size()) {
								System.out.println("Index cant be bigger than " + fold.getEmails().size());
								ind = 0;
							}

						} else {
							System.out.println("The index has to be an integer\n");
						}
					}
					ind--;
					mb.deleteEmail(fold.removeEmail(ind));
					System.out.println();

					break;
				case "v":// view email
					if (fold.getEmails().isEmpty()) {
						System.out.println("Ther is no Email in " + fold.getName());
						break;
					}
					index = null;
					ind = 0;
					while (ind == 0) {
						System.out.print("Enter email index: ");
						index = input.nextLine();

						if (!index.matches("[a-zA-Z]+")) {
							ind = Integer.parseInt(index);
							if (ind < 1) {
								System.out.println("Not a valid index\n");
								ind = 0;
							}
							if (ind > fold.getEmails().size()) {
								System.out.println("Index cant be bigger than " + fold.getEmails().size());
								ind = 0;
							}

						} else {
							System.out.println("The index has to be an integer\n");
						}
					}
					ind--;
					System.out.println("To: " + fold.getEmails().get(ind).getTo());
					System.out.println("CC: " + fold.getEmails().get(ind).getCc());
					System.out.println("BCC: " + fold.getEmails().get(ind).getBcc());
					System.out.println("Subject: " + fold.getEmails().get(ind).getSubject());
					System.out.println(fold.getEmails().get(ind).getBody() + "\n");
					break;
				case "sa":
					fold.sortBySubjectAscending();
					fold.setCurrentSortingMethod("Subject Ascending");
					break;
				case "sd":
					fold.sortBySubjectDescending();
					fold.setCurrentSortingMethod("Subject Descending");
					break;
				case "da":
					fold.sortByDateAscending();
					fold.setCurrentSortingMethod("Date Ascending");
					break;
				case "dd":
					fold.sortByDateDescending();
					fold.setCurrentSortingMethod("Date Descending");
					break;
				case "r":
					retw = true;
					break;
				default:
					System.out.println("This " + op + " is not an option.\n");
				}
			}
		} catch (Exception e) {
			System.out.println("Wrong input...");
			return;
		}
	}

	/**
	 * Prints all the folders in the mailbox
	 * 
	 * @param mb
	 */
	private static void printFolders(Mailbox mb) {
		System.out.println(mb.getInbox().getName());
		System.out.println(mb.getTrash().getName());
		if (mb.getFolders() != null) {
			for (int i = 0; i < mb.getFolders().size(); i++) {
				System.out.println(mb.getFolders().get(i).getName());
			}
		}
	}

}
