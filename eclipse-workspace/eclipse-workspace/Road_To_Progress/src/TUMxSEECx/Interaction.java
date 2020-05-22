package TUMxSEECx;

public class Interaction {

	
	  public Person initiator;

	    public Interaction(Person initiator) {
	        this.initiator = initiator;
	    }

	    public void printInteraction() {
	        System.out.println("Interaction by " + this.initiator.firstName);
	    }
}
