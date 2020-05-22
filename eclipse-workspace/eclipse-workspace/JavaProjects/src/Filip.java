import java.util.*;
public class Filip {

	public static void main(String[] args) {
		/*
	
		 * problem: Filip
		 * link:https://open.kattis.com/problems/filip
		 * Author:Filip Barl
		 * status: accepted
		 * runtime: 0.12s
		 *
		 */
		Scanner myscanner = new Scanner (System.in);
		 
		 String str  = "";
		
		 str = myscanner.nextLine();
		 
		 
		 if (str.charAt(6) < str.charAt(2)) {
			
			 System.out.print(str.charAt(2));
			 System.out.print(str.charAt(1));
			 System.out.print(str.charAt(0));
		 }   
		 else if(str.charAt(6) > str.charAt(2)) {
			 System.out.print(str.charAt(6));
			 System.out.print(str.charAt(5));
			 System.out.print(str.charAt(4));
		 }
		 else if( str.charAt(6) == str.charAt(2) &&  str.charAt(5) > str.charAt(1)  ) {
			 System.out.print(str.charAt(6));
			 System.out.print(str.charAt(5));
			 System.out.print(str.charAt(4));
		 }
		 
		 
		 else if( str.charAt(6) == str.charAt(2) && str.charAt(5) == str.charAt(1) &&  str.charAt(4) > str.charAt(0)  ) {
			 System.out.print(str.charAt(6));
			 System.out.print(str.charAt(5));
			 System.out.print(str.charAt(4));
		 }
		 
		 
		 else if( str.charAt(6) == str.charAt(2) &&  str.charAt(5) < str.charAt(1)  ) {
			 System.out.print(str.charAt(2));
			 System.out.print(str.charAt(1));
			 System.out.print(str.charAt(0));
		 }
		 
		 
		 else if( str.charAt(6) == str.charAt(2) &&  str.charAt(5) == str.charAt(1) && str.charAt(4) < str.charAt(0) ) {
			 System.out.print(str.charAt(2));
			 System.out.print(str.charAt(1));
			 System.out.print(str.charAt(0));
		 }
		 
		 
		 
		 
		 
		 
	
	}
 
	}


