package ui.selfstudy;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studyplan.*;
import ui.validation.ControlValidation;

/*
 * @author Bilen Yavuz
 * Controller  of MVC pattern for SelfStudy objects.
 */
public class SelfStudyViewController implements Initializable {

	@FXML
	private Label courseLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private TextField locationTextField;
	@FXML
	private TextField startTimeTextField;
	@FXML
	private TextField durationTextField;
	@FXML
	private TextField goalTextField;
	@FXML
	private TextArea notesTextArea;
	
	@FXML
	private VBox validationSummary;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startTimeTextField.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		validation = new SelfStudyViewValidation(this,"-fx-text-box-border: green","-fx-text-box-border: red");
	}
	
	private SelfStudy selfStudy;
	private SelfStudyViewValidation validation;
	/*
	 * Bind  model to user interface
	 */
	public void dataBind(SelfStudy selfStudy) {
		this.selfStudy = selfStudy;
		
		if(selfStudy != null) {
			courseLabel.setText(selfStudy.getCourse().getName());
			dateLabel.setText(selfStudy.getStart().toLocalDate().toString());
			locationTextField.setText(selfStudy.getLocation());
			startTimeTextField.setText(selfStudy.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
			durationTextField.setText(Long.toString(selfStudy.getDuration().toMinutes()));
			goalTextField.setText(selfStudy.getGoal());
			notesTextArea.setText(selfStudy.getNotes());
		}
	}
	
	public void save(ActionEvent event) {
		
		if(validation.validate()) {
		
		if(selfStudy == null) selfStudy = new SelfStudy();
		
		selfStudy.setLocation(locationTextField.getText());
		selfStudy.setStart(LocalDate.parse(dateLabel.getText()).atTime(LocalTime.parse(startTimeTextField.getText())));
		selfStudy.setDuration(Duration.ofMinutes(Long.parseLong(durationTextField.getText())));
		selfStudy.setGoal(goalTextField.getText());
		selfStudy.setNotes(notesTextArea.getText());
		
		Stage stage = (Stage) courseLabel.getScene().getWindow();
		stage.close();
		}
		
	}
	/*
	 * creates a scene from selfstudy view and sets it to provided stage and return the controller of the scene
	 */
	public static SelfStudyViewController createView(Stage stage) throws Exception{
		FXMLLoader loader = new FXMLLoader(SelfStudyViewController.class.getResource("SelfStudyView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		SelfStudyViewController controller = loader.getController();
		return controller;
	}
	
	/*
	 * creates a view for given activity and shows it within a modal window 
	 */
	public static SelfStudy showWindow(SelfStudy selfStudy) throws Exception{
		Stage modalStage = new Stage();
		modalStage.initModality(Modality.APPLICATION_MODAL);
		SelfStudyViewController controller = createView(modalStage);
		
		if(selfStudy != null) {
			controller.dataBind(selfStudy);
		}
		
		modalStage.setTitle("Self Study");
		
		modalStage.showAndWait();
	
		if(controller.validation.isValid())
			return controller.selfStudy;
		else
			return null;
	}
	/*
	 * validates SelfStudy View
	 */
	public class SelfStudyViewValidation extends ui.validation.SceneValidation<SelfStudyViewController>{
		private SelfStudyViewController controller;
		private String validControlStyle;
		private String invalidControlStyle;
		
		public SelfStudyViewValidation(SelfStudyViewController controller, String validControlStyle, String invalidControlStyle) {
			this.controller = controller;
			this.validControlStyle = validControlStyle;
			this.invalidControlStyle = invalidControlStyle;
		
			
			ControlValidation<TextField> locationValidation = new ControlValidation<TextField>(controller.locationTextField, "Location",validControlStyle, invalidControlStyle);
			locationValidation.getRules().add(new ui.validation.TextFieldNotEmptyRule());
			super.controlValidations.add(locationValidation);
			
			
			ControlValidation<TextField> startTimeValidation = new ControlValidation<TextField>(controller.startTimeTextField, "Start Time",validControlStyle, invalidControlStyle);
			startTimeValidation.getRules().add(new ui.validation.TextFieldTimeFormatRule());
			super.controlValidations.add(startTimeValidation);
			
			ControlValidation<TextField> durationValidation = new ControlValidation<TextField>(controller.durationTextField, "Duration",validControlStyle, invalidControlStyle);
			durationValidation.getRules().add(new ui.validation.TextFieldIntegerFormatRule(5,180));
			super.controlValidations.add(durationValidation);
		}
		
		
		@Override
		public boolean validate() {
			controller.validationSummary.getChildren().clear();
			
			
			super.validate();
			
			if(!isValid)
				ui.validation.ValidationSummaryUtility.addErrorMessages(controller.validationSummary, validation.getErrorMessages());
			
			return isValid;
		}		
	}
}
