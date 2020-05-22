import java.util.ArrayList;
import java.util.*;
import java.util.*;
public class KitenOnTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

Scanner sc = new Scanner(System.in);
		
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		
		while(sc.hasNext()) {			
			
			String readLine = sc.nextLine();
			String[] stringArray = readLine.trim().split("\\s+");    
		    
			ArrayList<Integer> temp = new ArrayList<Integer>();	

		    for (int i = 0; i < stringArray.length; i++) {
		    	temp.add(i, Integer.valueOf(stringArray[i]));
		    }
		    
		    if(temp.get(0)==-1) break;
		    
		    array.add(temp);
		    
		}
		
		int temp = array.get(0).get(0);
		System.out.print(temp);		
		
		for(int i = 1 ; i < array.size() ; i++) {			
			
			for(int j = 1 ; j < array.get(i).size(); j++) {
				
				if(temp == array.get(i).get(j)) {
					temp = array.get(i).get(0);
					System.out.print(" " + temp);
					i = 0;					
				}				
			}			
		}		
	}
	
	public static void printInfo(ArrayList<ArrayList<Integer>> array) {
		
		for(int i=0;i<array.size();i++) {	
		System.out.println("row head: " + array.get(i).get(0) +
							" row size: " + array.get(i).size());
		}		
	}
	
	
	public static void tryArrayList() {
		
		Scanner sc = new Scanner(System.in);
		
		int h = 0;
		ArrayList<Integer> intHeader = new ArrayList<Integer>();
		
		while(sc.hasNext()) {			
			
			String readLine = sc.nextLine();
			//System.out.println(readLine);			
			
			//remove any leading, trailing white spaces and split the string from rest of the white spaces
		    String[] stringArray = readLine.trim().split("\\s+");
		    
		    
		    intHeader.add(h, Integer.valueOf(stringArray[0]));
		    h++;
		    
		    
		    ArrayList<Integer> intArrayList = new ArrayList<Integer>();	
		    //parse the integer value and store it in the int array
		    for (int i = 0; i < stringArray.length; i++) {
		        intArrayList.add(i, Integer.valueOf(stringArray[i]));
		    }
		    

//		    for (int i = 0; i < intArrayList.size(); i++) {
//		        System.out.println(intArrayList.get(i));
//		    }
		    
		    if(intArrayList.get(0)==-1) break;
	
		}
		
		for (int i = 0; i<intHeader.size();i++) {
			System.out.println(intHeader.get(i));
			
		}
		
		
	}
	
	
	
	
	public static void staticArray() {
		
		Scanner sc = new Scanner(System.in);		
	
		int[][] intArray = new int[50][101];		
		//initialize intArray		
		for (int i = 0; i < 7 ; i++) {			
			for (int j = 0; j < 7; j++) {				
				intArray[i][j]=0;				
			}			
		}
		
		
		for(int i = 0; i < 50 ; i++) {
			
			String readLine = sc.nextLine();
			//System.out.println(readLine);			
			
			//remove any leading, trailing white spaces and split the string from rest of the white spaces
		    String[] stringArray = readLine.trim().split("\\s+");
			
		     //parse the integer value and store it in the int array
		    for (int j = 0; j < stringArray.length; j++) {
		        intArray[i][j] = Integer.parseInt(stringArray[j]);
		    }
		    
		    if(intArray[i][0]==-1) break;			
		}
		
		//print array
		for (int i = 0; i < 7; i++) {
				
			for(int j = 0; j < 7; j++) {
				
				System.out.print(intArray[i][j]);
			}
			System.out.println();
		}
		
		int[] result = new int[50];
		int K = intArray[0][0];
		result[0]=K;
		
		int r = 1;
			
		for(int i = 0; i<50 ; i++) {
			
			for(int j = 1; j < 101 ; j++) {
				
				if(intArray[i][j]==K) {
					K=intArray[i][0];
					result[r]=K;
					r++;
					i = 0;
				}
			}					
		}		
		
		for (int i = 0; i < 101; i++) {
			
			if(result[i]==0) break;
			
			if (result[i+1]==0) {
				System.out.print(result[i]);
			} else {
				System.out.print(result[i] + " ");
			}
			
		}
		
		sc.close();
	    
	}

}