public interface ISimpleList<E> {
	/**
	 * 
	 * @return the list size
	 */
	int size();
	
	/**
	 * Add item e to the end of the list
	 * @param e
	 */
	void add(E e);

	/**
	 * Get the element at position index
	 * 
	 * @param index
	 * @return
	 */
	E get(int index);
	
	/**
	 * Add item e at position index
	 * 
	 * @param index
	 * @param element
	 */
	void add(int index, E e);
	
	/**
	 * Remove the item at position index
	 * 
	 * @param index
	 */
	void remove(int index);
	
	/**
	 * Remove any occurrences of element e from the list and return how many were removed
	 * 
	 * @param e the element to remove
	 * @return how many occurrences of e were removed
	 */
	int remove(E e);
}
