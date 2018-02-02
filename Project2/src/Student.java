import java.util.*;
import java.lang.*;

/* Created by: NICHOLAS SUNGA
 * COURSE #4116
 * March 10, 2016
 *
 * Purpose: Source code for Student class. Implemented in "Project2.java"
 *
 * Input: none
 *
 * Output: none
 */

public class Student implements Comparable<Student> {

	private int id;
	private String name;
	private String school;
	
	/* Purpose: Student default constructor
	 * 
	 * Parameters: none
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	public Student() {
		
	}
	
	/* Purpose: Student constructor to initialize member variables
	 * 
	 * Parameters: int - ID Number, two strings - name of student, name of school
	 *
	 * Return: none
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	public Student(String name, int id, String school) {
		this.name = name;
		this.id = id;
		this.school = school;
	}
	
	/* Purpose: checks equality of the ID of one student w/ another
	 * 
	 * Parameters: Any object
	 *
	 * Return: boolean - true if both IDs are the same, false if not
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			Student other = (Student)obj;
			return this.id == other.id;
		} else {
			return false;
		}
	}
	
	/* Purpose: compares two student IDs. Needed to sort. ie: Arrays.sort(*array of Students*)
	 * 
	 * Parameters: Student
	 *
	 * Return: int - 0 if equal, -1 if calling Student's ID is less than the argument's ID,
	 * 1 if calling Student's ID is greater than the argument. 
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	public int compareTo(Student st) {
		return Integer.compare(this.id, st.id); 
	}

	/* Purpose: A String representation of a Student object
	 * 
	 * Parameters: none
	 *
	 * Return: String - the Name, ID Number, and School name of a student.
	 *
	 * Input: none
	 *
	 * Output: none
	 */
	@Override
	public String toString() {
		return this.name + ", " + this.id + ", " + this.school;
	}
}