/** 
 * Created by: NICHOLAS SUNGA
 * COURSE #4116
 * April 21, 2016
 *
 * Purpose: Contains implementation of getFileContentsInReverse.
 * File is meant to test SinglyLinkedList and SimpleStack classes
 *
 * Input: none
 *
 * Output: none
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project4 {

	/**
	 * Read lines from the file and put each line into the list in reverse order
	 * 
	 * @param filename the filename
	 * @return A list containing the file contents in reverse order 
	 * (first line in file is last in list and last line in file is first in list)
	 */
	static ISimpleList<String> getFileContentsInReverse(String filename) {
		SinglyLinkedList<String> nonReversedList = new SinglyLinkedList<String>();
		SinglyLinkedList<String> reversedList = new SinglyLinkedList<String>();
		
		try (Scanner input = new Scanner(new File(filename))) {
			while (input.hasNext()) {
				String line = input.nextLine();
				nonReversedList.add(line);
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // fill nonReversedList
		
		int size = nonReversedList.size();
		for (int i = 0; i < size; i++) { 
			reversedList.add(0, nonReversedList.get(0));
			nonReversedList.remove(0);
		} // reverse the lines in the file by storing them in another LinkedList

		return reversedList;
	}

	public static void main(String[] args) {
		// DO NOT PAY ATTENTION TO THIS
		
		//String thisString = new String("smc_grads_at_uc_1.txt");
		//String writeToThis = new String("smc_grads_at_uc_2.txt");
		//SinglyLinkedList<String> testClass = (SinglyLinkedList<String>)getFileContentsInReverse(thisString);

		//testClass.printThis();
		//int size = testClass.size();
		
		//SimpleStack<String> testStack = new SimpleStack<String>();
		
		//System.out.println(testStack.isEmpty());
		//testStack.push("String one");
		//System.out.println(testStack.isEmpty());
		//System.out.println(testStack.peek());
		//testStack.push("String two");
		//testStack.push("String three");
		//testStack.push("but its pretty cool");
		//testStack.push("CODING IS FRUSTRATING MOST OF THE TIME");
		//for (int i = 0; i < 5; i++) {
		//	System.out.println(testStack.pop());
		//}
		//System.out.println(testStack.isEmpty());

	}
}
