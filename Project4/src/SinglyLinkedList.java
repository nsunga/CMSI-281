/** 
 * Created by: NICHOLAS SUNGA
 * COURSE #4116
 * April 21, 2016
 *
 * Purpose: Implementation of my own linked list. User must specify 
 * the type of items to store in the list.
 *
 * Input: none
 *
 * Output: none
 */

public class SinglyLinkedList<E> implements ISimpleList<E> {

	/**
	 * Purpose: nodes of linked list
	 * 
	 * Parameters: none
	 * 
	 * Input: none
	 * 
	 * Output: none
	 */
	private class Node { 
		E val;
		Node next;
	}
	
	private Node front;
	private Node back;
	private int size;
	
	
	/**
	 * Purpose: Return number of nodes in linked list. O(1)
	 * 
	 * Parameters: none
	 *
	 * Return: int - the linked list size
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Purpose: Add item e to the back of the LinkedList.
	 * If LinkedList is empty, e is first node. O(1)
	 * 
	 * Parameters: E - the item to be added
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public void add(E e) {
		Node n = new Node();
		n.val = e;
		
		if (front == null) {
			front = n;
			back = n; // case if LinkedList is empty
		} else {
			back.next = n; 
			back = n; // add to back
		}
		size++; 
		
		return; // this whole method was from you
	}

	/**
	 * Purpose: Get the item at position index. O(n)
	 * 
	 * Parameters: int - index of an item
	 *
	 * Return: E - the item at the index in the linked list
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public E get(int index) {
		Node temp = front;
			
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		
		return temp.val;
	}

	/**
	 * Purpose: Add item e at position index. O(n)
	 * 
	 * Parameters: E - the item to be added
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public void add(int index, E e) {		

		Node temp = front;
		Node createdNode = new Node();
		createdNode.val = e;
			
		if (index == 0) {
			if (front == null) {
				this.add(e);
				
				return; // case for adding in the front if list is empty
			} else {
				createdNode.next = front;
				front = createdNode;
				size++;
				
				return; // case for adding in the front
			}
		} else if (index == size) {
			this.add(e);
			
			return; // case for adding in the back
		} else {
			for (int i = 0; i < (index - 1); i++) {
				temp = temp.next;
			} 
	
			createdNode.next = temp.next;
			temp.next = createdNode;
			size++;
			
			return; // case for adding between front and back
		}
	}

	/**
	 * Purpose: Remove node at position index. O(n)
	 * 
	 * Parameters: int - index of a node to be
	 * removed from the linked list
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public void remove(int index) {
		Node temp = front;
		
		if (index == 0) {
			front = front.next;
			size--;
			
			return; // case for removing the first node
		} else {
			for (int i = 0; i < (index - 1); i++) {
				temp = temp.next;
			} // case for removing any node other than the first
			
			temp.next = temp.next.next; // skips the node after temp
			
			if (index == (size - 1)) {
				back = temp; // case for removing the last node
			} else {
				;
			}
		}

		size--;

		return;	
	}

	/**
	 * Purpose: Remove occurrences of e in linked list. Return
	 * the number of occurrences. O(n)
	 * 
	 * Parameters: E - the item to be removed
	 *
	 * Return: int - the occurrence of e that were removed
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public int remove(E e) {
		int occurrences = 0;
		Node temp = this.front;
		SinglyLinkedList<E> helperList = new SinglyLinkedList<E>();
		
		while (temp != null) {
			if (temp.val != e) {
				helperList.add(temp.val);
			} else {
				occurrences++;
			}
			
			temp = temp.next;
		}
		
		this.front = helperList.front;
		this.back = helperList.back;
		this.size = helperList.size; 
		// assign this LinkedList to the helperList

		return occurrences;
	}
}
