//Fabian Zarama
//109599744
//CSE 214
//Homework #4

package week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class TreeDriver {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean quit = false;
		int nodes = 9;

		Tree tree = null;

		while (quit == false) {
			System.out.println("\nL - Load a Tree.");
			System.out.println("H - Begin a Help Session.");
			System.out.println("T - Traverse the Tree in preorder.");
			System.out.println("Q - Quit");
			System.out.print("choice> ");
			String ch = input.nextLine();

			try {
				switch (ch.toLowerCase()) {
				case "l":
					System.out.print("Enter the file name> ");
					String file = input.nextLine();
					if (file.length() > 20) {
						System.out.println("The max length of the file is 20");
						break;
					}
					System.out.println();

					try (BufferedReader br = new BufferedReader(new FileReader(file))) {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();

						while (line != null) {
							if (line.trim().length() > 0) {
								sb.append(line);
								sb.append(System.lineSeparator());
							}
							line = br.readLine();
						}
						String everything = sb.toString();

						if (everything.isEmpty()) {
							System.out.println("File is empty");
						}

						String[] lines = everything.split(System.getProperty("line.separator"));

						// clean lines
						for (int i = 0; i < lines.length; i++) {
							if (lines[i].contains("  ")) {
								lines[i] = lines[i].substring(0, lines[i].indexOf("  "));
							}

						}

						// create a Tree with specified root
						if (lines[0].contains("root")) {

							tree = new Tree(lines[1], lines[2], nodes);

						} else {
							System.out.println("Line 1 of the file is not a root label");
							break;
						}

						int getLabel = lines[3].indexOf(" ");
						int numberOfNodes = Integer.parseInt(lines[3].substring(getLabel + 1, getLabel + 2));

						// Add nodes for root
						int j = 4;
						for (int i = 0; i < numberOfNodes; i++) {
							tree.addNode(lines[j], lines[j + 1], lines[j + 2], "root", nodes);
							j += 3;
						}

						// Add rest of the nodes
						while (lines.length > j) {
							getLabel = lines[j].indexOf(" ");// line j=13, 23
							numberOfNodes = Integer.parseInt(lines[j].substring(getLabel + 1, getLabel + 2));
							String label = lines[j].substring(0, getLabel);
							for (int i = 0; i < numberOfNodes; i++) {
								tree.addNode(lines[j + 1], lines[j + 2], lines[j + 3], label, nodes);
								j += 3;
							}
							j++;
						}

					} catch (FileNotFoundException e) {
						System.out.println("file not found\n");
						break;
					} catch (IOException e) {
						System.out.println("Error in format File\n");
					}

					System.out.println("Tree created successfully!\n");
					break;
				case "h":
					if (tree == null) {
						System.out.println("\nPlease Load a Tree first\n");
						break;
					}
					System.out.println();
					tree.beginSession(tree.getRoot(), nodes);
					System.out.println();
					break;
				case "t":
					if (tree == null) {
						System.out.println("\nPlease Load a Tree first\n");
						break;
					}
					System.out.println();
					System.out.println("Traversing the tree in preorder:");
					tree.preOrder();
					System.out.println();
					break;

				case "q":
					quit = true;
					break;
				default:
					System.out.println("This is not an option..\n");
				}
			} catch (Exception e) {
				System.out.println("\nError of input File.\n");
			}
		}

	}
}
