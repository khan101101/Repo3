import java.util.*;
public class OneChicken {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

       Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();    
		int C = B-A;
		int D = A-B;
		
		
		if(C>1) {
			System.out.println("Dr. Chaz will have " + 
								C + " pieces of chicken left over!");
		}
		
		if(C==1) {
			System.out.println("Dr. Chaz will have " + 
								C + " piece of chicken left over!");
		}
		
		if(D>1) {
			System.out.println("Dr. Chaz needs " + 
								D + " more pieces of chicken!");
		}
		
		if(D==1 ) {
			System.out.println("Dr. Chaz needs " + 
								D + " more piece of chicken!");
		}
		

	}

}
