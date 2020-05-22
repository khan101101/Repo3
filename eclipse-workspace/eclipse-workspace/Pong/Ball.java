import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Circle;

public class Ball {
	static Agent agent = new Agent();
	static PongGUI pong = new PongGUI();
	static Circle ball = new Circle();
	static AnimationTimer animation;
	static int startX = 640;
	static int startY = 360;
	static int radius = 10;
	static boolean move = false;
	static int speed = 10; 
	static int speedX = 0;
	static int speedY = 0;
	static int speedBrick = 0;
	
	public Ball(){
		ball.setRadius(radius);
		ball.setCenterX(startX);
		ball.setCenterY(startY);
		ball.setFill(Color.rgb(153, 255, 00));
	}
	
	public static void start(){
		int direction = (int)(Math.random()*2);
		/////////////////////////////////////////////////////////////////
		direction = 1;
		/////////////////////////////////////////////////////////////////
		if(direction == 0){
			speedX = speed * (-1);
			speedY = (int)(Math.random()*21 +10)-20;
		} else {
			speedX = speed;
			speedY = (int)(Math.random()*21 +10)-20;
		}
		
		animation = new AnimationTimer() {
			@Override
			public void handle(long now) {
//				pong.pongButtons();
				double brickY = pong.leftBrick.brick.getY();

				if(brickY <= 0 || brickY >= 620) {
					speedBrick = 0;
					if(brickY <=0) {
						pong.leftBrick.brick.setY(1);
					} else {
						pong.leftBrick.brick.setY(619);
					}
				}
				pong.leftBrick.brick.setY(pong.leftBrick.brick.getY() + speedBrick);
			
				if(ball.getCenterX() > 200 && ball.getCenterX() < 1100){
					if(ball.getCenterY() > 10 && ball.getCenterY() < 710){
						
						//Agent Move
						if(speedX > 0){
							pong.rightBrick.brick.setY(pong.rightBrick.brick.getY() + agent.move(ball.getCenterY()));
						}
						
						ball.setCenterX(ball.getCenterX() + speedX);
						ball.setCenterY(ball.getCenterY() + speedY);
					} else {
						if(ball.getCenterY() <= 10){
							ball.setCenterY(11);
						} else {
							ball.setCenterY(709);
						}
						speedY = speedY * (-1);
					}
					
				} else {
					colision(ball.getCenterX(), ball.getCenterY());
				}
				
			}
		};
		
		animation.start();
		
	}
	
	public static void colision(double posX, double posY){
		double upper;
		double lower;
		if(posX <= 300){
			upper = pong.leftBrick.brick.getY();
			lower = pong.leftBrick.brick.getY() + pong.leftBrick.brick.getHeight();
			
			if(posY >= upper && posY <= lower){
				speedX = speed;
				angle(upper, posY);
				ball.setCenterX(201);
			} else {
				pong.counterRight++;
				pong.pointRight.setText(pong.counterRight.toString());
				animation.stop();
				speedX = 0;
				speedY = 0;
				ball.setCenterX(startX);
				ball.setCenterY(startY);
				pong.leftBrick.brick.setY(310);
				pong.rightBrick.brick.setY(310);
				move = false;
			}
			
		} else {
			
			upper = pong.rightBrick.brick.getY();
			lower = pong.rightBrick.brick.getY() + pong.rightBrick.brick.getHeight();
			
			if(posY >= upper && posY <= lower){
				speedX = speed * (-1);
				angle(upper, posY);
				ball.setCenterX(999);
			} else {
				pong.counterLeft++;
				pong.pointLeft.setText(pong.counterLeft.toString());
				animation.stop();
				speedX = 0;
				speedY = 0;
				ball.setCenterX(640);
				ball.setCenterY(360);
				pong.leftBrick.brick.setY(310);
				pong.rightBrick.brick.setY(310);
				move = false;
			}
		}
	}
	
	public static void angle(double brickUpper, double ballPosY){
		double sector = ballPosY - brickUpper;
		
		if(sector >= 0 && sector <= 10){
			speedY = -16;
		} else if(sector >= 11 && sector <= 20){
			speedY = -13;
		} else if(sector >= 21 && sector <= 30){
			speedY = -10;
		} else if(sector >= 31 && sector <= 40){
			speedY = -6;
		} else if(sector >= 41 && sector <= 50){
			speedY = -3;
		} else if(sector >= 51 && sector <= 60){
			speedY = 3;
		} else if(sector >= 61 && sector <= 70){
			speedY = 6;
		} else if(sector >= 71 && sector <= 80){
			speedY = 10;
		} else if(sector >= 81 && sector <= 90){
			speedY = 13;
		} else if(sector >= 91 && sector <= 100){
			speedY = 16;
		}
		
	}
}
