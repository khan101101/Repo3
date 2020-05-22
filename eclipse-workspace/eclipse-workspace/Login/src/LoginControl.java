import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginControl implements Initializable {

    @FXML
    private TextField T1;

    @FXML
    private Button MyButton;

    @FXML
    private TextField T2;

    @FXML
    private TextField T3;

    @FXML
    void da1717(ActionEvent event) {

    }
    
	 @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
	       // TODO (don't really need to do anything here).
	      
	   }

    @FXML
    public void handle(ActionEvent event) {
        if(T1.getText().isEmpty()) {
           System.out.println("provide your name");
            return;
        }
        if(T2.getText().isEmpty()) {
        	 System.out.println("provide your Email");
            return;
        }
        if(T3.getText().isEmpty()) {
        	 System.out.println("provide your Password");
            return;
        }

       System.out.println("Registration Successful!"+ "Welcome "); 
    }


}


