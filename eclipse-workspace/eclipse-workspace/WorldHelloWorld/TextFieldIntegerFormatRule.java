package ui.validation;



import javafx.scene.control.TextField;
/*
 * @author Bilen Yavuz
 * validates a TextField for integer input
 */
public class TextFieldIntegerFormatRule implements  ControlValidationRule<TextField> {
	Integer minValue, maxValue;
	
	public TextFieldIntegerFormatRule(Integer min, Integer max) {
		minValue = min;
		maxValue = max;
	}
	@Override
	public boolean validate(TextField control) {
		try {
			
			Integer val = Integer.parseInt(control.getText()); // try to parse value as integer
			if(val >= minValue && val <= maxValue)// check minimum and maximum values
				return true;
		}
		catch(Exception exc)
		{
			
		}
		
		return false;
		
	}

	@Override
	public String getErrorMessage(String controlName) {
		return controlName + " must be a valid number between " + minValue.toString() + " and " + maxValue.toString();
	}
}
