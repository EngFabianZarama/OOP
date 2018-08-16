//Fabian Zarama
//109599744
//CSE 214
//Homework #3

package week3;

public class BooleanSource {
	private double probability;
	/**
	 * 
	 * @param p
	 * a probability parameter as the value of this member variable
	 * @throws IllegalArgumentException
	 * if the probability is less than 0 or bigger than 1
	 */
	public BooleanSource(double p) throws IllegalArgumentException {
		if (p < 0.0 || p > 1.0)
			throw new IllegalArgumentException();
		probability = p;
	}
	
	/**
	 *  To determine the return value of the requestArrived() method, 
	 *  Math.random() method is used.
	 * @return
	 * returns true a percentage of the time equal to probability (and otherwise it returns false).
	 */
	public boolean requestArrived() {
	    return (Math.random() < probability);
	}
}
