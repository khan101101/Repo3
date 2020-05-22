package ui.validation;

import javafx.scene.control.TextField;
/*
 * @author Bilen Yavuz
 * validates a TextField for non empty input
 */
public class TextFieldNotEmptyRule implements  ControlValidationRule<TextField> {

	@Override
	public boolean validate(TextField control) {
		return control.getText() != null && !control.getText().isEmpty();
	}

	@Override
	public String getErrorMessage(String controlName) {
		return controlName + " can not be empty";
	}
	
	

}
