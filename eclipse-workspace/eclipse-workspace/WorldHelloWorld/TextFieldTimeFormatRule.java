package ui.validation;

import java.time.LocalTime;

import javafx.scene.control.TextField;

/*
 * @author Bilen Yavuz
 * validates a TextField for LocalTime input
 */
public class TextFieldTimeFormatRule implements  ControlValidationRule<TextField>{

	@Override
	public boolean validate(TextField control) {
		try {
			LocalTime.parse(control.getText()); // try to parse
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
		
	}

	@Override
	public String getErrorMessage(String controlName) {
		return controlName + " is not a valid time with format HH:mm";
	}
	
	

}
