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
import ui.validation.ControlValidation;

/*
 * @author Munkhzul Baatar
 * Controller  of MVC pattern for ActivityInstance objects.
 */
public class ActivityInstanceViewController implements Initializable {

	
	@FXML
	Label nameLabel;
	@FXML
	Label locationLabel;
	@FXML
	Label startDateLabel;
	@FXML
	Label startTimeLabel;
	@FXML
	Label durationLabel;
	@FXML
	Label messageLabel;
	@FXML
	TextField goalTextField;
	@FXML
	TextArea notesTextArea;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	
	private ActivityInstance activityInstance;
	
	/*
	 * bind ActivityInstance model to view
	 */
	public void dataBind(ActivityInstance activityInstance) {
		this.activityInstance = activityInstance;
		if(activityInstance != null) {
			Activity activity = activityInstance.getActivity();
			nameLabel.setText(activity.getName());
			locationLabel.setText(activity.getLocation());
			startDateLabel.setText(activityInstance.getDate().toString());
			startTimeLabel.setText(activity.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
			durationLabel.setText(Long.toString(activity.getDuration().toMinutes()));
			goalTextField.setText(activityInstance.getGoal());
			notesTextArea.setText(activityInstance.getNotes());
		}
		
	}
	
	public void save(ActionEvent event) {

		try {
			
			activityInstance.setGoal(goalTextField.getText());
			activityInstance.setNotes(notesTextArea.getText());
			
			Stage stage = (Stage) messageLabel.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			messageLabel.setText(e.getMessage());
		}
	}
	
	/*
	 * creates a scene from activity instance view and sets it to provided stage and return the controller of the scene
	 */
	public static ActivityInstanceViewController createView(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(ActivityInstanceViewController.class.getResource("ActivityInstanceView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		ActivityInstanceViewController controller = loader.getController();
		return controller;
	}
	
	/*
	 * creates a view for given activity instance and shows it within a modal window 
	 */
	public static ActivityInstance showWindow(ActivityInstance activity) throws Exception {

		Stage modalStage = new Stage();

		modalStage.initModality(Modality.APPLICATION_MODAL);

		ActivityInstanceViewController controller = createView(modalStage);

		if (activity != null)
			controller.dataBind(activity);

		modalStage.setTitle("Activity Instance");
		
		modalStage.showAndWait();

		return controller.activityInstance;

	}
	
}
