package ui.activity;

import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import studyplan.*;
import ui.validation.*;

/*
 * @author Munkhzul Baatar
 * Controller  of MVC pattern for Activity objects.
 */
public class ActivityViewController implements Initializable {

	@FXML
	private TextField nameTextField;
	@FXML
	private TextField locationTextField;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private TextField startTimeTextField;
	@FXML
	private TextField durationTextField;
	@FXML
	private VBox validationSummary;
	@FXML
	private ComboBox<String> repeatComboBox;
	@FXML
	private TextField repeatTextField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		repeatComboBox.getItems().addAll("None", "Weekly", "Every Two Weeks");
		repeatTextField.setText("0");

		// set validation
		validation = new ActivityViewValidation(this, "-fx-text-box-border: green", "-fx-text-box-border: red");

	}

	private Activity activity;
	private ActivityViewValidation validation;

	/*
	 * Bind  model to user interface
	 */
	void dataBind(Activity activity) {
		this.activity = activity;
		if (activity != null) {
			nameTextField.setText(activity.getName());
			locationTextField.setText(activity.getLocation());
			
			startDatePicker.setValue(activity.getStart().toLocalDate());
			startTimeTextField.setText(activity.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
			
			durationTextField.setText(Long.toString(activity.getDuration().toMinutes()));
			repeatComboBox.getSelectionModel().select(activity.getRepeat());
			repeatTextField.setText(activity.getRepeatTimes().toString());
		}
	}

	public void save(ActionEvent event) {

		if (validation.validate()) {

			if (activity == null)
				activity = new Activity();

			activity.setName(nameTextField.getText());
			activity.setLocation(locationTextField.getText());
			activity.setStart(startDatePicker.getValue().atTime(LocalTime.parse(startTimeTextField.getText())));
			activity.setDuration(Duration.ofMinutes(Long.parseLong(durationTextField.getText())));
			activity.setRepeat(repeatComboBox.getSelectionModel().getSelectedIndex());
			activity.setRepeatTimes(Integer.parseInt(repeatTextField.getText()));

			Stage stage = (Stage) nameTextField.getScene().getWindow();
			stage.close();
		}

	}

	/*
	 * creates a scene from activity view and sets it to provided stage and return the controller of the scene
	 */
	public static ActivityViewController createView(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(ActivityViewController.class.getResource("ActivityView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		ActivityViewController controller = loader.getController();
		return controller;
	}

	/*
	 * creates a view for given activity and shows it within a modal window
	 */
	public static Activity showWindow(Activity activity) throws Exception {

		Stage modalStage = new Stage();

		modalStage.initModality(Modality.APPLICATION_MODAL);

		ActivityViewController controller = createView(modalStage);

		if (activity != null) {
			controller.dataBind(activity);
			
			modalStage.setTitle(activity.getName());
		}else {
			modalStage.setTitle("New Activity");
		}
			

		modalStage.showAndWait();

		if (controller.validation.isValid())
			return controller.activity;
		else
			return null;

	}

	/*
	 * validates the Activity view
	 */
	public class ActivityViewValidation extends ui.validation.SceneValidation<ActivityViewController> {

		private ActivityViewController controller;

		private String invalidControlStyle;

		public ActivityViewValidation(ActivityViewController controller, String validControlStyle,String invalidControlStyle) {
			this.controller = controller;
	
			this.invalidControlStyle = invalidControlStyle;

			ControlValidation<TextField> nameValidation = new ControlValidation<TextField>(controller.nameTextField,
					"Name", validControlStyle, invalidControlStyle);
			nameValidation.getRules().add(new ui.validation.TextFieldNotEmptyRule());
			super.controlValidations.add(nameValidation);

			ControlValidation<TextField> locationValidation = new ControlValidation<TextField>(
					controller.locationTextField, "Location", validControlStyle, invalidControlStyle);
			locationValidation.getRules().add(new ui.validation.TextFieldNotEmptyRule());
			super.controlValidations.add(locationValidation);

			ControlValidation<TextField> startTimeValidation = new ControlValidation<TextField>(
					controller.startTimeTextField, "Start Time", validControlStyle, invalidControlStyle);
			startTimeValidation.getRules().add(new ui.validation.TextFieldTimeFormatRule());
			super.controlValidations.add(startTimeValidation);

			ControlValidation<TextField> durationValidation = new ControlValidation<TextField>(
					controller.durationTextField, "Duration", validControlStyle, invalidControlStyle);
			durationValidation.getRules().add(new ui.validation.TextFieldIntegerFormatRule(5, 180));
			super.controlValidations.add(durationValidation);

			ControlValidation<TextField> repeatTimesValidation = new ControlValidation<TextField>(
					controller.repeatTextField, "Repeat Times", validControlStyle, invalidControlStyle);
			repeatTimesValidation.getRules().add(new ui.validation.TextFieldIntegerFormatRule(0, 52));
			super.controlValidations.add(repeatTimesValidation);

		}

		@Override
		public boolean validate() {
			controller.validationSummary.getChildren().clear();

			if (super.validate()) {

				if (controller.activity != null) {
					// validate name
					for (Activity act : controller.activity.getCourse().getActivities()) {
						if (act.getName() == nameTextField.getText() && act != controller.activity) {
							isValid = false;
							errorMessages.add("There is already an activity with name " + act.getName());
							nameTextField.setStyle(invalidControlStyle);
						}
					}

					Semester semester = controller.activity.getCourse().getSemester();
					if (controller.startDatePicker.getValue().isBefore(semester.getStartDate())
							|| controller.startDatePicker.getValue().isAfter(semester.getEndDate())) {
						isValid = false;
						errorMessages
								.add("Start date must be with in semester start (" + semester.getStartDate().toString()
										+ ") and end (" + semester.getEndDate() + ") dates");
						controller.startDatePicker.setStyle(invalidControlStyle);
					}
				}

			}

			if (!isValid)
				ui.validation.ValidationSummaryUtility.addErrorMessages(controller.validationSummary,		super.getErrorMessages());
			return isValid;
		}

	}
}
