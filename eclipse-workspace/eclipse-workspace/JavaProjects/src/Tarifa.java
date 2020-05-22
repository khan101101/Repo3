import java.util.*;
public class Tarifa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Problem: Tariffa
		 * link: https://open.kattis.com/problems/tarifa
		 * Author:Nikola Dmitrović 
		 * 
		 * status : accepted
		 * runtime: 0.12s
		 * 
		 * 
		 */
		
		
		Scanner myscanner  = new Scanner(System.in);
		
		int X,N;
		
		System.out.println("");
		X = myscanner.nextInt();
		N = myscanner.nextInt();
		int []array = new int[N];
		
		System.out.println("");
		for(int i = 0; i<array.length; i++) {
			array[i] = myscanner.nextInt();
		}
		
		
		int []array2 = new int[N];
		for(int j = 0; j<array.length; j++) {
			array2[j] = X- array[j]; 
		}
		
		int sum = 0;
		int [] array3 = new int[N];
		for(int j = 0; j<array3.length; j++) {
			sum = sum + array2[j] ;
		}
		
		System.out.println(X+sum);
		
		
		
		
		
		
		
		
		
		
	}

}
