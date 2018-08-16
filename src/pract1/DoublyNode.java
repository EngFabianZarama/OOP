package midterm;


public class DoublyNode {
	private int item;
	private DoublyNode prev;
	private DoublyNode next;
	private DoublyNode head;

	/**
	 * Default constructor
	 */
	public DoublyNode() {
		item = 0;
		prev = null;
		next = null;
	}

	/**
	 * Constructor to initialized with the double linked list
	 * @param item
	 * @param prev
	 * @param next
	 */
	public DoublyNode(int item) {
		this.item = item;
		this.prev = null;
		this.next = null;
	}

	/**
	 * Setters and getters 
	 */
	public void setInfo(int info) {
		item = info;
	}

	public void setNext(DoublyNode node) {
		next = node;
	}

	public void setPrev(DoublyNode node) {
		prev = node;
	}

	public int getInfo() {
		return item;
	}

	public DoublyNode getNext() {
		return next;
	}

	public DoublyNode getPrev() {
		return prev;
	}
}
