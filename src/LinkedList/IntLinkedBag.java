package LinkedList;

public class IntLinkedBag implements Cloneable {
	private IntList data;
	private int manyItems;
	
	public IntLinkedBag() {
		  manyItems = 0;
		  data = new IntList();
		}
	
	public int getCapacity() {
		  return Integer.MAX_VALUE;
		}
	
	public int size() { return manyItems;
	}
	
	public void ensureCapacity (int minimumCapacity) {
		 // no work is needed
		}
	
	public void add(int element) {
		 data.addNewHead(element);
		manyItems++; }
	
	public int countOccurrences(int target) { int answer = 0;
	int index;
	data.resetCursor();
	  for (index=0; index<manyItems; index++)
	  {
	if (target == data.getNodeData()) answer++;
	    data.advanceCursor();
	  }
	  return answer;
	}
	
	
}
