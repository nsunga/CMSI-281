import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Experimental {
	
	public static void main(String[] args) {
		String[] test = new String[3];
		
		test[0] = "hello";
		test[0] = test[1];
		for(String put: test){
			System.out.println(put + ", ");
		}
		
		System.out.println(test.length);
	}
}