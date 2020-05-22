package HelloWorld;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HelloWorldSpace extends Application {
	
	public void start(Stage stage) 
	{
	    stage.setTitle( "Timeline Example" );
	 
	    Group root = new Group();
	    Scene theScene = new Scene( root );
	    stage.setScene( theScene );
	 
	    Canvas canvas = new Canvas(1300,1300 );
	    root.getChildren().add( canvas );
	 
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	 
	    Image earth = new Image( "earth.png" );
	    Image sun   = new Image( "sun.png" );
	    Image space = new Image( "space.png" );
	   
	  
	 
	    final long startNanoTime = System.nanoTime();
	 
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	            double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
	 
	            double x = 500 + 220 * Math.cos(t);
	            double y = 400 + 220 * Math.sin(t);
	 
	            // background image clears canvas
	            gc.drawImage( space, 0, 0 );
	            gc.drawImage( earth, x, y );
	            gc.drawImage( sun, 500, 300 );

	        }
	    }.start();
	 
	    stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
