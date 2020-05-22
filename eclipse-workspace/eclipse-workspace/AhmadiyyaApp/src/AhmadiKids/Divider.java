package AhmadiKids;

public class Divider {

	public int Divide(int a, int b) throws Exception {
		try {
		return a / b;
		}
		catch(ArithmeticException e) {
			throw new Exception("Some errors");
		}
	}
}
