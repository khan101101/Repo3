package ui.profile;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studyplan.Main;
import studyplan.Profile;

/*
 * @author Munkhzul Baatar
 * Controller  of MVC pattern for Profile object.
 */
public class ProfileViewController implements Initializable {

	@FXML
	TextField nameTextField;
	
	@FXML
	TextField majorTextField;
	@FXML
	TextField idTextField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadData();
	}
	
	
	public void loadData()
	{
		Profile profile = Main.getCurrentApplication().getProfile();
		if(profile != null)
		{
			nameTextField.setText(profile.getName());
			majorTextField.setText(profile.getMajor());
			idTextField.setText(profile.getId());
		}
	}
	
	
	public void save(ActionEvent event)
	{
		Main app = Main.getCurrentApplication();
		Profile profile = app.getProfile();
		if(profile == null) {
			profile = new Profile();
			app.setProfile(profile);
		}
		profile.setName(nameTextField.getText());
		profile.setMajor(majorTextField.getText());
		profile.setId(idTextField.getText());
		
		app.save();
		
		Stage stage = (Stage)nameTextField.getScene().getWindow();
		stage.close();
		
	}
	
	/*
	 * creates a scene from profile view and sets it to provided stage and return the controller of the scene
	 */
	public static ProfileViewController createView(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(ProfileViewController.class.getResource("ProfileView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		ProfileViewController controller = loader.getController();
		return controller;
	}

	/*
	 * creates a view for current course and shows it within a modal window 
	 */
	public static void showWindow() throws Exception
	{
		Stage modalStage = new Stage();
		modalStage.initModality(Modality.APPLICATION_MODAL);
		ProfileViewController.createView(modalStage);
		modalStage.setTitle("Profile");
		modalStage.showAndWait();

	}
	
	
}
