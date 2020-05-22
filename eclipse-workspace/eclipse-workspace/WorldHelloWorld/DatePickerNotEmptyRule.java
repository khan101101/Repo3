package ui.validation;
import javafx.scene.control.DatePicker;

/*
 * @author Bilen Yavuz
 * validates a Date picker for non empty input
 */
public class DatePickerNotEmptyRule implements ControlValidationRule<DatePicker> {

	@Override
	public boolean validate(DatePicker control) {
		return control.getValue() != null;
	}

	@Override
	public String getErrorMessage(String controlName) {
		return controlName + " can not be empty";
	}

}
