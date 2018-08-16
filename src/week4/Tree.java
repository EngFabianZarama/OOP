//Fabian Zarama
//109599744
//CSE 214
//Homework #4

package week4;

import java.util.Scanner;

public class Tree {
	private TreeNode root;

	/**
	 * Tree() constructor that makes the root of the tree with its contents as
	 * label, prompt and message
	 */
	public Tree(String prompt, String message, int nodes) {
		root = new TreeNode(nodes);
		root.setLabel("root");
		root.setPrompt(prompt);
		root.setMessage(message);
	}
	
	/**
	 * 
	 * @return the root of the tree
	 */
	public TreeNode getRoot() {
		return root;
	}


	/**
	 * A method to add a TreeNode to the tree. The location will be a child of
	 * parentLabel. The child node should be left justified meaning that it
	 * should first be placed in the left most TreeNode reference, then the
	 * middle, then the right. A return value of true indicates that the node
	 * was successfully added to the tree. Otherwise, the return value is false.
	 * More info on the label is in the input file format.
	 * 
	 * @param label
	 * @param prompt
	 * @param message
	 * @param parentLabel
	 * @return true if the node is found, else return false
	 */
	public boolean addNode(String label, String prompt, String message, String parentLabel, int nodes) {
		TreeNode search = new TreeNode(nodes);
		search = root;

		search = search.getNodeReference(parentLabel);

		if (search == null) {
			System.out.println("No node found: " + parentLabel);
		}
		for (int i = 0; i < search.getBranch().length; i++) {
			if (search.getBranch()[i] == null) {
				TreeNode newNode = new TreeNode(nodes);
				newNode.setLabel(label);
				newNode.setMessage(message);
				newNode.setPrompt(prompt);
				search.getBranch()[i] = newNode;
				return true;
			}
		}
		return false;
	}

	/**
	 * Traverses the tree in preorder, and prints the contents of the tree to
	 * the screen.
	 */
	public void preOrder() {
		root.preOrder();
	}

	/**
	 * 
	 * @param node
	 * @return the number of elements in the array
	 */
	public static int getSize(TreeNode node) {
		int i = 0;
		for (int j = 0; j < node.getBranch().length; j++) {
			if (node.getBranch()[j] instanceof TreeNode) {
				i++;
			}
		}
		return i;
	}

	/**
	 * This method will be used to start the question and answer session.
	 */
	public void beginSession(TreeNode current, int nodes) {

		Scanner input = new Scanner(System.in);
		String ch = null;
		TreeNode currentNode = new TreeNode(nodes);
		TreeNode prevNode = null;
		currentNode = current;
		System.out.println("Help Session Starting...");

		while (ch != "0") {
			try {
				System.out.println(currentNode.getMessage());
				for (int i = 0; i < getSize(currentNode); i++) {
					System.out.println(i + 1 + ") " + currentNode.getBranch()[i].getPrompt());
				}
				System.out.println("B) Prev question.");
				System.out.println("0) Exit Session.");
				System.out.print("Choice> ");
				ch = input.nextLine();
				if (ch.equals("0")) {
					break;
				}

				if (ch.toLowerCase().equals("b")) {
					currentNode = prevNode;
					System.out.println();
					continue;
				}
				prevNode = currentNode;
				currentNode = currentNode.getBranch()[Integer.parseInt(ch) - 1];

				System.out.println();

				if (currentNode.getBranch()[0] == null) {
					System.out.println(currentNode.getMessage() + "\n");
					System.out.println("Thank you for using this automated help service!");
					break;
				}

			} catch (Exception e) {
				System.out.println();
				System.out.println("Not an option");
				return;
			}
		}

	}

}
