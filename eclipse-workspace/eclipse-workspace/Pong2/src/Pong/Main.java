package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {


private static final long serialVersionUID = 1L; 

private JPanel contentPane = null;

private Panel panel = null;

private Panel getPanel() {
if (panel == null) {
panel = new Panel(); 
}
return panel;
}


public Main() {
super();
initialize();

// Listeners for the keyboard
this.addKeyListener(new KeyAdapter() {
//Method for the key pressed
public void keyPressed(KeyEvent evt) {
formKeyPressed(evt);
}

// Method for the key released
public void keyReleased(KeyEvent evt) {
formKeyReleased(evt);
}
});

}

// Here i'm stating the method that will send the key pressed to the game class
private void formKeyPressed(KeyEvent evt)
{
panel.keyPressed(evt);
}

// Here i'm stating the method that will send the key released to the game class
private void formKeyReleased(KeyEvent evt)
{
panel.keyReleased(evt);
}


private void initialize() {
this.setResizable(false);
this.setBounds(new Rectangle(312, 184, 250, 250)); 
this.setMinimumSize(new Dimension(250, 250));
this.setMaximumSize(new Dimension(250, 250));
this.setContentPane(getcontentPane());
this.setTitle("Pong");
}


private JPanel getcontentPane() {
if (contentPane == null) {
contentPane = new JPanel();
contentPane.setLayout(new BorderLayout());
contentPane.add(getPanel(), BorderLayout.CENTER);
}
return contentPane;
}

public static void main(String[] args) {
// TODO Auto-generated method stub
SwingUtilities.invokeLater(new Runnable() {
public void run() {
Main thisClass = new Main();
thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
thisClass.setVisible(true);
}
});
}
}
