import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class MainElements {

	StackPane stack = new StackPane();
	static Button start = new Button("Start");
	static Button statistics = new Button("Statistics");
	static Button exit = new Button("Exit");
	
	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public Scene insert() throws MalformedURLException{
		
		VBox buttonPane = new VBox();
		buttonPane.setSpacing(10);
		buttonPane.setPadding(new Insets(15,10, 15,10));
		
		BorderPane titel = new BorderPane();
		titel.setMinSize(1280, 300);
		
		BorderPane startButtonPane = new BorderPane();
		BorderPane statisticsButtonPane = new BorderPane();
		BorderPane exitButtonPane = new BorderPane();
		
		File file = new File("pong2.jpg");
		String localUrl = file.toURI().toURL().toString();
		Image image = new Image(localUrl);
		ImageView imageView = new ImageView(image);
		
		Reflection reflection = new Reflection();
		reflection.setBottomOpacity(0.0);
		reflection.setTopOpacity(0.5);
		reflection.setTopOffset(10);
		reflection.setFraction(0.7);
		Text text = new Text();
	    text.setX(20.0);
	    text.setY(90.0);
	    text.setCache(true);
	    text.setText("Pong");
	    text.setFill(Color.web("0x99FF00"));
	    text.setFont(Font.font(null, FontWeight.BOLD, 80));
	    
	    text.setEffect(reflection);
	    titel.setCenter(text);
	    
	    buttonPane.getChildren().addAll(titel);
	    
		
		start.setPrefSize(500, 50);
		statistics.setPrefSize(500, 50);
		exit.setPrefSize(500, 50);
		
		startButtonPane.setCenter(start);
		statisticsButtonPane.setCenter(statistics);
		exitButtonPane.setCenter(exit);
		buttonPane.getChildren().add(startButtonPane);
		buttonPane.getChildren().add(statisticsButtonPane);
		buttonPane.getChildren().add(exitButtonPane);
		
		stack.getChildren().add(imageView);
		stack.getChildren().add(buttonPane);
		
		Scene scene = new Scene(stack,1280,720);

		MainButtons();
		return scene;
		
	}
	
	/**
	 * 
	 */
	public static void MainButtons(){
		MainWindow gui =new MainWindow();
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StartElements se = new StartElements();
				try {
					gui.stage.setScene(se.insert());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gui.stage.show();
				
			}
		});
		
		statistics.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				statisticElements se = new statisticElements();
				gui.stage.setScene(se.insert());
				gui.stage.show();
				
			}
		});
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gui.stage.close();
				
			}
		});
	}
}