package org.o7planning.javafx;

import java.io.FileInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.prism.Image;
import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Circle;


public class MyController implements Initializable {
	
	//Here is the declaration
	
	  @FXML 
	  private TextField myTextField3;
	  @FXML 
	  
	   private Button myButton;
	  
	   @FXML
	   private TextField myTextField;
	   
	   @FXML
	   private TextField myTextField2;
	   
	   @FXML
	   private Circle myCircle;
	  
	   //here is the initialization
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
	       // TODO (don't really need to do anything here).
	      
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   @FXML
	   public void showDateTime(ActionEvent event) {
	       System.out.println("Button Clicked!");
	      
	       Date now= new Date(); // a new Date-object is created
	  
	       DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
	     
	      
	        // Model Data
	        String dateTimeString = df.format(now);// format the date stored in now-date-object via format method of df-dateformat class
	     
	        // Show in VIEW
	        myTextField.setText(dateTimeString);
	        myTextField2.setText(dateTimeString);
	  
	       
	   }
	   
	   @FXML
	   public void showMessage(ActionEvent event) {
		   System.out.println("Circle Clicked!");
		     
	   }
	   
	  
}			


