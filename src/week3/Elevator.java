//Fabian Zarama
//109599744
//CSE 214
//Homework #3

package week3;

public class Elevator {
	private final int IDLE = 0;
	private final int TO_SOURCE = 1;
	private final int TO_DESTINATION = 2;
	private int currentFloor;
	private int elevatorState;//IDLE, TO_SOURCE, TO_DESTINATION
	private Request request;//the request been handle or null if the Elevator is idle
	private int destinationFloor;
	private boolean up;
	
	public Elevator(){
		
		request = null;
		elevatorState = IDLE;
		currentFloor = 1;
		this.setDestinationFloor(1);

	}

	/**
	 * @return the iDLE
	 */
	public int getIDLE() {
		return IDLE;
	}

	/**
	 * @return the tO_SOURCE
	 */
	public int getTO_SOURCE() {
		return TO_SOURCE;
	}

	/**
	 * @return the tO_DESTINATION
	 */
	public int getTO_DESTINATION() {
		return TO_DESTINATION;
	}

	/**
	 * @return the currentFloor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * @param currentFloor the currentFloor to set
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * @return the elevatorState
	 */
	public int getElevatorState() {
		return elevatorState;
	}

	/**
	 * @param elevatorState the elevatorState to set
	 */
	public void setElevatorState(int elevatorState) {
		this.elevatorState = elevatorState;
	}

	/**
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}
	
	/**
	 * @param destinationFloor
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}
	
	/**
	 * @return destinationFloor()
	 */
	public int getDestinationFloor() {
		return destinationFloor;
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
