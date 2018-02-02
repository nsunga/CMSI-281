import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author  Nicholas Sunga
 *
 */

public class Project6 {
	
	/*
	 * ******************************** Part 1 ******************************
	 */
	/*
	 * DO NOT MODIFY THE NODE CLASS
	 */
	static class Node {
		Node(int data) {
			this.data = data;
		}

		int data;
		Node left;
		Node right;
	}
	
	/**
	 * Invert a tree from left-right
	 * 
	 * @param root - start position
	 */
	static void flip(Node root) {
		if (root == null) {
			return;
		}
		
		// swap left and right child
		Node temp = root.right;
		root.right = root.left;
		root.left = temp; 
		
		// depth first, does left side of tree then right
		flip(root.left);
		flip(root.right);
	}
	
	/*
	 * ******************************** Part 2 ******************************
	 */
	static class MinMaxQueue {
		
		PriorityQueue<String> minQueue = new PriorityQueue<String>();
		
		/**
		 * Push String val into the queue
		 * 
		 * @param val
		 * @return void
		 */
		void push(String val) {
			minQueue.add(val);
		}
		
		/**
		 * Get the min val in the queue
		 * 
		 * @return String - the min val
		 */
		String popMin() {
			return minQueue.poll();
		}

		/**
		 * Get the max val in the queue
		 * 
		 * @return String - the max val
		 */
		String popMax() {
			String[] keys = new String[minQueue.size()];
			
			try {
				keys = minQueue.toArray(keys);
			} catch (NullPointerException e){
				e.printStackTrace();
			}
			
			// make this a max priority queue
			PriorityQueue<String> maxQueue = new PriorityQueue<String>(11, Collections.reverseOrder());
			
			// add the elements in the minQueue to the maxQueue
			for (String addThis : keys) {
				maxQueue.add(addThis);
			}
			
			minQueue.remove(maxQueue.peek()); // need to keep track of size, so remove it in minQueue
			return maxQueue.poll();
		}

		/**
		 * Get the size of the queue
		 * 
		 * @return - int the size of queue
		 */
		int size() {
			return minQueue.size();
		}
	}
	
	/*
	 * ******************************** Part 3 ******************************
	 */
	// experiment with this size to see how it affects efficiency
	private final int TABLE_SIZE = 21061; // alpha < 5 O(1)?, alpha = 4.7ish
	private List<String>[] table = new LinkedList[TABLE_SIZE];

	/**
	 * Constructs a word matcher based on the given dictionary.
	 * 
	 * @param filename The dictionary file name
	 */
	public Project6(String filename) {
		

		int hash = 0;
		
		for (int i = 0; i < TABLE_SIZE; i++) {
			table[i] = new LinkedList();
		} // create buckets
		
		try (Scanner scan = new Scanner(new File(filename)) ) {
			while (scan.hasNext()) {
				String fullLine = scan.nextLine();
				fullLine = fullLine.toLowerCase().trim();
				
				hash = myHashFunction(fullLine);
				table[hash].add(fullLine); // add the words to its bucket in the table.
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Purpose - sort the string and get the hashcode. Therefore, all anagrams hash to 
	 * the same value. Still, collisions of non-anagrams will be unavoidable.
	 * ie 'dog' and 'matthew' could potentially collide
	 *  
	 * @param str - to be hashed
	 * @return int - hash index
	 */
	public int myHashFunction(String str) {
		int hash = 0;
		
		char[] letters = str.toCharArray();
		Arrays.sort(letters); // nlogn but lengths are short. hardly affects performance
		String convertedString = new String(letters);
	
		
		hash = convertedString.hashCode() % TABLE_SIZE;
		if (hash < 0) {
			hash += TABLE_SIZE;
		}
		
		return hash;
		// got help regarding collisions but the code is 100% mine,
		// http://stackoverflow.com/questions/37663786/incorrect-method-of-making-same-hashcode-for-anograms
	}
	
	/**
	 * Return a list of dictionary words that have the same letters as the given word.
	 * Differences in letter cases are ignored.
	 * 
	 * @param word The word to find matches for. May or may not be in the dictionary.
	 * 
	 * @return The list of matching words from the dictionary, all in lower case.
	 * 		   The word itself is not included in the returned list.
	 * 		   e.g.: 	NAME -> [amen, mane, mean]
	 */
	public List<String> getMatches(String word) {
		List<String> validAnagrams = new LinkedList<String>();
		
		String lowerCaseWord = word.toLowerCase();
		int hash = myHashFunction(lowerCaseWord); // change to lower case, compute hash
		
		char[] sortedWordArr = lowerCaseWord.toCharArray();
		Arrays.sort(sortedWordArr);
		String sortedWord = new String(sortedWordArr); // sort the word. needed for comparisons
		
		if (table[hash].contains(lowerCaseWord)) { // check if word is in word.txt. All are converted to lowercase
		
			for(String temp : table[hash]) {
				char[] sortedTempArr = temp.toCharArray();
				Arrays.sort(sortedTempArr);
				String sortedTemp = new String(sortedTempArr);
				
				if (sortedWord.equals(sortedTemp) && !(temp.equals(lowerCaseWord)) ) { // add anagrams not including 'word'
					validAnagrams.add(temp);
				}
			}
			
		}
		
		return validAnagrams; // is size 0 if word not in word.txt
	}
	
	public static void main(String[] args) {					
		/*
		 *    You can test your methods here but I will not grade the main
		 *    
		 *    Some part 3 tests below in case you find them useful.
		 */
		
		/*Project6 matcher = new Project6("words.txt");

		String test = "act";
		String[] words = { "act", "hug", "cafe", "node", "canoe", "dusty",
				"friend", "silent", "meteor", "markers", "aStewSir",
				"dirtyRoom", "stampStore", "moonStarers", "theClassroom",
				"accordNotInIt" };
		for (String word : words) {
			System.out.println(word + " -> " + matcher.getMatches(word));
		}

		// Stress the application to ensure it runs efficiently under load.
		// All runs below should complete practically in an instant.
		final int RUNS = 100000;
		for (int i = 0; i <= RUNS; i++) {
			matcher.getMatches("noMoreStars");
			if (i % 1000 == 0) {
				System.out.print(".");
			}
		}
		
		System.out.println();
		System.out.println("NAME" + " -> "
				+ matcher.getMatches("NAME"));
		
		System.out.println();
		System.out.println("POTS" + " -> "
				+ matcher.getMatches("pots"));*/
	}
}