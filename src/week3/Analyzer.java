//Fabian Zarama
//109599744
//CSE 214
//Homework #3

package week3;

import java.util.Scanner;

public class Analyzer {
	public static void main(String[] args) {
		String a;
	
		while (true) {
			try {
				Scanner input = new Scanner(System.in).useDelimiter("\\n");
			
				// try
				System.out.println("Welcome to the Elevator simulator!\n");
				System.out.print("Please enter the probability of arrival for Requests: ");
				double prob = input.nextDouble();
				System.out.print("Please enter the number of floors: ");
				int floors = input.nextInt();
				System.out.print("Please enter the number of elevators: ");
				int el = input.nextInt();
				System.out.print("Please enter the length of the simulation (in time units): ");
				int length = input.nextInt();
				System.out.print("Method for simulation, regular(r) or optimal(o): ");
				char option = input.next().charAt(0);

				switch (option) {
				case 'r':
				case 'R':
					System.out.println();
					Simulator.simulate(prob, floors, el, length);
					break;
				case 'O':
				case 'o':
					System.out.println();
					OptimalSimulator.simulate(prob, floors, el, length);
					break;
				default:
					System.out.println("Thats not an option, please enter 'r' for regular or 'o' for optimal");
					break;
				}
			} catch (Exception e) {
				System.out.println("Error in the input...\n");
			}
		}

	}
}
