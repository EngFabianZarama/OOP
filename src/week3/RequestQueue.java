//Fabian Zarama
//109599744
//CSE 214
//Homework #3

package week3;

import java.util.ArrayList;

public class RequestQueue extends ArrayList<Request> {


	/**
	 * insert an element request to the rear of the queue
	 * 
	 * @param request
	 *            previously initiated as a type Request to add at the end of
	 *            the queue
	 */
	
	public void enqueue(Request request) {
		this.add(request);
	}

	/**
	 * Remove the front element request from the queue
	 * 
	 * @return
	 * @throws EmptyQueueException
	 */
	public Request dequeue() throws EmptyQueueException {
		if (this.isEmpty()) {
			throw new EmptyQueueException();
		}
		return this.remove(0);
	}
}
