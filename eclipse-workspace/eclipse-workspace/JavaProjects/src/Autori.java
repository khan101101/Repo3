import java.util.*;
public class Autori {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Problem: Autori
	     link:https://open.kattis.com/problems/autori
	     Author:Marko Ivanković
		 Status: Accepted
		 Runtime: 0.12s
		 */
		
		Scanner myscanner = new Scanner (System.in);
	
		String input = myscanner.nextLine();//Ich lege hiermit meine eingegebene Dings in input String variable
		String[] inputArray = input.split("-");//
		
	
		
	
		for(int i = 0; i<inputArray.length; i++) 
			
	    System.out.print(inputArray[i].charAt(0));
		
	}
	
}