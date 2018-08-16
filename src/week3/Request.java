//Fabian Zarama
//109599744
//CSE 214
//Homework #3

package week3;

public class Request {
	private int sourceFloor;
	private int destinationFloor;
	private int timeEntered;// - The time that this request was placed on the
							// queue
	private boolean up;
	/**
	 * detinationFloor = gets a random number between 1 and floors(inclusive)
	 * sourceFloor = gets a random number between 1 and floors(incluse)
	 * 
	 * @param floors
	 */
	public Request(int floors) {
		destinationFloor = (int) (Math.random() * floors) + 1;
		sourceFloor = (int) (Math.random() * floors) + 1;
	}

	/**
	 * @return the sourceFloor
	 */
	public int getSourceFloor() {
		return sourceFloor;
	}

	/**
	 * @param sourceFloor
	 *            the sourceFloor to set
	 */
	public void setSourceFloor(int sourceFloor) {
		this.sourceFloor = sourceFloor;
	}

	/**
	 * @return the destinationFloor
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}

	/**
	 * @param destinationFloor
	 *            the destinationFloor to set
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	/**
	 * @return the timeEntered
	 */
	public int getTimeEntered() {
		return timeEntered;
	}

	/**
	 * @param timeEntered
	 *            the timeEntered to set
	 */
	public void setTimeEntered(int timeEntered) {
		this.timeEntered = timeEntered;
	}
	
	/**
	 * 
	 * @param up
	 */
	public void setUp(boolean up) {
		this.up = up;
	}
	
	/**
	 * 
	 * @return up
	 */
	public boolean isUp() {
		return up;
	}	

}
