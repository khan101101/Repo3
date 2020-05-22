import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main_page {

    //@Override
    public void start(Stage primaryStage) throws Exception 
  {
        Parent root = FXMLLoader.load(getClass().getResource("Page.fxml"));

        primaryStage.setTitle("Taskplanner");
        primaryStage.setScene(new Scene(root,500,500));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
}


	private static void launch(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
