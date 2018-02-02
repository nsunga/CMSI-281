import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Purpose: Driver code that creates and organizes a list of students based on ID Number.
 * Students in question are SMC grads that attend UCs. Program demonstrates an example
 * in which a binary search is preferred instead a linear one in this case.
 * 
 * Input: None
 * 
 * Output: statement of the time required to complete the task through a linear search
 * and another with a binary search. 
 * 
 * @author NICHOLAS SUNGA
 * @ID 1477113
 *
 */

public class Project2 {

	/**
	 * Read in a file containing student records and create an array of Student
	 * objects
	 * 
	 * @param file The filename
	 * @param num The number of students in the file
	 * @return An array of Student objects
	 */
	static Student[] readStudentsFromFile(String filename, int num) {
		try (Scanner in = new Scanner(new File(filename))) {
			Student[] students = new Student[num];
			
			int i = 0; // counter to avoid ArrayIndexOutOfBounds
			
			while (in.hasNext() && i < num) {
				String line = in.nextLine();
				String[] lineParsed = line.split(",");
				
				int idNumber = Integer.parseInt(lineParsed[1]); // ID String to int
				
				students[i++] = new Student(lineParsed[0], idNumber, lineParsed[2]);
			} // get line from file and split it into three strings. Use the data to
			  // construct Student objects in students until full
			
			return students;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Write an array of Student objects to a file
	 * 
	 * @param students The array of Student objects to write out
	 * @param filename The output filename
	 */
	static void writeStudentsToFile(Student[] students, String filename) {
		
		try (FileWriter out = new FileWriter(filename)) {
			for (Student test: students) {
				out.write(test.toString() + "\n");
			}  //Write down every Student in the array to the file
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * comparing it with every student in group2.
	 * 
	 * @param group1 A group of Students
	 * @param group2 A group of Students
	 * @param numCommon number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents1(Student[] group1, Student[] group2,
			int numCommon) {
		Student[] common = new Student[numCommon];
		int i = 0; // counter to avoid ArrayIndexOutOfBounds
		
		while(i < numCommon) {
			for (Student st: group1) {
				for(Student otherSt: group2) {
					if(st.equals(otherSt)) {
						common[i++] = st;
					} else {
						continue;
					}
				}
			}			
		} // Linear search. Gets one Student from group1.
		  // Compares this Student with every Student in group2.
		  // If match, then assign this Student to array "common." Repeat until "common" full.

		return common;
	}

	/**
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * doing a binary search on group2.
	 * 
	 * @param group1 A group of Students
	 * @param group2 A group of Students
	 * @param numCommon number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents2(Student[] group1, Student[] group2,
			int numCommon) {
		Student[] common = new Student[numCommon];
		
		int i = 0; // counter to avoid ArraysIndexOutOfBounds
		Arrays.sort(group2);
		while (i < numCommon) {
			for (Student st: group1) {
				int studentFound = Arrays.binarySearch(group2, st);
				
				if (studentFound >= 0) {
					common[i++] = st;
				} else {
					continue;
				}		
			}
		} // Binary search. Gets one Student from group1.
		  // If this Student is equal to a Student in group2,
		  // assign this Student to array "common." Repeat until "common" full.
		
		return common;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		/***** These files provided to help you with initial testing *****/
//		Student[] uc = readStudentsFromFile("sample_uc_students.txt", 10);
//		Student[] smc = readStudentsFromFile("sample_smc_grads.txt", 5);
//		final int SMC_UC_GradsNumber = 2;
//
// Sample code from instructor
		
		/***** Use these files for the output you submit *****/
		Student[] uc = readStudentsFromFile("uc_students.txt", 350000);
		Student[] smc = readStudentsFromFile("smc_grads.txt", 75000);
		final int SMC_UC_GradsNumber = 25000;

		long start, end;

		start = System.currentTimeMillis();
		Student[] common1 = findCommonStudents1(uc, smc, SMC_UC_GradsNumber);
				
		end = System.currentTimeMillis();
		System.out.println("Cross checking took " + (end - start) / 1000.0
				+ " seconds.");
		Arrays.sort(common1);
		writeStudentsToFile(common1, "smc_grads_at_uc_1.txt");

		start = System.currentTimeMillis();
		Student[] common2 = findCommonStudents2(uc, smc, SMC_UC_GradsNumber);
		end = System.currentTimeMillis();
		System.out.println("Using binary search it took " + (end - start)
				/ 1000.0 + " seconds.");
		Arrays.sort(common2);
		for (Student temp : common2) {
			System.out.println(temp.toString());
		}
		writeStudentsToFile(common2, "smc_grads_at_uc_2.txt");
		
	}

}
