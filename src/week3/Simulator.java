package week3;

import java.util.ArrayList;

public class Simulator {

	private static int time;
	private static int request;
	private static int totalWait;

	/**
	 * 
	 * @param prob
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
		BooleanSource probablity = new BooleanSource(prob);
		RequestQueue queue = new RequestQueue();
		ArrayList<Elevator> elevators = new ArrayList<Elevator>();

		for (int i = 0; i < el; i++) {
			elevators.add(new Elevator());
		}

		time = 0;

		while (time < length) {
			if (probablity.requestArrived()) {
				Request enqueueTemp = new Request(floors);
				enqueueTemp.setTimeEntered(time);
				queue.enqueue(enqueueTemp);
			}

				for (int i = 0; i < elevators.size(); i++) {
					if (elevators.get(i).getElevatorState() == 0) {//Idle
						if (!queue.isEmpty()) {
							elevators.get(i).setRequest(queue.dequeue());
							elevators.get(i).setElevatorState(1);//to_source
						}
					}
				}

			for (int i = 0; i < elevators.size(); i++) {
				if (elevators.get(i).getElevatorState() == 1) {
					if (elevators.get(i).getCurrentFloor() < elevators.get(i).getRequest().getSourceFloor()) {
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor() + 1);
					}
					if (elevators.get(i).getCurrentFloor() > elevators.get(i).getRequest().getSourceFloor()) {
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor() - 1);
					}
					if (elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().getSourceFloor()) {
						elevators.get(i).setElevatorState(2);
						elevators.get(i).setDestinationFloor(elevators.get(i).getRequest().getDestinationFloor());
						request++;
						totalWait += (time - elevators.get(i).getRequest().getTimeEntered());
					}
				} else if (elevators.get(i).getElevatorState() == 2) {
					if (elevators.get(i).getCurrentFloor() < elevators.get(i).getRequest().getDestinationFloor()) {
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor() + 1);
					}
					if (elevators.get(i).getCurrentFloor() > elevators.get(i).getRequest().getDestinationFloor()) {
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor() - 1);
					}
					if (elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().getDestinationFloor()) {
						elevators.get(i).setElevatorState(0);
					}
				}
			}
			time++;
		}

		if (request == 0) {
			System.out.println("Total Wait Time: " + totalWait + "\nTotal Request: " + request + "\nAverave Wait Time: None");
		} else {
			double average = (double) (totalWait) / (double) (request);
			System.out.println("Total Wait Time: " + totalWait + "\nTotal Request: " + request);
			System.out.println(String.format("Averave Wait Time: %.2f", average));
		}

	}

}
