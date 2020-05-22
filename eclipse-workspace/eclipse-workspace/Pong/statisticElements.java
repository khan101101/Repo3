import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class statisticElements {
	
	static Button back = new Button("back");
	
	public Scene insert(){
		back.setPrefSize(250, 30);
		
		ComboBox combo = new ComboBox();
		combo.setPrefSize(250, 30);
		
		HBox top = new HBox();
		top.getChildren().add(combo);
		top.getChildren().add(back);
		
		VBox root = new VBox();
		root.setStyle("-fx-background-color: #000000;");
		root.getChildren().add(top);
		
		Scene scene = new Scene(root,1280,720);
		return scene;
	}
	
	public static void backButton(){
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				MainWindow gui =new MainWindow();
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