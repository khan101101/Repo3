package org.o7planning.javafx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login_main extends Application {

	
	   @Override
	    public void start(Stage primaryStage) {
	        try {
	            // Read file fxml and draw interface.
	        	
	            Parent root = FXMLLoader.load(Login_main.class.getResource("Login.fxml"));
	 
	            primaryStage.setTitle("Login App");
	            primaryStage.setScene(new Scene(root));
	            primaryStage.show();
	         
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				launch(args);
	}

}
