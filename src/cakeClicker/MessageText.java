
package cakeClicker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MessageText implements ActionListener {
	
	Font messageFont = new Font("Arial", Font.PLAIN, 24);
	public boolean instructionsRead = false;
	boolean textShowing;
	public String text = "Welcome! Press 'I' anytime for instructions!";
	Timer timer;
	
	MessageText() {
		timer = new Timer(1000, this);
		textShowing = true;
		timer.start();
	}
	
	// x = 25, y = 750
	public void draw(Graphics g) {
		if (textShowing) {
			g.setColor(Color.BLACK);
			g.setFont(messageFont);
			g.drawString(text, 25, 750);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!textShowing) {
			textShowing = true;
		} else {
			textShowing = false;
		}
	}
	
}
