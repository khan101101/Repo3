package ui.validation;

import java.util.ArrayList;

/*
 * @author Bilen Yavuz
 * validates a control against  to list of validation rules
 * @see ui.validation.IControlValidation
 */
public class ControlValidation<C extends javafx.scene.control.Control> implements IControlValidation {
	
	private ArrayList<ControlValidationRule<C>> rules = new ArrayList<ControlValidationRule<C>>();
	private C control;
	private String controlName;
	private String validControlStyle;
	private String invalidControlStyle;
	
	public ControlValidation(C control, String controlName) {
		this.control = control;
		this.controlName = controlName;
	}
	public ControlValidation(C control, String controlName,String validControlStyle, String invalidControlStyle) {
		this.control = control;
		this.controlName = controlName;
		this.validControlStyle = validControlStyle;
		this.invalidControlStyle = invalidControlStyle;
	}
	
	public C getControl() {
		return control;
	}

	public void setControl(C control) {
		this.control = control;
	}

	public String getControlName() {
		return controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	public String getValidControlStyle() {
		return validControlStyle;
	}

	public void setValidControlStyle(String validControlStyle) {
		this.validControlStyle = validControlStyle;
	}

	public String getInvalidControlStyle() {
		return invalidControlStyle;
	}

	public void setInvalidControlStyle(String invalidControlStyle) {
		this.invalidControlStyle = invalidControlStyle;
	}

	public ArrayList<ControlValidationRule<C>> getRules(){
		return rules;
	}
	/*
	 * validates the control against validation rules
	 * (non-Javadoc)
	 * @see ui.validation.IControlValidation#validate(java.util.ArrayList)
	 * @param errorMessages validation messages will be added to this list
	 */
	public boolean validate(ArrayList<String> errorMessages) {
		boolean isValid = true;
		for (ControlValidationRule<C> rule : rules) {
			if(!rule.validate(this.control)) {
				isValid = false;
				errorMessages.add(rule.getErrorMessage(this.controlName));
			}
		}
		
		if(isValid) {
			if(this.validControlStyle != null) 
				control.setStyle(validControlStyle);
		} else {
			if(this.invalidControlStyle != null)
				control.setStyle(invalidControlStyle);
		}
		
		return isValid;
	}
}
