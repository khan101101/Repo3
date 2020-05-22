package org.o7planning.javafx;

import java.awt.Button;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application {

	   @Override
	    public void start(Stage primaryStage) {
	        try {
	            // Read file fxml and draw interface.
	        	
	            Parent root = FXMLLoader.load(MyApplication.class.getResource("MyScene.fxml"));
	 
	            primaryStage.setTitle("My Application khan");
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
