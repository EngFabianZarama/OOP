//Fabian Zarama
//109599744
//CSE 214
//Homework #2

package week2;

import java.util.Scanner;

public class DepartmentStore {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in).useDelimiter("\\n");
		ItemList list = new ItemList();
		boolean exit = false;

		System.out.println("Welcome:\n");
		while (exit == false) {

			try {
				System.out.println("C - Clean store");
				System.out.println("I - Insert an item into the list");
				System.out.println("L - List by location");
				System.out.println("M - Move an item in the store");
				System.out.println("O - Checkout");
				System.out.println("P - Print all items in store");
				System.out.println("R - Print by RFID tag number         (optional - extra credit)");
				System.out.println("U - Update inventory system");
				System.out.println("Q - Exit the program.");
				System.out.print("\nPlease select an option: ");
				String option = input.next();

				switch (option.toLowerCase()) {
				case "i":
					System.out.print("Enter the name: ");
					String name = input.next();
					System.out.print("Enter the RFID: ");
					String rfid = input.next();
					System.out.print("Enter the original location: ");
					String location = input.next();
					double price;
					try {
						System.out.print("Enter the price: ");
						price = input.nextDouble();
						list.insertInfo(name, rfid, price, location);
						System.out.println();

						break;
					} catch (Exception e) {
						System.out.println("Error: price must be a decimal number");
						break;
					}

				case "p":
					list.printAll();
					System.out.println();
					break;

				case "m":

					System.out.print("Enter the RFID: ");
					String rfidTag = input.next();
					System.out.print("Enter the original location: ");
					String source = input.next();
					System.out.print("Enter the new location: ");
					String dest = input.next();

					try {
						list.moveItem(rfidTag, source, dest);
						break;
					} catch (Exception e) {
						throw e;
					}
				case "o":
					System.out.print("Enter the cart number: ");
					String cartNumber = input.next();
					try {
						System.out.println("\nThe total cost for all merchandise in cart " + cartNumber + " was $"
								+ list.checkOut(cartNumber) + "\n");
						break;
					} catch (Exception e) {
						throw e;
					}

				case "l":
					System.out.print("Enter the location: ");
					location = input.next();
					list.printByLocation(location);
					break;

				case "c":
					list.cleanStore();
					break;

				case "u":
					list.removeAllPurchased();
					System.out.println();
					break;

				case "r":
					System.out.print("Enter the RFID tag number: ");
					rfid = input.next();
					if (rfid.length() != 9 && !rfid.matches("-?[0-9a-fA-F]+")) {
						System.out.println("\nError: The rfid number must be hex with 9 digits\n");
						break;
					}
					list.printRfid(rfid);
					System.out.println();
					break;

				case "q":
					exit = true;
					break;
				default:
					System.out.println("This option does not exist\n");
				}
			} catch (Exception e) {
				System.out.println("\nWrong input, please retry again\n");
			}
		}
		System.out.println("Program terminating...");
	}
}
