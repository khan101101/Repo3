package test;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

public class Controller implements Initializable {
	
	@FXML 
	Button myButton;
	@FXML
	TextField TField;

	@Override	
	public void initialize(URL location, ResourceBundle resources) {	
	}
		  
		   public void methoda(ActionEvent event) {
		      
		       Date now= new Date(); // a new Date-object is created
		  
		       DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		     
		      
		        // Model Data
		        String dateTimeString = "This is a text";// format the date stored in now-date-object via format method of df-dateformat class
		     
		        // Show in VIEW
		        TField.setText(dateTimeString);
		      
		  
		       
		   }
		  
	}

		