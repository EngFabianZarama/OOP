//Fabian Zarama
//109599744
//CSE 214
//Homework #2

package week2;

public class ItemInfoNode {
	private ItemInfo item = new ItemInfo();
	private ItemInfoNode prev;
	private ItemInfoNode next;

	/**
	 * Default constructor
	 */
	public ItemInfoNode() {
		item = null;
		prev = null;
		next = null;
	}

	/**
	 * Constructor to initialized with the double linked list
	 * @param item
	 * @param prev
	 * @param next
	 */
	public ItemInfoNode(ItemInfo item, ItemInfoNode prev, ItemInfoNode next) {
		this.item = item;
		this.prev = prev;
		this.next = next;
	}

	/**
	 * Setters and getters 
	 */
	public void setInfo(ItemInfo info) {
		item = info;
	}

	public void setNext(ItemInfoNode node) {
		next = node;
	}

	public void setPrev(ItemInfoNode node) {
		prev = node;
	}

	public ItemInfo getInfo() {
		return item;
	}

	public ItemInfoNode getNext() {
		return next;
	}

	public ItemInfoNode getPrev() {
		return prev;
	}

}
