
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;
public class Grass_Seed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Problem:  Grass seed inc.
		 * link:https://open.kattis.com/problems/grassseed
		 * author:Jim Grimmett
		 * 
		 * 
		 * status: accepted
		 * runtime: 0.19s
		 * 
		 * 
		 */
		
		
		
		
		Locale.setDefault(Locale.ENGLISH);
		Scanner myscanner = new Scanner(System.in);
		
		double cost, l,b;
		int landscape;
		
		System.out.println("");
		cost = myscanner.nextDouble();
		landscape = myscanner.nextInt();
		
		double array[] = new double[landscape*3];
		
		int j = 0, a = 0;
		double array2[] = new double[landscape]; //for area
		while (j < landscape) {
			System.out.println(""); 
			array[j] = myscanner.nextDouble();
			
			array[j+1] = myscanner.nextDouble();
			
			double area = array[j] * array[j+1];
				   array2[a] = area;
				   a++;
				j++;
		}
		 /*calculating cost*/
		 double costArray[] = new double[landscape];
		 for(int z =0; z<costArray.length; z++) {
			 costArray[z] = array2[z] * cost;
		 }
		 
		 /* Costs Addition*/
		 double sum = 0;
		 for(int z =0; z<costArray.length; z++) {
			 sum = sum + costArray[z];
		 }
		 //System.out.println("costArray[] = " + Arrays.toString(costArray)); 
		 
		 
		 DecimalFormat f = new DecimalFormat("#0.0000000");
		 System.out.println(f.format (sum));
		}
		 		
		
	}

