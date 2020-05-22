import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
public class Epot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner myscanner = new Scanner(System.in);
		int []store = new int[3];
		System.out.print("");
		for(int i = 0; i< store.length; i++) {
		
			store[i] =  myscanner.nextInt();
		}
		 System.out.println("store[] = " + Arrays.toString(store)); // print the array
		 
		 
		int[] restArray = new int[3];
		for(int i = 0; i< store.length; i++) {
			restArray[i] = store[i] % 10;
			
		}
		System.out.println("restArray[] = " + Arrays.toString(restArray));

		
		int summe = 0;
		int[] newstore = new int[3];
		
		for(int i = 0; i< store.length; i++) {
			for(int j=0; j<restArray.length; j++) {
				for(int h = 0; h< newstore.length; h++)
					
				//newstore[h]  = (int) Math.pow (store[i]  , restArray[j]) ;
				newstore[h]  = (store[i] ) ^ restArray[j] ;
				
			}
			
		}
		System.out.println("Problem Array[] = " + Arrays.toString(newstore));
	
		
			System.out.println("The Number is : " + summe);
			
		
		
		
		
		
	}

}
