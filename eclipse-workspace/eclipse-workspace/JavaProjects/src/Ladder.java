import java.util.Scanner;
import java.lang.Math;
public class Ladder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * problem: Ladder
		 * link:https://open.kattis.com/problems/ladder
		 * author:Per Austrin
		 * 
		 * status: accepted
		 * runtime: 0.12s
		 * 
		 * 
		 * 
		 */
		Scanner myscanner = new Scanner(System.in);
		double h, v;
		
		System.out.print("");
		h = myscanner.nextInt();
		v = myscanner.nextInt();
		
		v = Math.sin(Math.PI*v/180);
		double result = h / v;
		//result = Math.ceil(result);
		System.out.println((int)Math.ceil(result));
		
	
		
	}

}
