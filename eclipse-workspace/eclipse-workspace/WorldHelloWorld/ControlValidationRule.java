package ui.validation;


/*
 * @author Bilen Yavuz
 * a rule to validate control
 */
public interface ControlValidationRule<C extends javafx.scene.control.Control> {
	public boolean validate(C control);
	public String getErrorMessage(String controlName);
	
	
}
