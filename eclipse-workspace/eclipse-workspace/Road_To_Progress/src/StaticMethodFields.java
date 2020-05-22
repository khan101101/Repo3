
public class StaticMethodFields {

	public int a;
	
	
	public int mul(int a) {
		return this.a * a;
	}
	
	public static int mult(int b) {
	 return b;
	}
	
	public String toString() {
		return "String " + a;
	}
	
}
