/** 
 * Created by: NICHOLAS SUNGA
 * COURSE #4116
 * May 21, 2016
 *
 * Purpose: My implementation of Project 5.
 * Tests understanding of .compare() in Comparator interface and traversals of trees.
 *
 * Input: none
 *
 * Output: none
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Comparator;

public class Project5 {

	/**
	 * Read students from file into a list
	 * 
	 * @param filename
	 * @return the list of students
	 */
	static ArrayList<Student> readFromFile(String filename) {
		ArrayList<Student> students = new ArrayList<Student>();
		try (Scanner in = new Scanner(new File(filename))) {
			while (in.hasNextLine()) {
				String[] fields = in.nextLine().split(",");
				students.add(new Student(Integer.parseInt(fields[1]), fields[0], fields[2]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return students;
	}

	/*
	 * ******************************** Part 1 ******************************
	 */
	
	/**
	 * Sorts the students list by school. Same school students are sorted by ID.
	 * 
	 * Performs two stable sorts with different criteria. One sort and then another sort.
	 * 
	 * @param students
	 */
	static void sortBySchoolById1(ArrayList<Student> students) {
		// Sort by school
		Collections.sort(students, new Comparator<Student>() {
			/**
			 * Compare two students by school.
			 * 
			 * @param two Students
			 * 
			 * @return 0 int if argument is equal to callee. Negative if argument is greater
			 * than callee. Positive if argument is less than callee.
			 */
			@Override
			public int compare(Student s1, Student s2) {
				String s1School = s1.getSchool();
				String s2School = s2.getSchool();
				
				return s1School.compareTo(s2School);
			}
		});
		
		// sort by ID
		Collections.sort(students, new Comparator<Student>() {
			/**
			 * If two students attend the same school, their ID's are compared.
			 * 
			 * @param two Students
			 * 
			 * @return 0 int if argument is equal to callee. Negative if argument is greater
			 * than callee. Positive if argument is less than callee.
			 */
			@Override
			public int compare(Student s1, Student s2) {
				String s1School = s1.getSchool();
				String s2School = s2.getSchool();
				int swapBySchool = s1School.compareTo(s2School);
				int s1ID = s1.getId();
				int s2ID = s2.getId();
				
				if ( (swapBySchool == 0) ) {
					return (Integer.compare(s1ID, s2ID));
				} else {
					return 0;
				} // if same school, compare by ID. if not, don't swap them
			}
		});
	}

	/**
	 * Sorts the students list by school. Same school students are sorted by ID.
	 * 
	 * Performs only one sort that uses a comparator that takes both criteria
	 * into account.
	 * 
	 * @param students
	 */
	static void sortBySchoolById2(ArrayList<Student> students) {
		Collections.sort(students, new Comparator<Student>() {
			/**
			 * Compare two students by school. If they attend the same school,
			 * compare them by ID. Sorts 'students' in one sort.
			 * 
			 * @param two Students
			 *
			 * @return 0 int if argument is equal to callee. Negative if argument is greater
			 * than callee. Positive if argument is less than callee.
			 */
			@Override
			public int compare(Student s1, Student s2) {
				String s1School = s1.getSchool();
				String s2School = s2.getSchool();
				int swapBySchool = s1School.compareTo(s2School);
				int s1ID = s1.getId();
				int s2ID = s2.getId();
				
				if (swapBySchool == 0) {
					return Integer.compare(s1ID, s2ID);
				} else {
					return swapBySchool;
				} // swap by school. If same, then swap by ID
			}
		});
	}

	
	/*
	 * ******************************** Part 2 ******************************
	 */
	/**
	 * Return all the first names which occur at least a given number of times
	 * 
	 * @param students
	 * @param threshold
	 * @return A list of first names (with no duplicates) occurring <threshold>
	 *         times or more in the student list
	 */
	static List<String> commonFirstNames(ArrayList<Student> students,
			int threshold) {
		ArrayList<String> firstNamesList = new ArrayList<String>();
		
		// sort students by Name
		Collections.sort(students, new Comparator<Student>() {
			/**
			 * Compare two Students by name
			 * 
			 * @param two Students
			 * 
			 * @return 0 int if argument is equal to callee. Negative if argument is greater
			 * than callee. Positive if argument is less than callee.
			 */
			@Override
			public int compare(Student s1, Student s2) {
				String s1Name = s1.getName();
				String s2Name = s2.getName();
				
				return s1Name.compareTo(s2Name);
			}
		});
		
		String fullName;
		String pivotFirstName;
		String otherPivotFirstName;
		int spaceIndex;
		int occurrences = 1;
		int nextInList = 0;
		
		fullName = students.get(nextInList).getName();
		spaceIndex = fullName.indexOf(' ');
		pivotFirstName = fullName.substring(0, spaceIndex); // get the firstname only
		
		for (int i = 0; i < students.size(); i++) {
			if ( (nextInList + 1) == students.size() ) {
				break; // ensure no ArrayIndexOutOfBounds
			}
			
			if (occurrences == (threshold)) {
				if (!firstNamesList.contains(pivotFirstName) ) {
					firstNamesList.add(pivotFirstName); // add new student when threshold is met
				} else {
					;
				}
			}
			
			fullName = students.get(nextInList + 1).getName();
			spaceIndex = fullName.indexOf(' ');
			otherPivotFirstName = fullName.substring(0, spaceIndex); // get firstname only
			
			if (pivotFirstName.compareTo(otherPivotFirstName) == 0) {
				occurrences ++;
			} else {
				pivotFirstName = otherPivotFirstName; // not a match
				occurrences = 1; // restart occurrences
			}
			
			nextInList++; // compare with next student
		}
		
		// list is sorted
		return firstNamesList;
	}
	
	/*
	 * ******************************** Part 3 ******************************
	 */
	/**
	 * Return all the first names which occur at least a given number of times
	 * 
	 * @param students
	 * @param threshold
	 * @return A list of first names (with no duplicates) occurring <threshold>
	 *         times or more in the student list
	 */
	static List<String> commonFirstNames2(ArrayList<Student> students,
			int threshold) {
		
		TreeMap<String, Integer> firstNamesMap = new TreeMap<String, Integer>();
		ArrayList<String> firstNamesList = new ArrayList<String>();
		String fullName;
		String key;
		
		int spaceIndex;
		int mappedValue;
		
		for (int i = 0; i < students.size(); i++) {
			 fullName = students.get(i).getName();
			 spaceIndex = fullName.indexOf(' ');
			 key = fullName.substring(0, spaceIndex); // get the firstname
			
			if ( !(firstNamesMap.containsKey(key)) ) {
				firstNamesMap.put(key, 1); // initially not in map, so add it
			} else {
				mappedValue = firstNamesMap.get(key).intValue();
				mappedValue++;
				firstNamesMap.put(key, mappedValue);
				// increment the occurrence of the key
				// 250-252 similar code to: https://www.youtube.com/watch?v=m7s6ulOJOAM
				
				if (mappedValue == threshold) {
					firstNamesList.add(key);
				}
			}
		}
		
		// list is unsorted
		return firstNamesList;
	}

	public static void main(String[] args) {
		
		//ArrayList<Student> students = readFromFile("students.txt");
		//sortBySchoolById1(students);
		//sortBySchoolById2(students);
		
		//for (Student printThis : students) {
		//	System.out.println(printThis);
		//}
		
		//String outFile = new String("test.txt");
		//try (FileWriter out = new FileWriter(outFile)) {
			//for (Student test: students) {
				//out.write(test.toString() + "\n");
			//}  //Write down every Student in the array to the file
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
		//ArrayList<String> myList = (ArrayList<String>)commonFirstNames2(students, 146);
		
		//Collections.sort(myList);
		//for(String printThis : myList) {
			//System.out.println(printThis);
		//}
	}
}