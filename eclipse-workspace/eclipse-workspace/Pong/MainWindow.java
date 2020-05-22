
import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow extends Application{
	
	static MainElements me = new MainElements();
	static StartElements start = new StartElements();
	static statisticElements statistic = new statisticElements();
	static PongGUI pong = new PongGUI();

	public static void main(String[] args) {
		
//		me.MainButtons();
		start.startButtons();
		statistic.backButton();
		launch(args);
		
	}

	static Stage stage = new Stage();
	public void start(Stage stage) throws Exception {
		try {		
			this.stage.setTitle("Pong");
			this.stage.setScene(me.insert());
			this.stage.show();
			
		} catch (Exception e) {
			
		}
	}
}