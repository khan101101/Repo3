package ui.validation;

import java.util.ArrayList;
import java.util.List;
/*
 * @author Bilen Yavuz
 * a base class to validate a scene (input controls)
 */
public  class SceneValidation<C> {
	protected ArrayList<IControlValidation> controlValidations = new  ArrayList<IControlValidation>();
	protected boolean isValid = false;
	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	protected ArrayList<String> errorMessages;
	
	public List<String> getErrorMessages(){
		return errorMessages;
	}
	
	public boolean validate() {
		isValid = true;
		errorMessages = new ArrayList<String>();
		
		for (IControlValidation controlValidation : controlValidations) {
		
			if(!controlValidation.validate(errorMessages))
				isValid = false;
		}
		
		
		
		return isValid;
	}
	
}
