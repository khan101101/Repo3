
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class loginMain extends Application {
	
	
	  @Override
	    public void start(Stage primaryStage) {
	        try {
	            // Read file fxml and draw interface.
	        	
	            Parent root = FXMLLoader.load(loginMain.class.getResource("login.fxml"));
	 
	            primaryStage.setTitle("Login App");
	            primaryStage.setScene(new Scene(root));
	            primaryStage.show();
	         
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
