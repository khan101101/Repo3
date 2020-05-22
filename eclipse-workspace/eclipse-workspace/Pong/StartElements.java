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

public class StartElements {
	
	static Button HvM = new Button("H vs. M");
	static Button MvM = new Button("M vs. M");
	static Button back = new Button("back");
	
	StackPane stack = new StackPane();
	VBox vb = new VBox();
	BorderPane titel = new BorderPane();
	BorderPane HvMPane = new BorderPane();
	BorderPane MvMPane = new BorderPane();
	BorderPane backPane = new BorderPane();
	
	public Scene insert() throws IOException{
		
		File file = new File("pong2.jpg");
		String localUrl = file.toURI().toURL().toString();
		Image image = new Image(localUrl);
		ImageView imageView = new ImageView(image);
		stack.getChildren().add(imageView);
		
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
	    titel.setMinSize(1280, 300);
	    titel.setCenter(text);
	    vb.getChildren().addAll(titel);
	    
	    HvM.setPrefSize(500, 50);
		MvM.setPrefSize(500, 50);
		back.setPrefSize(500, 50);
		
		HvMPane.setCenter(HvM);
		MvMPane.setCenter(MvM);
		backPane.setCenter(back);
		vb.getChildren().add(HvMPane);
		vb.getChildren().add(MvMPane);
		vb.getChildren().add(backPane);
		vb.setSpacing(10);
		vb.setPadding(new Insets(15,10, 15,10));
		
		stack.getChildren().add(vb);
	
		Scene scene = new Scene(stack,1280,720);
		return scene;
	}
	
	public static void startButtons(){
		MainWindow gui =new MainWindow();
		
		HvM.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				PongGUI pg = new PongGUI();
				gui.stage.setScene(pg.insert());
				gui.stage.show();
			}
		});
		
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				MainElements se = new MainElements();
				try {
					gui.stage.setScene(se.insert());
					gui.stage.show();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
