import java.util.*;
public class Batterup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] array = new int[n];
		
		int hits = 0;
		int dec = 0;
		
		for (int i = 0; i<n ; i++ ) {
			
			array[i]=sc.nextInt();
			
			if(array[i]>0) {
				hits +=array[i];
			}
			
			if(array[i]<0) {
				dec++;
			}
		}		
		
		double result = (double) hits/(double)(n-dec);
		
		System.out.println(result);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
