import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class Cont_page {
	
	@FXML
	Button btnSignIn;

	@FXML
	public void signInButtonClicked() throws Exception
	{
	//Here I want call the new Scene(SignInGUI.fxml) in my old Stage
	   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Page.fxml"));
	}

}
