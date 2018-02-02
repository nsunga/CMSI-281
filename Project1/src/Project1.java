/* Created by: NICHOLAS SUNGA
 * COURSE #4116
 * February 18, 2016
 *
 * Purpose: Driver code that maps all 26 letters to a different one.
 * The encryption is determined by the user, and is asked to choose a .txt file to encrypt. 
 *
 * Input: user inputs the encryption. i.e. "sjwkvputqhoenzlyrabxigmdcf" for 
 * "abcdefghijklmnopqrstuvwxyz." Then, the user chooses a .txt file to encrypt.
 *
 * Output: print statements of the .txt file in encrypted form.
 */

import java.util.*;
import java.io.*;

public class Project1 {

	/* Purpose: Implements the created methods to check and output the .txt file in encrypted form.
	 *
	 * Parameters: String[] args
	 *
	 * Return: none
	 *
	 * Input: user inputs the encryption. i.e. "sjwkvputqhoenzlyrabxigmdcf" for 
 	 * "abcdefghijklmnopqrstuvwxyz."
	 *
	 * Output: Print statements that correspond to the encrypted key and the print statements
	 * from the implemented methods.
	 */
	public static void main(String[] args) throws Exception{
		boolean condition = false; // if false, encrypted key is invalid
		Scanner input = new Scanner(System.in);
		String encode, alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		do{
			System.out.print("Enter a valid key: ");
			encode = input.nextLine();
			
			condition = checkEncode(encode, alphabet);
			
			if (condition == false){
				System.out.println("Key is not valid");
			}
		} while (condition == false); // ensures that the encrypted key is valid
				
		outputFileText(encode, alphabet, input);
		System.out.println("\nGood Bye!");
	}
	
	/* Purpose: method checks the validity of the encrypted key chosen by the user.
	 *
	 * Parameters: two Strings - the encrypted key, and an alphabetical one.
	 *
	 * Return: boolean - if false, then the key is invalid. if true, then the key is valid.
	 * 
	 * Input: no input
	 *
	 * Output: no output
	 */
	public static boolean checkEncode(String encode, String alphabet){
		boolean[] determinant = new boolean[26]; 
		// contents all false. if all true, then all letters were used.
		
		if ( (encode.equals(alphabet)) || (encode.length() != 26) ){
			return false;
		} // ensures not in alphabetical order, and is 26 characters
		
		for (int i = 0; i < encode.length(); i++){
			if ( (encode.charAt(i) < 97) || (encode.charAt(i) > 122) ){
				return false;
			} else {
				determinant[encode.charAt(i) - 'a'] = true;
			}	
		} // check for no special characters i.e. '5' or '!'
		
		for (int i = 0; i < determinant.length; i++){
			if (determinant[i] == false){
				return false;
			}
		} // ensures all letters were used, no duplicates
		
		return true;
	}
	
	/* Purpose: encrypts the .txt file chosen by the user and outputs the encryption onto
	 * the terminal screen.
	 *
	 * Parameters: two strings - the encrypted key and the alphabet.
	 * Scanner - used for user input. i.e. Strings
	 *
	 * Return: none
	 * 
	 * Input: the .txt file to be encoded
	 *
	 * Output: Asks the user for a .txt file and outputs the file in encrypted form.
	 */
	public static void outputFileText(String encode, String alphabet, Scanner a) throws Exception{
		System.out.print("Enter the .txt file you want to encode: ");
        
    	String infileName = a.nextLine();			
    	File infile = new File(infileName);
        Scanner input = new Scanner(infile);
        
        System.out.println("\n************** Encoded Contents ****************");
        while (input.hasNext()){
        	String line = input.nextLine();
        	char [] lineToArray = line.toCharArray();
        	
        	for (int j = 0; j < lineToArray.length; j++){
        		for (int i = 0; i < 26; i++){
        			if ( lineToArray[j] == alphabet.charAt(i) ){
                        lineToArray[j] = encode.charAt(i);
                        break;
                    } else if ( lineToArray[j] == (alphabet.charAt(i) - 32) ){
                    	lineToArray[j] = (char)(encode.charAt(i) - 32);
                    	break;
                    }
        		}
        		System.out.print(lineToArray[j]);
        	} // maps upper and lower letters of the .txt to the encryption by line.
        	
        	System.out.println(); // line break
        }
        System.out.println("************************************************");
	}
}