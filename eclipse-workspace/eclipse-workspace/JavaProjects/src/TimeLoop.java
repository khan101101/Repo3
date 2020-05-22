import java.util.Scanner;
public class TimeLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Problem: stuck in a time loop
		 * link: https://open.kattis.com/problems/timeloop
		 * Author:
		 * 
		 * Status: accepted
		 * runtime: 0.12s
		 */
		Scanner myscanner = new Scanner(System.in);
		
		int a = 1;
		System.out.println("");
		a = myscanner.nextInt();
		
		for(int i = 1; i<=a ; i++) {
			
			System.out.println(i + " Abracadabra");
			
			
		}
		
		
		
		
		
		
		
		
	}

}
