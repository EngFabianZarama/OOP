//Fabian Zarama
//109599744
//CSE 214
//Homework #3

package week3;

import java.util.ArrayList;

public class OptimalSimulator {

	

	/**
	 * This is an optimal simulation were Elevators now also have a direction,
	 * up or down. As an elevator passes a floor, all waiting passengers are
	 * picked up that want to go in that direction. An elevator moves until it
	 * no longer has any requests (sources or destinations) in the direction it
	 * was moving. At this point, it is now idle. Elevators can carry an
	 * unlimited number of passengers. Idle elevators can move in any direction
	 * to pick up requests.
	 * 
	 * The algorithm used to service the request is first looking at the destination floor 
	 * and the source floor, if destination floor is higher than source floor it means is going up
	 * other wise is going down. then in the array of goingUp or goingDown a flag is set. Then since the
	 * upRequests array is empty in the location of the goingUpg the request object is assign to this location.
	 * The same would happened if the elevator is going down but instead of using upRequest as Request object, I am 
	 * using down request as a request object. Next in a single for loop a idle elevator will be found. This elevator 
	 * is set a request to go up or down depending on the selection with the request previously stored in the 
	 * Request object array. To this elevator is designated the destination floor and the elevator is set to the condition
	 * TO_SOURCE and a boolean flag is set to true if the current floor is less than the source floor, false otherwise
	 * This same loop takes the elevator that is set TO_SOURCE where this elevator starts going to the destination and 
	 * finally set to TO_DESTINATION after each floor the variable time is set to increase one unit. If there is another 
	 * request the first idle elevator will be used, if there is no idle elevator then, the elevator that is closest to 
	 * the source request and in the same direction to the destination of this request will take care of this new request, 
	 * setting this elevator with another TO_SOURCE flag stored in the array. Finally when the elevators changes the flag 
	 * to TO_DESTINATION it means that the request is over, and if it does not contain any more request then the elevator 
	 * is again set to IDLE.			 
	 *
	 *
	 *@param prob
	 *            probability of a request being introduced per time unit
	 *            between 0 and 1 inclusive
	 * @param floors
	 *            The number of floors int he building
	 * @param elevators
	 *            The number of elevators in the building grater than 1 grater
	 *            than 0
	 * @param length
	 *            The length of the simulation in time units grater than 0
	 * @throws EmptyQueueException
	 * in case the queue is empty it throws an exception
	 */
	public static void simulate(double prob, int floors, int el, int length) throws EmptyQueueException {
		if (prob < 0) {
			System.out.println("Probability can not be less than 0");
			return;
		} else if (1 < prob) {
			System.out.println("Probability can not be more than 1");
			return;
		} else if (floors <= 1) {
			System.out.println("Floors can not be less or equal than 1");
			return;
		} else if (length < 1) {
			System.out.println("Length of the simulation has to be grater than 0");
			return;
		} else if (el <= 0) {
			System.out.println("Elevators has to be grater than 0");
			return;
		}
		
		ArrayList<Elevator> elevator = new ArrayList<Elevator>();
		BooleanSource probablity = new BooleanSource(prob);
		int request = 0;
		int totalWait = 0;
		int time=0;
		Request[] upRequests = new Request[floors + 1];
		Request[] downRequests = new Request[floors + 1];
		int[] goingUp = new int[floors + 1];;
		int[] goingDown = new int[floors + 1];;
		
		
		for (int i = 0; i < el; i++) {
			elevator.add(new Elevator());
		}

		while (time < length) {
			//is it going up or down?
			if (probablity.requestArrived()) {
				Request enqueueTemp = new Request(floors);
				if (enqueueTemp.getSourceFloor() <= enqueueTemp.getDestinationFloor()) {
					goingUp[enqueueTemp.getSourceFloor()]++;
					if (upRequests[enqueueTemp.getSourceFloor()] == null) {
						upRequests[enqueueTemp.getSourceFloor()] = enqueueTemp;
					} else {
						if (upRequests[enqueueTemp.getSourceFloor()].getDestinationFloor() <= enqueueTemp.getDestinationFloor()){
							upRequests[enqueueTemp.getSourceFloor()] = enqueueTemp;
						}
					}
				} else {
					if (downRequests[enqueueTemp.getSourceFloor()] == null) {
						downRequests[enqueueTemp.getSourceFloor()] = enqueueTemp;
					} else {
						goingDown[enqueueTemp.getSourceFloor()]++;
						if (downRequests[enqueueTemp.getSourceFloor()].getDestinationFloor() >= enqueueTemp.getDestinationFloor()){
							downRequests[enqueueTemp.getSourceFloor()] = enqueueTemp;
						}
					}
				}
			}
			for (int i = 0; i < elevator.size(); i++) {
				if (elevator.get(i).getElevatorState() == 0) {//Idle
					for (int j = 0; j < upRequests.length; j++) {
						if (upRequests[j] != null) {
								elevator.get(i).setRequest(upRequests[j]);
								upRequests[j] = null;
						} else if (downRequests[j] != null) {
								elevator.get(i).setRequest(downRequests[j]);
								downRequests[j] = null;
						}
					}
					if (elevator.get(i).getRequest() != null) {
						elevator.get(i).setDestinationFloor(elevator.get(i).getRequest().getDestinationFloor());
						elevator.get(i).setElevatorState(1);//TO_SOURCE
						elevator.get(i).setUp(elevator.get(i).getCurrentFloor() < elevator.get(i).getRequest().getSourceFloor());
					}
				}
				if (elevator.get(i).getElevatorState() == 1) {//TO_SOURCE
					if (elevator.get(i).getCurrentFloor() < elevator.get(i).getRequest().getSourceFloor()) {
						if (upRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() > elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							upRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingUp[elevator.get(i).getCurrentFloor()];
							goingUp[elevator.get(i).getCurrentFloor()] = 0;
						}
						elevator.get(i).setCurrentFloor(elevator.get(i).getCurrentFloor() + 1);
						if (upRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() > elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							upRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingUp[elevator.get(i).getCurrentFloor()];
							goingUp[elevator.get(i).getCurrentFloor()] = 0;
						}
					}
					if (elevator.get(i).getCurrentFloor() > elevator.get(i).getRequest().getSourceFloor()) {
						if (downRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() < elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							downRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingDown[elevator.get(i).getCurrentFloor()];
							goingDown[elevator.get(i).getCurrentFloor()] = 0;
						}
						elevator.get(i).setCurrentFloor(elevator.get(i).getCurrentFloor() - 1);
						if (downRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() < elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							downRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingDown[elevator.get(i).getCurrentFloor()];
							goingDown[elevator.get(i).getCurrentFloor()] = 0;
						}
					}
					if (elevator.get(i).getCurrentFloor() == elevator.get(i).getRequest().getSourceFloor()) {
						elevator.get(i).setElevatorState(2);
						if (elevator.get(i).getRequest().isUp()) {
							request += goingUp[elevator.get(i).getCurrentFloor()];
							goingUp[elevator.get(i).getCurrentFloor()] = 0;
						} else {
							request += goingDown[elevator.get(i).getCurrentFloor()];
							goingDown[elevator.get(i).getCurrentFloor()] = 0;
						}
					}
				} else if (elevator.get(i).getElevatorState() == 2) {//TO_DESTINATION
					if (elevator.get(i).getCurrentFloor() < elevator.get(i).getRequest().getDestinationFloor()) {
						if (upRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() > elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							upRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingUp[elevator.get(i).getCurrentFloor()];
							goingUp[elevator.get(i).getCurrentFloor()] = 0;
						}
						elevator.get(i).setCurrentFloor(elevator.get(i).getCurrentFloor() + 1);
						if (upRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() > elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(upRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							upRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingUp[elevator.get(i).getCurrentFloor()];
							goingUp[elevator.get(i).getCurrentFloor()] = 0;
						}
					}
					if (elevator.get(i).getCurrentFloor() > elevator.get(i).getRequest().getDestinationFloor()) {
						if (downRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() < elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							downRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingDown[elevator.get(i).getCurrentFloor()];
							goingDown[elevator.get(i).getCurrentFloor()] = 0;
						}
						elevator.get(i).setCurrentFloor(elevator.get(i).getCurrentFloor() - 1);
						if (downRequests[elevator.get(i).getCurrentFloor()] != null) {
							if (downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor() < elevator.get(i).getRequest().getDestinationFloor()) {
								elevator.get(i).setDestinationFloor(downRequests[elevator.get(i).getCurrentFloor()].getDestinationFloor());
							}
							downRequests[elevator.get(i).getCurrentFloor()] = null;
							request += goingDown[elevator.get(i).getCurrentFloor()];
							goingDown[elevator.get(i).getCurrentFloor()] = 0;
						}
					}
					if (elevator.get(i).getCurrentFloor() == elevator.get(i).getRequest().getDestinationFloor()) {
						elevator.get(i).setElevatorState(0);
						elevator.get(i).setRequest(null);
					}
				}
			}
			time++;
			for (int i = 0; i < upRequests.length; i++) {
				totalWait += goingUp[i] + goingDown[i];
			}
		}
		if (request == 0) {//Idle
			System.out.println(
					"Total Wait Time: " + totalWait + "\nTotal Request: " + request + "\nAverave Wait Time: None");
		} else {
			double average = (double) (totalWait) / (double) (request);
			System.out.println("Total Wait Time: " + totalWait + "\nTotal Request: " + request);
			System.out.println(String.format("Averave Wait Time: %.2f", average));
		}
	}

}