import java.util.Arrays;
import java.util.ArrayList;
public class ToStringMethodInJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  
		/*This class contains methods which could be used on arrays in java*/
		
		
		/* Return Value
     toString()  This returns a String object representing the value of this Integer.

     toString(integer i)  This returns a String object representing the specified integer.
      */        
		      Integer x = 5;

		      System.out.println(x.toString());  
		      System.out.println(Integer.toString(12)); 
		      
		      int[] b = new int[10];
		      System.out.println(Arrays.toString(b));
			  
		      int[] c = new int[2];
		      int[] d = new int[2];
		      
		      /*The method Arrays.equal(a,b) gives back a true if both arrays a and b have same values in the same order*/
		      for(int i = 0; i< c.length; i++)
		    	  c[i] = i;
		      for(int i =0; i<d.length; i++)
		    	  d[i] = i;
		     boolean e = Arrays.equals(c, d) ;
		     System.out.println(e);
		     System.out.println("c[] = " + Arrays.toString(c));
		     System.out.println("d[] = " + Arrays.toString(d));
		     
		     /*have to think about it*/
		     System.out.println("what is this " + Arrays.copyOf(c, 5));
		     
		     /*Search a sorted array for a given value*/
		     int[] h = new int[10];
		   
		     int j = 3;
		     int i = 0;
		     while( i< h.length) {
		    	h[i] = j;
		    	j++;
		     i++;
		     }
		     
		     System.out.println("h[] = " + Arrays.toString(h));
		 
		     System.out.println(Arrays.binarySearch(h, 8));//returns the index where the value is found , return -1 if it is not found
		     
		     
		     /*if you forget to use the toString() method and instead pass the array directly*/
		     System.out.println("This will show the address of the array c[] = " + c);
		    
		     /*Arraylist */
		     ArrayList<Integer> name = new ArrayList<Integer>();
		     name.add(0, 8);
		    
		     int variable = name.get(0);
		     System.out.println(variable);
		     name.set(0, 101);
		     /*update the element at index 0 and get the index value again*/
		     int variable1 = name.get(0);
		     System.out.println(variable1);
		     System.out.println("My ArrayList[] = " +name);
		     
		     
		     
		     /*Ab hier neue beispiel*/
		     
		     ArrayList<Integer> arraylist = new ArrayList<Integer>();
		     arraylist.add(1);
		     arraylist.add(2);
		     arraylist.add(3);
		     arraylist.add(4);
		     arraylist.add(5);
		     arraylist.add(6);
		     arraylist.add(7);
		     System.out.println("ArrayList before update: "+arraylist);
		     //Updating 1st element
		     arraylist.set(0, 11);
		     //Updating 2nd element
		     arraylist.set(1, 22);
		     //Updating 3rd element
		     arraylist.set(2, 33);
		     //Updating 4th element
		     arraylist.set(3, 44);
		     //Updating 5th element
		     arraylist.set(4, 55);
		     System.out.println("ArrayList after Update: "+arraylist);
		     System.out.println(arraylist.get(2)); //Yahoooo!
	}

}
