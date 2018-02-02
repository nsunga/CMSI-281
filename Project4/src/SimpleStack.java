/** 
 * Created by: NICHOLAS SUNGA
 * COURSE #4116
 * April 21, 2016
 *
 * Purpose: Implementation of my own Stack that is designed with a LinkedList.
 *
 * Input: none
 *
 * Output: none
 */

public class SimpleStack<E> implements ISimpleStack<E>{

	private SinglyLinkedList<E> myStack = new SinglyLinkedList<E>();
	private E topValue;
	
	/**
	 * Purpose: Check if stack is empty.
	 * Accessing first element. No traversing linked list - O(1)
	 * 
	 * Parameters: none
	 *
	 * Return: true - stack is empty, false - stack is not
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public boolean isEmpty() {
		try {
			if (myStack.get(0) == null) {
				throw new NullPointerException();
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**
	 * Purpose: Push item e to the top of the stack.
	 * Adds to top. No traversing linked list - O(1)
	 * 
	 * Parameters: E - the item to be pushed
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public void push(E e) {
		myStack.add(0, e);
	}

	/**
	 * Purpose: Remove the item on top of the stack and return it.
	 * Accessing first element. No traversing linked list - O(1)
	 * 
	 * Parameters: none
	 *
	 * Return: E - top item that was removed from the stack
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public E pop() {
		topValue = myStack.get(0);
		myStack.remove(0);
		return topValue;
	}

	/**
	 * Purpose: Check what item is on top of the stack and return it.
	 * Accessing first element. No traversing linked list - O(1)
	 * 
	 * Parameters: none
	 *
	 * Return: E - top item on the stack
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public E peek() {
		return (topValue = myStack.get(0));
	}

}
