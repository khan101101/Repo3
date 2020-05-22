import java.util.LinkedList;

public class Linkedlist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*This class shows some operations on a linkedlist in java */
		
		// create a new linked list
		LinkedList<Integer> lst = new LinkedList<Integer>();
		// add elements
		lst.add(15); // [15]
		lst.add(3);
		lst.add(5);
		lst.addFirst(13); // [13,15,19,21]
		lst.addLast(24); // [13,15,19,21,24]
	
		System.out.println(lst);
		lst.removeFirst();
		System.out.println(lst);
		lst.removeLast();
		System.out.println(lst);
		lst.remove(2); // 2 is the index.
		System.out.println(lst);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
