package ui.semester;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studyplan.*;
import ui.validation.ControlValidation;

/*
 * @author Munkhzul Baatar
 * Controller  of MVC pattern for semester objects.
 */
public class SemesterViewController implements Initializable {
	

	@FXML
	TextField nameTextField;
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;
	
	@FXML
	private VBox validationSummary;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		validation = new SemesterViewValidation(this, "-fx-text-box-border: green", "-fx-text-box-border: red");
		
	}

	Semester semester;
	SemesterViewValidation validation;

	public void bindSemester(Semester s) {
		semester = s;
		if (semester != null) {
			nameTextField.setText(semester.getName());
			startDatePicker.setValue(semester.getStartDate());
			endDatePicker.setValue(semester.getEndDate());
		}
	}
	
	

	public void save(ActionEvent event) {
		

		if(validation.validate()) {
		
		if (semester == null) semester = new Semester();
			
		
		
		semester.setName(nameTextField.getText());
		semester.setStartDate(startDatePicker.getValue());
		semester.setEndDate(endDatePicker.getValue());
		
		
		Stage stage = (Stage) nameTextField.getScene().getWindow();
		stage.close();
		}

	}
	/*
	 * creates a scene from semester view and sets it to provided stage and return
	 * the controller of the scene
	 */
	public static SemesterViewController createView(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(SemesterViewController.class.getResource("SemesterView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		SemesterViewController controller = loader.getController();
		return controller;
	}

	/*
	 * creates a view for given semester and shows it within a modal window
	 */
	public static Semester showWindow(Semester s) 
	{
		try {
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			
			SemesterViewController controller = SemesterViewController.createView(modalStage);
	
			if (s != null) {
				controller.bindSemester(s);
				modalStage.setTitle(s.getName());
			}
			else {
				modalStage.setTitle("New Semester");
			}
			
			
			modalStage.showAndWait();
	
			if (controller.validation.isValid())
				return controller.semester;
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public class SemesterViewValidation extends ui.validation.SceneValidation<SemesterViewController>{
		private SemesterViewController controller;
		
		private String invalidControlStyle;
		
		public SemesterViewValidation(SemesterViewController controller, String validControlStyle,String invalidControlStyle) {
			this.controller = controller;
			this.invalidControlStyle = invalidControlStyle;

			ControlValidation<TextField> nameValidation = new ControlValidation<TextField>(controller.nameTextField,
					"Name", validControlStyle, invalidControlStyle);
			nameValidation.getRules().add(new ui.validation.TextFieldNotEmptyRule());
			super.controlValidations.add(nameValidation);
			
			ControlValidation<DatePicker> startValidation = new ControlValidation<DatePicker>(controller.startDatePicker,
					"Start Date", validControlStyle, invalidControlStyle);
			startValidation.getRules().add(new ui.validation.DatePickerNotEmptyRule());
			super.controlValidations.add(startValidation);
			
			ControlValidation<DatePicker> endValidation = new ControlValidation<DatePicker>(controller.endDatePicker,
					"End Date", validControlStyle, invalidControlStyle);
			endValidation.getRules().add(new ui.validation.DatePickerNotEmptyRule());
			super.controlValidations.add(endValidation);
		}
		
		@Override
		public boolean validate() {
			controller.validationSummary.getChildren().clear();

			if (super.validate()) {
				// validate start & end date order
				if(this.controller.endDatePicker.getValue().isBefore(this.controller.startDatePicker.getValue())) {
					errorMessages.add("End date must be later than start date ");
					startDatePicker.setStyle(invalidControlStyle);
					isValid  = false;
				}

			

			}

			if (!isValid)
				ui.validation.ValidationSummaryUtility.addErrorMessages(controller.validationSummary, super.getErrorMessages());
			return isValid;
		}

	}
	
}
