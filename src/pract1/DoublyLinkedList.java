package midterm;

public class DoublyLinkedList {
	private DoublyNode head;
	private DoublyNode tail;
	private DoublyNode cursor;
	private int size;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		cursor = null;
		size = 0;
	}

	public void addNewHead(int element) {
		DoublyNode newNode = new DoublyNode(element);
		newNode.setNext(head);
		head = newNode;
		cursor = newNode;
		if (tail == null) {
			tail = head;
		}
		size++;
	}

	public void addAfterCursor(int element) {
		DoublyNode newNode = new DoublyNode(element);

		if (cursor == null) {
			head = newNode;
			tail = newNode;
			cursor = newNode;
			size++;
		} else {

			if (cursor.getNext() == null) {
				newNode.setPrev(cursor);
				cursor.setNext(newNode);
				tail = newNode;
				cursor = tail;
				size++;
				return;
			}

			newNode.setNext(cursor.getNext());
			cursor.setNext(newNode);
			cursor.getNext().setPrev(newNode);
			newNode.setPrev(cursor);
			newNode.setPrev(cursor);
			size++;

		}
	}

	public void printAll() {

		while (cursor.getPrev() != null) {
			cursor = cursor.getPrev();
		}
		for (int i = 1; i <= size; i++) {
			System.out.print(cursor.getInfo() + " ");
			cursor = cursor.getNext();
		}
		return;
	}

	// returns the element at position index and then removes that node. The
	// first node in the list is index 1. If index does not correctly correspond
	// to a node, throw an exception.

	public int removeAt(int index) throws IllegalArgumentException {
		index--;
		int place = 0;
		cursor = head;
		int tam =1;
		
		while(cursor.getNext()!=null){
			tam++;
			cursor = cursor.getNext();
		}
		cursor = head;
		for (int i = 1; i <= tam; i++) {

			if (index == place) {
				
				//check to see if is the head
				if(cursor.getPrev()==null){
					cursor.getNext().setPrev(null);
					size--;
					return cursor.getInfo();
				}
				
				
				cursor.getPrev().setNext(cursor.getNext());
				cursor.getNext().setPrev(cursor.getPrev());
				size--;
				return cursor.getInfo();
			}
			cursor = cursor.getNext();
			place++;
		}

		return index;

	}

	public static void main(String[] args) {
		DoublyLinkedList a = new DoublyLinkedList();
		a.addNewHead(0);
		a.addAfterCursor(1);
		a.addAfterCursor(2);
		a.printAll();
		System.out.println();
		System.out.println(a.removeAt(1));
		a.printAll();
	}
}
