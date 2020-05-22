
import java.util.*;

public class HappyTrails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		double hc = 0;
		int[] array = new int[n*2];
		
		//int alpha = sc.nextInt();
		//int b = sc.nextInt();		
		//hc = b*Math.sin(Math.toRadians(alpha));	

		
		for (int i = 0; i<n*2;i++) {
			
			array[i] = sc.nextInt();			
			
		}	
		
		for (int i = 0; i<(n*2)-1;i+=2) {
		
			hc += array[i+1]*Math.sin(Math.toRadians(array[i]));
		
		}
		
		System.out.printf("%.2f", hc);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
