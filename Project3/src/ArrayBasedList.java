/** Created by: NICHOLAS SUNGA
 * COURSE #4116
 * April 8, 2016
 *
 * Purpose: Source code for ArrayBasedList class. Implemented in "Project3.java"
 *
 * Input: int - index position, String - object to remove or add
 *
 * Output: none
 */

public class ArrayBasedList implements ISimpleList {

	private String[] arr = new String[4];
	private int logicalSize = 0; // amount of Strings in arr
	
	/* Purpose: default constructor
	 * 
	 * Parameters: none
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	public ArrayBasedList() {
		
	}
	
	/* Purpose: Returns the size of arr, O(1)
	 * 
	 * Parameters: none
	 *
	 * Return: int - logicalSize
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public int size() {
		return this.logicalSize;
	}

	/* Purpose: Add a String to arr - done by appending to the end of the array or
	 * at the 0th position if array is empty. If logical size equals capacity,
	 * arr doubles capacity. O(1)/O(n)
	 * 
	 * Parameters: String - object to add
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public void add(String e) {
		if ( this.logicalSize == this.arr.length ) {
			ensureCapacity();
			// double arr capacity w/ same Strings
			
			this.arr[logicalSize++] = e;
		} else {
			this.arr[logicalSize++] = e;
		}
	}
	
	/* Purpose: Return the String with the corresponding index. 
	 * If the reference is null, NullPointerException thrown. O(1)
	 * 
	 * Parameters: String - object to return
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public String get(int index) {
		try {
			if (this.arr[index] == null) {
				throw new NullPointerException();
			} else {
				return this.arr[index];
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
			// Trying to get null reference
		}
	}

	/* Purpose: Add a String at the given index. Before adding, all
	 * Strings at the given index up to the logical size are shifted
	 * one to the right. Index must be less than/equal to 
	 * logicalSize because it makes no sense to add past that.O(n)
	 * 
	 * Parameters: index - position, String - object to add
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public void add(int index, String e) {
		if (index <= logicalSize) {
			if ( this.logicalSize == this.arr.length ) {	
				ensureCapacity();
				// double arr capacity w/ same strings
				
				for (int i = this.logicalSize; i > index; i--) { 
					this.arr[i] = this.arr[i-1];
				} // shift Strings from the back and work till index
			
				this.arr[index] = e;
				this.logicalSize++;
			} else {
				for (int i = this.logicalSize; i > index; i--) {
					this.arr[i] = this.arr[i-1];
				} // shift Strings from the back and work till index
					
				this.arr[index] = e;
				this.logicalSize++;
			}
		} else {
			throw new ArrayIndexOutOfBoundsException();
			// index greater than the logicalSize
			// There exists a reference before the index pointing to null
		}
	}

	/* Purpose: Remove a String at the given index. O(n)
	 * 
	 * Parameters: index - position
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public void remove(int index) {		
		this.arr[index] = null;
		for (int i = index; i < this.logicalSize; i++) {
			this.arr[i] = this.arr[i + 1];
		} // shift left from index(excluded) to logicalSize
		
		logicalSize--;
		// last index of logicalSize gets nulled
		// shouldn't be accessed		
	}

	/* Purpose: Remove all occurrences of given String. O(n)
	 * 
	 * Parameters: String to be removed
	 *
	 * Return: number of Strings removed
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public int remove(String e) {
		int nullCounter = 0;
		ArrayBasedList noNullList = new ArrayBasedList();
		noNullList.arr = new String[this.arr.length];
		
		for (int i = 0; i < this.logicalSize; i++) {
			if (this.arr[i].equals(e)) {
				this.arr[i] = null;
				nullCounter++;
			} else if (!this.arr[i].equals(e)) {
				noNullList.add(this.arr[i]);
			} else {
				continue;
			}
		} // get the number of occurrences of e, set to null,
		  // make a new ArrayBasedList without e.
		
		this.arr = noNullList.arr;
		// assign this array to the array without e

		this.logicalSize -= nullCounter;

		return nullCounter;		
	}
	
	/* Purpose: assign arr to a different array w/ double capacity and
	 * same Strings. O(n)
	 * 
	 * Parameters:
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	public void ensureCapacity() {
		String[] doubleList = new String[this.arr.length * 2];
		
		for (int i = 0; i < this.logicalSize; i++) {
			doubleList[i] = this.arr[i];
		} // copy arr
		
		this.arr = doubleList;
	}
	
	
	/**
	 * UNNECESSARY FUNCTIONS
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public int remove(String e) {
	 *	int nullCounter = 0;
	 *	int quarterSize = this.arr.length/4;
	 *	ArrayBasedList noNullList = new ArrayBasedList();
	 *	noNullList.arr = new String[this.arr.length];
	 *	
	 *	for (int i = 0; i < this.logicalSize; i++) {
	 *		if (this.arr[i].equals(e)) {
	 *			this.arr[i] = null;
	 *			nullCounter++;
	 *		} else if (!this.arr[i].equals(e)) {
	 *			noNullList.add(this.arr[i]);
	 *		} else {
	 *			continue;
	 *		}
	 *	} // get the number of occurrences of e, set to null,
	 *	  // make a new ArrayBasedList without e.
	 *	
	 *	this.arr = noNullList.arr;
	 *	// assign this array with the array without e
	 *
	 *		this.logicalSize -= nullCounter;
	 *		
	 *		if (this.logicalSize == quarterSize) {
	 *			String[] halfList = new String[this.arr.length / 2];
	 *
	 *			for (int i = 0; i < this.logicalSize; i++) {
	 *				halfList[i] = this.arr[i];
	 *			}
	 *			
	 *			this.arr = halfList;
	 *		} else {
	 *			;
	 *		} // if logicalSize is 1/4 capacity, give arr half capacity
	 *
	 *		return nullCounter;		
	 *	}

	 *public void remove(int index) {
	 *		int quarterSize = (this.arr.length)/4;
	 *		
	 *		//this.arr[index] = null; unnecessary?
	 *		
	 *		for (int i = index; i < this.logicalSize; i++) {
	 *			this.arr[i] = this.arr[i +1];
	 *		} // all strings from (excluded) index to logical size are shifted to the left
	 *		
	 *		logicalSize--;
	 *		// last index of logicalSize gets nulled, ie. logSize = 4, arr[3] = null
 	 *		// shouldn't be accessed		
	 *		
	 *		if (this.logicalSize == quarterSize) {
	 *			String[] halfList = new String[this.arr.length / 2];
	 *			
	 *			for (int i = 0; i < this.logicalSize; i++) {
	 *				halfList[i] = this.arr[i];
	 *			}
	 *			
	 *			this.arr = halfList;
	 *		} else {
	 *			;
	 *		} // if logicalSize is 1/4 capacity, give arr half capacity
	 *	}
	 */
}
