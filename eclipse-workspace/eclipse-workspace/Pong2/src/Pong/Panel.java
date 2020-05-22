package Pong;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class Panel extends JPanel implements Runnable {

private static final long serialVersionUID = 1L;
// Positions on X and Y for the ball, player 1 and player 2
private int pilotX = 10, pilotY = 100, num=10, num2=100, num3=230, num4=100;
Thread t;
int right=5; // to the right
int left= -5; //to the left
int up=5; // upward
int down= -5; // down
int w, h; // Width and height of the ball
// Scores
int play=0, contPlay2=0;
boolean player1FlagArr,player1FlagAba, player2FlagArr, player2FlagAba;
boolean p, gameOver;

public Panel(){
p=true;
t=new Thread(this);
t.start();
}

// Draw ball and ships
public void paintComponent(Graphics gc){
setOpaque(false);
super.paintComponent(gc);

// Draw ball
gc.setColor(Color.RED);
gc.fillOval(pilotX, pilotY, 8,8);

// Draw ships
gc.fillRect(num, num2,10, 25);
gc.fillRect(num3, num4, 10, 25);

//Draw scores
gc.drawString("Jugador1: "+play, 25, 10);
gc.drawString("Jugador2: "+contPlay2, 150, 10);

if(gameOver)
gc.drawString("Game Over", 100, 125);
}

// Positions on X and Y for the ball
public void Pos (int nx, int ny)
{
pilotX= nx;
pilotY= ny;
this.w=this.getWidth();
this.h=this.getHeight();
repaint();
}

// Here we receive from the game container class the key pressed
public void keyPressed(KeyEvent evt)
{
switch(evt.getKeyCode())
{
// Move ship 1
case KeyEvent.VK_W :
player1FlagArr = true;
break;
case KeyEvent.VK_S :
player1FlagAba = true;
break;

// Move ship 2
case KeyEvent.VK_UP:
player2FlagArr=true;
break;
case KeyEvent.VK_DOWN:
player2FlagAba=true;
break;
}
}

// Here we receive from the game container class the key released
public void keyReleased(KeyEvent evt)
{
switch(evt.getKeyCode())
{
// Mover Nave1
case KeyEvent.VK_W :
player1FlagArr = false;
break;
case KeyEvent.VK_S :
player1FlagAba = false;
break;

// Mover nave 2
case KeyEvent.VK_UP:
player2FlagArr=false;
break;
case KeyEvent.VK_DOWN:
player2FlagAba=false;
break;
}
}

// Move player 1
public void moverPlayer1()
{
if (player1FlagArr == true && num2 >= 0)
num2 += down;
if (player1FlagAba == true && num2 <= (this.getHeight()-25))
num2 += up;
dibujarPlayer1(num, num2);
}

// Move player 2
public void moverPlayer2()
{
if (player2FlagArr == true && num4 >= 0)
num4 += down;
if (player2FlagAba == true && num4 <= (this.getHeight()-25))
num4 += up;
dibujarPlayer2(num3, num4);
}

// Position on Y for the player 1
public void dibujarPlayer1(int x, int y){
this.num=x;
this.num2=y;
repaint();
}
// Position on Y for the player 2
public void dibujarPlayer2(int x, int y){
this.num3=x;
this.num4=y;
repaint();
}

public void run() {
// TODO Auto-generated method stub
boolean c2=false;
boolean c1=false;

while(true){

if(p){

// The ball move from left to right
if (c2)
{

pilotX += right;
if (pilotX >= (w - 8))
c2= false;
}
else
{

pilotX += left;
if ( pilotX <= 0)
c2 = true;
}


// The ball moves from up to down
if (c1)
{

pilotY += up;
if (pilotY >= (h - 8))
c1= false;

}
else
{

pilotY += down;
if ( pilotY <= 0)
c1 = true;
}
Pos(pilotX, pilotY);


try
{
Thread.sleep(50);
}
catch(InterruptedException ex)
{

}

// Move player 1
moverPlayer1();

// Move player 2
moverPlayer2();

// The score of the player 1 increase
if (pilotX >= (w - 8))
play++;

// The score of the player 2 increase
if ( pilotX == 0)
contPlay2++;

// Game over. Here you can change 6 to any value
// When the score reach to the value, the game will end
if(play==6 || contPlay2==6){
p=false;
gameOver=true;
}

// The ball stroke with the player 1
if(pilotX==num+10 && pilotY>=num2 && pilotY<=(num2+25))
c2=true;

// The ball stroke with the player 2
if(pilotX==(num3-5) && pilotY>=num4 && pilotY<=(num4+25))
c2=false;
}
}
}

}
