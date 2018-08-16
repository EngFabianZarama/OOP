//Fabian Zarama
//109599744
//CSE 214
//Homework #2

package week2;

/**
 * -currentLocation: a String that represents the location of the item at the
 * current time. It may be a shelf position (as described above), an encoding of
 * a cart number, which is designated by the letter 'c' followed by a 3 digit
 * number ("c101", "c001", "c347", etc.), or it may have been checked out by a
 * customer already in which case the location will be represented by the String
 * "out", where case is not important.
 */
public class ItemInfo {
	private String name; // Product name
	private double price;// Price of product as a positive value
	private String rfidTagNumber;// encodes the radio frequency to scann the
									// item length fixed to 9
	private String originalLocation;// encodes the original sheld position of
									// the item
	private String currentLocation;// Represents the location of the item at the
									// current time
	// Default Constructor

	public ItemInfo() {
	}

	/**
	 * @param name
	 *            Is the name of the item.
	 * @param price
	 *            Is the price of the item.
	 * @param rfidTagNumber
	 *            a String that encodes the radio frequency for scanning the
	 *            item. It is 9 characters long and represented in hexadecimal
	 *            format(base 16) which means each character is either a digit
	 *            from 0 to 9 or one of the letters A through F, where case is
	 *            not important. The length of this String is to be fixed at 9.
	 * @param originalLocation
	 *            a String that encodes the original shelf position of the item.
	 *            The first character is 's' to designate that it is a shelf
	 *            position and it is followed by a 5 digit shelf number to
	 *            further specify where it can be found in the store. (Examples
	 *            may be "s00013", "s90909", "s32760", etc.). The length of the
	 *            String is to be fixed at 6.
	 */

	public ItemInfo(String name, double price, String rfidTagNumber, String originalLocation) {
		this.name = name;
		this.price = price;

		try {
			if (rfidTagNumber.length() == 9 && rfidTagNumber.matches("-?[0-9a-fA-F]+")) {
				this.rfidTagNumber = rfidTagNumber;
			} else {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("The rfidTagNumber must be 9 Hex digits");
		}

		try {
			if (originalLocation.length() == 6 && originalLocation.charAt(0) == 's'
					&& originalLocation.substring(1, 6).matches("-?[0-9]+")) {
				this.originalLocation = originalLocation;
			} else {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Original Location must start with an \"s\" and 5 digits\n");

		}

		this.currentLocation = originalLocation;// Current location is on shelf
	}

	/**
	 * getters
	 */
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getRfidTagNumber() {
		return rfidTagNumber;
	}

	public String getOriginalLocation() {
		return originalLocation;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * Setters
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRfidTagNumber(String rfidTagNumber) {
		this.rfidTagNumber = rfidTagNumber;
	}

	public void setOriginalLocation(String orininalLocation) {
		this.originalLocation = orininalLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

}
