//Fabian Zarama
//109599744
//CSE 214
//Homework #2

package week2;

public class ItemList {
	private ItemInfoNode head;
	private ItemInfoNode tail;
	private ItemInfoNode cursor;
	private int size;

	/**
	 * Default constructor
	 */
	public ItemList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * creates a ItemInfo item with the following inputs Inserts the info into
	 * the list in its correct position based on its rfidTagNumber.
	 * 
	 * @param name
	 *            input of the name of the item
	 * @param rfidTag
	 *            input of rfidTag of the item
	 * @param price
	 *            input of the price of the item
	 * @param initPosition
	 *            input of the initial position of the item
	 * 
	 *            Big O notation: O(size)
	 *            Its size since is the worst case in the linkedList
	 */
	public void insertInfo(String name, String rfidTag, double price, String initPosition) {
		ItemInfo item = new ItemInfo(name, price, rfidTag, initPosition);

		ItemInfoNode nodePointer = new ItemInfoNode(item, null, null);
		if (size == 0 || (Long.parseLong(rfidTag, 16) < Long.parseLong(this.head.getInfo().getRfidTagNumber(), 16))) {
			insertAtStart(item);
			return;
		}

		ItemInfoNode pointer = head;
		for (int i = 1; i <= size; i++) {
			if (pointer.getNext() == null) {
				insertAtEnd(item);
				return;
			} else {
				if (Long.parseLong(rfidTag, 16) >= Long.parseLong(pointer.getInfo().getRfidTagNumber(), 16) && Long
						.parseLong(rfidTag, 16) < Long.parseLong(pointer.getNext().getInfo().getRfidTagNumber(), 16)) {
					ItemInfoNode tmp = pointer.getNext();
					pointer.setNext(nodePointer);
					nodePointer.setPrev(pointer);
					nodePointer.setNext(tmp);
					tmp.setPrev(nodePointer);
					size++;
					return;
				} else {
					pointer = pointer.getNext();
				}
			}
		}
	}

	/**
	 * removes all items that have current location listed as "out" and displays
	 * a list of all items that have been removed in this fashion. uses a helper
	 * method deleteAtPos(int x); in order to delete the item previously found
	 * in the loop Big O notation: O(s)
	 * Its s since is the worst case in the linkedList
	 */
	public void removeAllPurchased() {
		System.out.println("\nThe following item(s) have removed from the system:");
		System.out.println("\t\t\t\t\tOriginal\tCurrent");
		System.out.print("Item Name\t\tRFID\t\tLocation\tLocation\t\tPrice\n");
		System.out.println("===========\t==================\t=========\t=========\t\t=======");

		ItemInfoNode pointer = head;
		int s = size;
		int temp = 1;

		for (int i = 1; i <= s; i++) {
			if (pointer.getInfo().getCurrentLocation().equals("out")) {
				System.out.println(String.format("%-21s%-20s%-16s%-21s%.2f", pointer.getInfo().getName(),
						pointer.getInfo().getRfidTagNumber(), pointer.getInfo().getOriginalLocation(),
						pointer.getInfo().getCurrentLocation(), pointer.getInfo().getPrice()));
				deleteAtPos(temp);
				temp--;
			}
			temp++;
			pointer = pointer.getNext();
		}

	}

	/**
	 * 
	 * @param rfidTag
	 *            input if rfid of item
	 * @param source
	 *            input source of the item
	 * @param dest
	 *            input dest of the item
	 * @return The return value indicates whether or not an item of the given
	 *         rfidTagNumber was found at the given source location.
	 * @throws dest
	 *             has to be a shelf or car or out. Big O notation: O(size)
	 *             Its size since is the worst case in the linkedList
	 */
	public boolean moveItem(String rfidTag, String source, String dest) {
		if (source.equals("out")) {
			throw new IllegalArgumentException();
			// System.out.println("destination can not be out");
		}

		if ((source.charAt(0) == 's' && source.substring(1).matches("-?[0-9]+") && source.length() == 6)
				|| (source.charAt(0) == 'c' && source.substring(1).matches("-?[0-9]+") && source.length() == 4)) {
			if ((dest.substring(1).matches("-?[0-9]+") && dest.length() == 4 && dest.charAt(0) == 'c')
					|| (dest.substring(1).matches("-?[0-9]+") && dest.length() == 6 && dest.charAt(0) == 's')
					|| dest.toLowerCase().equals("out")) {

				ItemInfoNode pointer = head;

				// find rfidTag
				for (int i = 0; i < size; i++) {
					if (pointer.getInfo().getRfidTagNumber().equals(rfidTag)
							&& pointer.getInfo().getCurrentLocation().equals(source)) {
						pointer.getInfo().setCurrentLocation(dest);
						return true;
					}
					pointer = pointer.getNext();
				}
				// move to this source

			}

		}

		System.out.println("\nshelf must be: ex-> s12345");
		System.out.println("car must be:   ex-> c101\n");

		return false;

	}

	/**
	 * 
	 * @param location
	 *            Prints out in order of rfid number, using the current location
	 *            as a constraint to print out the numbers Big O notation:
	 *            O(size)
	 *            Its size since is the worst case in the linkedList
	 */
	public void printByLocation(String location) {
		ItemInfoNode pointer = head;

		System.out.println("\n\t\t\t\t\tOriginal\tCurrent");
		System.out.print("Item Name\t\tRFID\t\tLocation\tLocation\t\tPrice\n");
		System.out.println("===========\t==================\t=========\t=========\t\t=======");

		for (int i = 1; i <= size; i++) {
			if (pointer.getInfo().getCurrentLocation().equals(location)) {
				System.out.print(String.format("%-21s%-20s%-16s%-21s%.2f", pointer.getInfo().getName(),
						pointer.getInfo().getRfidTagNumber(), pointer.getInfo().getOriginalLocation(),
						pointer.getInfo().getCurrentLocation(), pointer.getInfo().getPrice()));
				System.out.println();
			}
			pointer = pointer.getNext();
		}
	}

	/**
	 * prints out the items that are not in a original location Then the item
	 * "moves" to the original location so original location and current
	 * location are the same
	 * 
	 * Big O notation: O(size)
	 * Its size since is the worst case in the linkedList
	 */
	public void cleanStore() {
		System.out.println("The following item(s) have been moved back to their original locations:");
		System.out.println("\n\t\t\t\t\tOriginal\tCurrent");
		System.out.print("Item Name\t\tRFID\t\tLocation\tLocation\t\tPrice\n");
		System.out.println("===========\t==================\t=========\t=========\t\t=======");
		ItemInfoNode pointer = head;
		for (int i = 1; i <= size; i++) {
			if (pointer.getInfo().getCurrentLocation().charAt(0) == 'c'
					|| pointer.getInfo().getCurrentLocation().equals("out")) {
				pointer = pointer.getNext();
				continue;

			} else if (!pointer.getInfo().getCurrentLocation().equals(pointer.getInfo().getOriginalLocation())) {
				System.out.println(String.format("%-21s%-20s%-16s%-21s%.2f", pointer.getInfo().getName(),
						pointer.getInfo().getRfidTagNumber(), pointer.getInfo().getOriginalLocation(),
						pointer.getInfo().getCurrentLocation(), pointer.getInfo().getPrice()));
				pointer.getInfo().setCurrentLocation(pointer.getInfo().getOriginalLocation());
				pointer = pointer.getNext();
			} else {
				pointer = pointer.getNext();
			}
		}
		System.out.println("\n");
	}

	/**
	 * goes to a carNumber and changes its current location to "out"
	 * 
	 * @param cartNumber
	 *            input of the cartNumber to use as a constrain to assign the
	 *            new location as "out"
	 * @return the total value of the cost for the items in the cart
	 * 
	 *         Big O notation: O(size)
	 *         Its size since is the worst case in the linkedList
	 */
	public double checkOut(String cartNumber) {

		try {
			if (cartNumber.length() == 4 && cartNumber.charAt(0) == 'c'
					&& cartNumber.substring(1, 4).matches("-?[0-9]+")) {
				System.out.println("\n\t\t\t\t\tOriginal\tCurrent");
				System.out.print("Item Name\t\tRFID\t\tLocation\tLocation\t\tPrice\n");
				System.out.println("===========\t==================\t=========\t=========\t\t=======");

				double tot = 0;
				ItemInfoNode pointer = head;

				for (int i = 1; i <= size; i++) {
					if (pointer.getInfo().getCurrentLocation().equals(cartNumber)) {
						System.out.println(String.format("%-21s%-20s%-16s%-21s%.2f", pointer.getInfo().getName(),
								pointer.getInfo().getRfidTagNumber(), pointer.getInfo().getOriginalLocation(),
								pointer.getInfo().getCurrentLocation(), pointer.getInfo().getPrice()));
						pointer.getInfo().setCurrentLocation(pointer.getInfo().getOriginalLocation());
						tot += pointer.getInfo().getPrice();
						pointer.getInfo().setCurrentLocation("out");
					}
					pointer = pointer.getNext();
				}

				return tot;

			} else {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Cart must start with an \"s\" and 3 digits for example: c101 ");
		}
		return 0;

	}

	/**
	 * 
	 * @param pos
	 *            deletes a node at a certain position
	 */
	public void deleteAtPos(int pos) {
		if (pos == 1) {
			if (size == 1) {
				head = null;
				tail = null;
				size = 0;
				return;
			}
			head = head.getNext();
			head.setPrev(null);
			size--;
			return;
		}
		if (pos == size) {
			tail = tail.getPrev();
			tail.setNext(null);
			size--;
			return;
		}
		ItemInfoNode ptr = head.getNext();
		for (int i = 2; i <= size; i++) {
			if (i == pos) {
				ItemInfoNode p = ptr.getPrev();
				ItemInfoNode n = ptr.getNext();

				p.setNext(n);
				n.setPrev(p);
				size--;
				return;
			}
			ptr = ptr.getNext();
		}
	}

	/**
	 * 
	 * @param item
	 *            item is used to insert a node at the start of the nodes
	 */
	public void insertAtStart(ItemInfo item) {
		ItemInfoNode nodePointer = new ItemInfoNode(item, null, null);
		if (head == null) {
			head = nodePointer;
			tail = head;
		} else {
			head.setPrev(nodePointer);
			nodePointer.setNext(head);
			head = nodePointer;
		}
		size++;
	}

	/**
	 * 
	 * @param item
	 *            is used to inser a node at the end of the nodes
	 */
	public void insertAtEnd(ItemInfo item) {
		ItemInfoNode nptr = new ItemInfoNode(item, null, null);
		if (head == null) {
			head = nptr;
			tail = head;
		} else {
			nptr.setPrev(tail);
			tail.setNext(nptr);
			tail = nptr;
		}
		size++;
	}

	/**
	 * 
	 * @param rfid
	 *            input to find in the list the rfid number to print out with
	 *            this constrain
	 * 
	 * @throws IllegalArgumentException
	 *             if the rfid number is not of size 9 or a hexadecimal number
	 *             it throws an exception Big O notation: O(size)
	 *             Its size since is the worst case in the linkedList
	 */
	public void printRfid(String rfid) throws IllegalArgumentException {
		if (rfid.length() == 9 && rfid.matches("-?[0-9a-fA-F]+")) {
			System.out.println("\nError: The rfid number must be hex with 9 digits\n");
		}
		System.out.println("\n\t\t\t\t\tOriginal\tCurrent");
		System.out.print("Item Name\t\tRFID\t\tLocation\tLocation\t\tPrice\n");
		System.out.println("===========\t==================\t=========\t=========\t\t=======");

		if (size == 0) {
			return;
		}
		ItemInfoNode pointer = head;
		for (int i = 1; i <= size; i++) {
			if (pointer.getInfo().getCurrentLocation().equals(rfid)) {
				System.out.print(String.format("%-21s%-20s%-16s%-21s%.2f", pointer.getInfo().getName(),
						pointer.getInfo().getRfidTagNumber(), pointer.getInfo().getOriginalLocation(),
						pointer.getInfo().getCurrentLocation(), pointer.getInfo().getPrice()));
				System.out.println();
			}
			pointer = pointer.getNext();
		}

	}

	/**
	 * Prints out in a organized matter the items in the list, they are
	 * organized with the rfid number, from smallest to highest.
	 * 
	 * Big O notation: O(size)
	 * Its size since is the worst case in the linkedList
	 */
	public void printAll() {

		System.out.println("\n\t\t\t\t\tOriginal\tCurrent");
		System.out.print("Item Name\t\tRFID\t\tLocation\tLocation\t\tPrice\n");
		System.out.println("===========\t==================\t=========\t=========\t\t=======");

		if (size == 0) {
			return;
		}
		if (head.getNext() == null) {
			System.out.print(String.format("%-21s%-20s%-16s%-21s%.2f", head.getInfo().getName(),
					head.getInfo().getRfidTagNumber(), head.getInfo().getOriginalLocation(),
					head.getInfo().getCurrentLocation(), head.getInfo().getPrice()));
			System.out.println();
			return;
		}
		ItemInfoNode ptr = head;
		System.out.print(String.format("%-21s%-20s%-16s%-21s%.2f", head.getInfo().getName(),
				head.getInfo().getRfidTagNumber(), head.getInfo().getOriginalLocation(),
				head.getInfo().getCurrentLocation(), head.getInfo().getPrice()));
		System.out.println();
		ptr = head.getNext();
		while (ptr.getNext() != null) {
			System.out.println(String.format("%-21s%-20s%-16s%-21s%.2f", ptr.getInfo().getName(),
					ptr.getInfo().getRfidTagNumber(), ptr.getInfo().getOriginalLocation(),
					ptr.getInfo().getCurrentLocation(), ptr.getInfo().getPrice()));
			ptr = ptr.getNext();
		}
		System.out.print(String.format("%-21s%-20s%-16s%-21s%.2f", ptr.getInfo().getName(),
				ptr.getInfo().getRfidTagNumber(), ptr.getInfo().getOriginalLocation(),
				ptr.getInfo().getCurrentLocation(), ptr.getInfo().getPrice()));
		System.out.println("\n");
	}
}
