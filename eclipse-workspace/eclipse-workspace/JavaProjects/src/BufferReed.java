
import java.util.*;



public class BufferReed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner myscanner = new Scanner (System.in);
		 
		 String str  = "";
		
		 str = myscanner.nextLine();
		 
		 
		 if (str.charAt(4) < str.charAt(0)) {
			
			 System.out.print(str.charAt(2));
			 System.out.print(str.charAt(1));
			 System.out.print(str.charAt(0));
		 }   
		 else if(str.charAt(4) > str.charAt(0)) {
			 System.out.print(str.charAt(6));
			 System.out.print(str.charAt(5));
			 System.out.print(str.charAt(4));
		 }
	
	}

}
