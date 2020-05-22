import java.util.*;
public class Engineering_English {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner my = new Scanner(System.in);
		String []s = new String[5];
		
		System.out.println("");
		 
		   String str ="";
		   System.out.print("");
		   str = my.nextLine();
		   String d = "";
		   for(int i = 0; i<str.length(); i++) {
			       if( str.charAt(i) != ' ')         
				   d += str.charAt(i);
				
		   }
		    d.toCharArray();
		   for(int i = 0; i<d.length(); i++) {
			   if(d.charAt(i) == d.charAt(i+1)) {
				   d.charAt(i) = '.' ;  
			    
			   }
			   
		   }
		  

		   
		}
	
	}
