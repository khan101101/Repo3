
package inheritance.examples;

public class print {
	
	public print(String student_name) {
		this.student_name = student_name;
	}
	
	

	String student_name;
	
	public void printIt() {
		System.out.print("Hello World " + this.student_name);
	}
	

}
