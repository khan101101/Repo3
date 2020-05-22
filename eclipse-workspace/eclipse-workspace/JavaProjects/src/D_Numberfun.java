import java.util.*;
public class D_Numberfun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
  Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] array = new int[N*3];
		
		for(int i = 0 ; i<N*3 ; i++) {
			
			array[i]= sc.nextInt();
		}
		
		for(int i = 0 ; i<N*3-1; i+=3){
			
		
			if(array[i+2]==array[i]+array[i+1]) {
				System.out.println("Possible");
//				System.out.println("+ " + array[i]+array[i+1]);
			
			} else if (array[i+2]==array[i]*array[i+1]) {
				System.out.println("Possible");
//				System.out.println("* " + array[i]*array[i+1]);
		
			} else if(array[i+2]==array[i]-array[i+1]) {
				System.out.println("Possible");
//				System.out.print("m1 ");
//				System.out.println(array[i]-array[i+1]);
		
			} else if(array[i+2]==array[i+1]-array[i]) {
				System.out.println("Possible");
//				System.out.print("m2 ");
//				System.out.println(array[i+1]-array[i]);
		
			} else if((array[i+2]==array[i]/array[i+1])&(array[i]%array[i+1]==0)) {
				System.out.println("Possible");
//				System.out.println("/1" + array[i]/array[i+1]);
	
			} else if((array[i+2]==array[i+1]/array[i])&(array[i+1]%array[i]==0)) {
				System.out.println("Possible");
//				System.out.println("/2" + array[i+1]/array[i]);
	
			} else {
				System.out.println("Impossible");
			}			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
