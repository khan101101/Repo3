package ui.course;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studyplan.*;
import ui.validation.ControlValidation;
/*
 * @author Munkhzul Baatar
 * Controller  of MVC pattern for Course objects.
 */
public class CourseViewController implements Initializable {

	@FXML
	TextField nameTextField;

	@FXML
	TextField instructorTextField;

	@FXML
	TextArea notesTextArea;

	@FXML
	private VBox validationSummary;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		validation = new CourseViewValidation(this, "-fx-text-box-border: green", "-fx-text-box-border: red");
	}

	private Course course;
	private CourseViewValidation validation;

	/*
	 * Bind course model to user interface
	 */
	public void dataBind(Course c) {
		course = c;
		nameTextField.setText(c.getName());
		instructorTextField.setText(c.getInstructor());
		notesTextArea.setText(c.getNotes());

	}

	public void save(ActionEvent event) {
		// clear previous validation
		validationSummary.getChildren().clear();
		try {
			if (validation.validate()) { // if the inputs are valid, assign the attributes of object 
				
				if (course == null)
					course = new Course();

				course.setName(nameTextField.getText());
				course.setInstructor(instructorTextField.getText());
				course.setNotes(notesTextArea.getText());
				
				// close the window
				Stage stage = (Stage) nameTextField.getScene().getWindow();
				stage.close();
			} else {
				ui.validation.ValidationSummaryUtility.addErrorMessages(validationSummary,
						validation.getErrorMessages());
			}
		} catch (Exception e) {
			ui.validation.ValidationSummaryUtility.addErrorMessage(validationSummary, e.getMessage());
		}

	}

	/*
	 * creates a scene from course view and sets it to provided stage and return the controller of the scene
	 */
	public static CourseViewController createView(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(CourseViewController.class.getResource("CourseView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		CourseViewController controller = loader.getController();
		return controller;
	}

	/*
	 * creates a view for given course and shows it within a modal window 
	 */
	public static Course showWindow(Course course) throws Exception {

		Stage modalStage = new Stage();

		modalStage.initModality(Modality.APPLICATION_MODAL);

		CourseViewController controller = createView(modalStage);

		if (course != null)
			controller.dataBind(course);

		modalStage.setTitle("Course");
		
		modalStage.showAndWait();

		if(controller.validation.isValid())
			return controller.course;
		else
			return null;

	}

	/*
	 * validates the Course view 
	 */
	public class CourseViewValidation extends ui.validation.SceneValidation<CourseViewController> {

		private CourseViewController controller;
		private String validControlStyle;
		private String invalidControlStyle;

		public CourseViewValidation(CourseViewController controller, String validControlStyle,
				String invalidControlStyle) {
			this.controller = controller;
			this.validControlStyle = validControlStyle;
			this.invalidControlStyle = invalidControlStyle;

			ControlValidation<TextField> nameValidation = new ControlValidation<TextField>(controller.nameTextField,
					"Name", validControlStyle, invalidControlStyle);
			nameValidation.getRules().add(new ui.validation.TextFieldNotEmptyRule());
			super.controlValidations.add(nameValidation);

		}

		@Override
		public boolean validate() {
			if (super.validate()) {

				if (this.controller.course != null) {
					// validate name
					for (Course act : this.controller.course.getSemester().getCourses()) {
						if (act.getName() == nameTextField.getText() && act != this.controller.course) {
							isValid = false;
							errorMessages.add("There is already an course with name " + act.getName());
							nameTextField.setStyle(invalidControlStyle);
						}
					}
				}

			}

			return isValid;
		}

	}
}
