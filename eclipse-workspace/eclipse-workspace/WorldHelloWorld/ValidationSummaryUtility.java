package ui.validation;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
/*
 * @author Bilen Yavuz
 * helper methods to create labels for error messages
 */
public class ValidationSummaryUtility {

	public static void addErrorMessage(javafx.scene.layout.VBox validationSummary, String message) {
		Label label = new Label(message);
		label.setTextFill(Color.RED);
		validationSummary.getChildren().add(label);
	}
	
	public static void addErrorMessages(javafx.scene.layout.VBox validationSummary, List<String> messages) {
		for (String message : messages) {
			addErrorMessage(validationSummary, message);
			
		}
	}
	
	
}
