import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
	Rectangle brick = new Rectangle();
	
	public Brick(int x, int y){
		brick.setX(x);
		brick.setY(y);
		brick.setWidth(20);
		brick.setHeight(100);
//		brick.setArcWidth(20);
//		brick.setArcHeight(20);
		brick.setFill(Color.rgb(153, 255, 00));
	}
}
