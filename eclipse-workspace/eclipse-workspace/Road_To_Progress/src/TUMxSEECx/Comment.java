package TUMxSEECx;

public class Comment extends Interaction {

	
    public String text;

    public Comment(Person initiator, String text) {
        super(initiator);
        this.text = text;
    }
}
