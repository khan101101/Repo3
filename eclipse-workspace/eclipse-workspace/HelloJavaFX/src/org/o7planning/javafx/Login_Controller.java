package org.o7planning.javafx;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class Login_Controller implements Initializable {
	 @FXML
	 private Button myButton;
	 @FXML
	 private TextField myTextField;
	 @FXML
	 private TextField TextField2;
	 
	    
	 @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
	       // TODO (don't really need to do anything here).
	      
	   }

	 @FXML 
	 public void action(ActionEvent event) {
	        myTextField.setText("button pressed");
	    }
	@FXML
	public void drag(ActionEvent event) {
		TextField2.setText("The circle is draged");
	}
}
