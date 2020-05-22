
public class Agent {
	
	static PongGUI pong = new PongGUI();
	String name;

	public static int move(double ballPosY){
		
		if(ballPosY > (pong.rightBrick.brick.getY() +50)){
			return 10;
		} else if(ballPosY < (pong.rightBrick.brick.getY() +50)){
			return -10;
		} else {
			return 0;
		}
	}

}
