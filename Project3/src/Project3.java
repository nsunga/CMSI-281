/** Created by: NICHOLAS SUNGA
 * COURSE #4116
 * April 8, 2016
 *
 * Purpose: Created and implemented my own ArrayList. However, nothing 
 * shows up on the console. Professor is only concerned about algorithmic complexity.
 *
 * Input: none
 *
 * Output: none
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Project3 {

	/**
	 * Read lines from the file and put each line into the list in reverse order
	 * 
	 * @param filename the filename
	 * @return A list containing the file contents in reverse order 
	 * (first line in file is last in list and last line in file is first in list)
	 */
	static ISimpleList getFileContentsInReverse(String filename) {
		
		ISimpleList nonReversedList = new ArrayBasedList();
		ISimpleList reversedList = new ArrayBasedList();

		try (Scanner input = new Scanner(new File(filename))) {
			while (input.hasNext()) {
				String line = input.nextLine();
				nonReversedList.add(line);
			} // get the lines in the file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 1; i-1 < nonReversedList.size(); i++) { 
			reversedList.add(nonReversedList.get(nonReversedList.size()-i));
		} // reverse the lines in the file by storing them in another List

		return reversedList;
	}

	public static void main(String[] args) {
		/**** PAY NO ATTENTION TO THIS CODE **
		 *
		 * String thisString = new String("smc_grads_at_uc_1.txt");
		 * ArrayBasedList testClass = new ArrayBasedList();
		 * testClass = (ArrayBasedList) getFileContentsInReverse(thisString);
		 * System.out.println(testClass.printArray());
		 */
	}
}
