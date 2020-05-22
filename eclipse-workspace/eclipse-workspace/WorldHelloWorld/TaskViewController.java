package ui.task;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studyplan.*;
import ui.validation.ControlValidation;

/*
 * @author Munkhzul Baatar
 * Controller  of MVC pattern for Task  objects.
 */
public class TaskViewController implements Initializable {

	@FXML
	private TextField nameTextField;
	@FXML
	private DatePicker dueDatePicker;

	@FXML
	private CheckBox completedCheckBox;
	@FXML
	private TextArea detailsTextArea;
	@FXML
	private VBox validationSummary;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		completedCheckBox.setText("Is Completed ?");
		validation = new TaskViewValidation(this, "-fx-text-box-border: green", "-fx-text-box-border: red");
	}

	Task task;
	TaskViewValidation validation;

	/*
	 * Bind model to user interface
	 */
	void dataBind(Task t) {
		task = t;
		if (t != null) {
			nameTextField.setText(t.getName());
			dueDatePicker.setValue(t.getDueDate());
			completedCheckBox.setSelected(t.getIsCompleted());
			detailsTextArea.setText(t.getDetails());
		}
	}

	public void save(ActionEvent event) {

		if (validation.validate()) {
			if (task == null)
				task = new Task();

			task.setName(nameTextField.getText());
			task.setDueDate(dueDatePicker.getValue());
			task.setIsCompleted(completedCheckBox.isSelected());
			task.setDetails(detailsTextArea.getText());

			Stage stage = (Stage) nameTextField.getScene().getWindow();
			stage.close();
		}
	}

	/*
	 * creates a scene from task view and sets it to provided stage and return the controller of the scene
	 */
	public static TaskViewController createView(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(TaskViewController.class.getResource("TaskView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		TaskViewController controller = loader.getController();
		return controller;
	}

	/*
	 * creates a view for given task and shows it within a modal window
	 */
	public static Task showWindow(Task task) throws Exception {

		Stage modalStage = new Stage();

		modalStage.initModality(Modality.APPLICATION_MODAL);

		TaskViewController controller = createView(modalStage);

		if (task != null) {
			controller.dataBind(task);
			modalStage.setTitle(task.getName());
		} else {
			modalStage.setTitle("New Task");
		}
		modalStage.showAndWait();

		if (controller.validation.isValid())
			return controller.task;
		else
			return null;

	}

	/*
	 * validates the Task view
	 */
	public class TaskViewValidation extends ui.validation.SceneValidation<TaskViewController> {

		private TaskViewController controller;

		private String invalidControlStyle;

		public TaskViewValidation(TaskViewController controller, String validControlStyle, String invalidControlStyle) {
			this.controller = controller;

			this.invalidControlStyle = invalidControlStyle;

			ControlValidation<TextField> nameValidation = new ControlValidation<TextField>(controller.nameTextField,
					"Name", validControlStyle, invalidControlStyle);
			nameValidation.getRules().add(new ui.validation.TextFieldNotEmptyRule());
			super.controlValidations.add(nameValidation);

		}

		@Override
		public boolean validate() {
			controller.validationSummary.getChildren().clear();

			if (super.validate()) {

				if (this.controller.task != null) {

					Semester semester = this.controller.task.getCourse().getSemester();
					if (controller.dueDatePicker.getValue().isBefore(semester.getStartDate())
							|| controller.dueDatePicker.getValue().isAfter(semester.getEndDate())) {
						isValid = false;
						errorMessages
								.add("Due date must be with in semester start (" + semester.getStartDate().toString()
										+ ") and end (" + semester.getEndDate() + ") dates");
						controller.dueDatePicker.setStyle(invalidControlStyle);
					}
				}

			}

			if (!isValid)
				ui.validation.ValidationSummaryUtility.addErrorMessages(controller.validationSummary,
						super.getErrorMessages());
			return isValid;
		}

	}
}
