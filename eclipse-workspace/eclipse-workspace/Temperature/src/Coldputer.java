import java.util.Scanner;

public class Coldputer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * problem:Cold-Puter Science
		 * link:https://open.kattis.com/problems/cold
		 * author: 
		 * status: accepted
		 * runtime: 0.13s
		 * 
		 * 
		 * 
		 */
		int a,anz=0;
		int array[] = new int[1000];
		
		Scanner myscanner = new Scanner(System.in);
		System.out.println("");
		
		a = myscanner.nextInt();
		
		System.out.print("");
		for(int i = 0; i<a ; i++) {
			
			array[i] = myscanner.nextInt();
		}
	
		for(int i = 0; i<a ; i++) {
			if(array[i] < 0)
				anz = anz + 1;	
		}
		
		System.out.println(anz);

	}

}
