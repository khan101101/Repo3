package ui.validation;

import java.util.ArrayList;

/*
 * @author Bilen Yavuz
 * validate a control 
 */
public interface IControlValidation {
	 boolean validate(ArrayList<String> errorMessages);
}
