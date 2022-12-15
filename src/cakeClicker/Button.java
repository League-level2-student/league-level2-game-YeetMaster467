
package cakeClicker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Button implements ActionListener {

	int x;
	int y;
	int width;
	int height;
	String text;
	Color color;
	int moneyPerSecs;
	double secs;
	int cost;
	Font normalFont = new Font("Arial", Font.PLAIN, 12);
	Rectangle collisionBox;
	boolean buttonPressed = false;
	Timer timer;

	Button(int x, int y, int width, int height, String text, Color color, int moneyPerSecs, double secs, int cost) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.color = color;
		this.moneyPerSecs = moneyPerSecs;
		this.secs = secs;
		this.cost = cost;
		collisionBox = new Rectangle(x, y, width, height);
		timer = new Timer((int) (secs * 1000), this);
	}

	public void draw(Graphics g) {
		if (!buttonPressed) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.setFont(normalFont);
			g.drawString(text, (int) (x + width / 2 - width / 3), y + height / 2);
			collisionBox.setBounds(x, y, width, height);
		}
	}

	public void autoclick() throws InterruptedException {
		if (!buttonPressed) {
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Shop.money += moneyPerSecs;
	}

}
